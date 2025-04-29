import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderStats {
    private final Map<String, Integer> statusCount = new ConcurrentHashMap<>();
    private final AtomicInteger totalBlocks = new AtomicInteger(0);
    private final AtomicInteger serverCount = new AtomicInteger(0);
    private final AtomicInteger poweredByCount = new AtomicInteger(0);
    public static final HeaderStats POISON_PILL = new HeaderStats(true);
    private boolean isPoison;

    public HeaderStats() {
        this(false);
    }

    private HeaderStats(boolean isPoison) {
        this.isPoison = isPoison;
    }

    public boolean isPoison() {
        return isPoison;
    }
    public void updateFromBlock(String block) {
        String[] lines = block.split("\\r?\\n");
        String status = "UNKNOWN";

        if (lines.length > 0) {
            Matcher matcher = Pattern.compile("HTTP/\\d\\.\\d\\s+(\\d{3})").matcher(lines[0]);
            if (matcher.find()) status = matcher.group(1);
        }

        boolean hasServer = false, hasPoweredBy = false;
        for (String line : lines) {
            if (line.toLowerCase().startsWith("server:")) hasServer = true;
            if (line.toLowerCase().startsWith("x-powered-by:")) hasPoweredBy = true;
        }

        statusCount.merge(status, 1, Integer::sum);
        if (hasServer) serverCount.incrementAndGet();
        if (hasPoweredBy) poweredByCount.incrementAndGet();
        totalBlocks.incrementAndGet();
    }

    public void merge(HeaderStats other) {
        other.statusCount.forEach((k, v) ->
                statusCount.merge(k, v, Integer::sum)
        );
        serverCount.addAndGet(other.serverCount.get());
        poweredByCount.addAndGet(other.poweredByCount.get());
        totalBlocks.addAndGet(other.totalBlocks.get());
    }

    public void printStats() {
        System.out.println("== TỔNG HỢP ==");
        System.out.println("Tổng block: " + totalBlocks);
        System.out.println("Status codes: " + statusCount);
        System.out.printf("Server: %.2f%%\n", (serverCount.get() * 100.0 / totalBlocks.get()));
        System.out.printf("X-Powered-By: %.2f%%\n", (poweredByCount.get() * 100.0 / totalBlocks.get()));
    }
    public void writeTo(Writer writer) throws IOException {
        writer.write("== THỐNG KÊ ==\n");
        writer.write("Tổng block: " + totalBlocks.get() + "\n");
        writer.write("Status Codes:\n");
        for (var entry : statusCount.entrySet()) {
            writer.write(" - " + entry.getKey() + ": " + entry.getValue() + "\n");
        }
        writer.write(String.format("Server Header: %.2f%%\n", serverCount.get() * 100.0 / totalBlocks.get()));
        writer.write(String.format("X-Powered-By Header: %.2f%%\n", poweredByCount.get() * 100.0 / totalBlocks.get()));
    }
}
