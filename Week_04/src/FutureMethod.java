import jdk.management.resource.internal.FutureWrapper;

import javax.print.attribute.IntegerSyntax;
import java.util.concurrent.*;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/11 下午2:24
 * @Version 1.0
 **/
public class FutureMethod {


	public static void main(String[] args) throws ExecutionException, InterruptedException {

		long start=System.currentTimeMillis();
		// 在这里创建一个线程或线程池，
		// 异步执行 下面方法

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> task = executor.submit(new calculateTask());

		int result = task.get(); //这是得到的返回值

		// 确保  拿到result 并输出
		System.out.println("异步计算结果为："+result);

		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

		// 然后退出main线程
	}

	static class calculateTask implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			return sum();
		}

		private  int sum() {
			return fibo(36);
		}

		private  int fibo(int a) {
			if ( a < 2)
				return 1;
			return fibo(a-1) + fibo(a-2);
		}
	}



}
