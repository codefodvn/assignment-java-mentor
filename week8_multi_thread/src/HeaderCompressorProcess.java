import java.io.*;
import java.nio.file.*;
import java.util.zip.GZIPOutputStream;

public class HeaderCompressorProcess {

    public static void compressFile(Path inputFile) {
        Path outputFile = inputFile.resolveSibling(inputFile.getFileName() + ".gz");

        try (
                InputStream in = Files.newInputStream(inputFile);
                OutputStream out = new GZIPOutputStream(Files.newOutputStream(outputFile))
        ) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            System.out.println("✔ Đã nén thành file GZIP: " + outputFile);
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi nén file (GZIP): " + inputFile);
            e.printStackTrace();
        }
    }
}
