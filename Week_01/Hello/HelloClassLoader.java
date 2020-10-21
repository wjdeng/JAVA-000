import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Wayne.Deng
 * @Date 2020/10/21 上午11:10
 * @Version 1.0
 **/
public class HelloClassLoader extends ClassLoader{


	public static void main(String[] args){

		String xlassFilePath = "Hello.xlass";

		try {
			// 自定义加载器加载类
			Class<?> clazz = new HelloClassLoader().findClass("Hello",xlassFilePath);
			
			// 获取方法
			Method hello = clazz.getDeclaredMethod("hello");
			hello.setAccessible(true);
			
			//通过反射调用方法
			hello.invoke(clazz.newInstance());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	protected Class<?> findClass(String className, String classFilePath) throws ClassNotFoundException{

		// 读取文件获得加载类的byte数组
		byte[] classBytes = readFile(classFilePath);

		return defineClass(className, classBytes, 0, classBytes.length);

	}

	private byte[] readFile(String filePath){


		byte[] result = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;

		try {
			fis = new FileInputStream(new File(filePath));
			bos = new ByteArrayOutputStream(1024);

			// 每次只读区1个字节
			byte[] win = new byte[1];
			int num;
			while ((num = fis.read(win)) != -1){

				// x = 255-x 还原x 255-(255-x) = x
				win[0] = (byte)(255-win[0]);
				bos.write(win,0,num);
			}
			result = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}