import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

class AsyncTest {

	@Test
	void test() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(1); // 최소한의 pool 갯수 Default 1
		threadPoolTaskExecutor.setMaxPoolSize(5);
		threadPoolTaskExecutor.setQueueCapacity(5);
		threadPoolTaskExecutor.initialize();

		for (int i = 0; i < 10; i++) {
			threadPoolTaskExecutor.execute(() -> {
				try {
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			});

			System.out.println("pool Size : " + threadPoolTaskExecutor.getPoolSize());
			System.out.println("active Count : " + threadPoolTaskExecutor.getActiveCount());
			System.out.println("queue Size : " + threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().size());
			System.out.println("===============================END");
		}
	}
}