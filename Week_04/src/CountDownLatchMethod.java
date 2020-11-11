import java.util.concurrent.CountDownLatch;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/11 下午2:01
 * @Version 1.0
 **/
public class CountDownLatchMethod {

	private static volatile Integer result = null;


	public static void main(String[] args) throws InterruptedException {

		long start=System.currentTimeMillis();
		// 在这里创建一个线程或线程池，
		// 异步执行 下面方法

		CountDownLatch countDownLatch = new CountDownLatch(1);
		Thread thread = new Thread(new calculateTask(countDownLatch));
		thread.start();
		countDownLatch.await();

		// 确保  拿到result 并输出
		System.out.println("异步计算结果为："+result);

		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

		// 然后退出main线程
	}

	static class calculateTask implements Runnable{
		private CountDownLatch latch;
		public calculateTask(CountDownLatch latch){
			this.latch = latch;
		}

		@Override
		public void run() {
			synchronized (this){
				result = sum();
				latch.countDown();
			}

		}

		private  static int sum() {
			return fibo(36);
		}

		private  static int fibo(int a) {
			if ( a < 2)
				return 1;
			return fibo(a-1) + fibo(a-2);
		}
	}


}
