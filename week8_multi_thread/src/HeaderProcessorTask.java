import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

public class HeaderProcessorTask implements Callable<HeaderStats> {
    private final Path file;
    private final ForkJoinPool forkJoinPool;

    public HeaderProcessorTask(Path file, ForkJoinPool forkJoinPool) {
        this.file = file;
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    public HeaderStats call() throws Exception {
        String content = Files.readString(file);
        String[] blocks = content.split("(\\r?\\n){2,}");
        ForkJoinHeaderTask rootTask = new ForkJoinHeaderTask(Arrays.asList(blocks));
        HeaderStats stats = forkJoinPool.invoke(rootTask);

        // Ghi ra file output
        writeStatsToFile(stats);

        return stats;
    }
    private void writeStatsToFile(HeaderStats stats) throws IOException {
        String fileName = file.getFileName().toString().replace(".header", ".stats");
        Path outputDir = Paths.get("C:\\JavaCore\\output\\processed");
        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            stats.writeTo(writer);
        }

        // Gọi process nén
        HeaderCompressorProcess.compressFile(outputFile);
    }
}
