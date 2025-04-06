import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkJoinHeaderTask extends RecursiveTask<HeaderStats> {
    private static final int THRESHOLD = 1000;
    private final List<String> blocks;

    public ForkJoinHeaderTask(List<String> blocks) {
        this.blocks = blocks;
    }

    @Override
    protected HeaderStats compute() {
        if (blocks.size() <= THRESHOLD) {
            HeaderStats stats = new HeaderStats();
            for (String block : blocks) {
                stats.updateFromBlock(block);
            }
            return stats;
        } else {
            int mid = blocks.size() / 2;
            ForkJoinHeaderTask left = new ForkJoinHeaderTask(blocks.subList(0, mid));
            ForkJoinHeaderTask right = new ForkJoinHeaderTask(blocks.subList(mid, blocks.size()));
            //Đánh dấu trái chạy song song và thread hiện tại dùng để chaỵ phải
            left.fork();
            HeaderStats rightResult = right.compute();
            //đợi trái chạy xong lấy kết quả và merge với phải
            HeaderStats leftResult = left.join();
            leftResult.merge(rightResult);
            return leftResult;
        }
    }
}
