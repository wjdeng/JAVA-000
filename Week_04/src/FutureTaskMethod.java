import java.util.concurrent.*;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/11 下午3:06
 * @Version 1.0
 **/
public class FutureTaskMethod {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		long start=System.currentTimeMillis();
		// 在这里创建一个线程或线程池，
		// 异步执行 下面方法


		FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
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
		});
		new Thread(task).start();


		int result = task.get(); //这是得到的返回值

		// 确保  拿到result 并输出
		System.out.println("异步计算结果为："+result);

		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

		// 然后退出main线程
	}





}
