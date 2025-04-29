import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        BlockingQueue<HeaderStats> statsQueue = new LinkedBlockingQueue<>();

        Thread aggregatorThread = new Thread(new HeaderAggregator(statsQueue));
        aggregatorThread.start();

        Files.walk(Paths.get("C:/JavaCore/100"))
                .filter(p -> p.toString().endsWith(".header"))
                .forEach(file -> {
                    executor.submit(() -> {
                        try {
                            HeaderProcessorTask task = new HeaderProcessorTask(file, forkJoinPool);
                            HeaderStats stats = task.call();
                            statsQueue.offer(stats);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        statsQueue.offer(HeaderStats.POISON_PILL); // tín hiệu kết thúc
        aggregatorThread.join();
    }


}
