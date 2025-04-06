import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class HeaderAggregator implements Runnable {
    private final BlockingQueue<HeaderStats> statsQueue;

    public HeaderAggregator(BlockingQueue<HeaderStats> statsQueue) {
        this.statsQueue = statsQueue;
    }

    @Override
    public void run() {
        HeaderStats total = new HeaderStats();

        try {
            while (true) {
                HeaderStats stat = statsQueue.take();
                if (stat.isPoison()) break;
                total.merge(stat);
            }

            // Xuất tổng hợp cuối cùng ra file/log
            System.out.println("== TỔNG KẾ CUỐI CÙNG ==");
            total.writeTo(new OutputStreamWriter(System.out));
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

