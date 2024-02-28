import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadData {

    double chromo_length;

    public String read(String path) {
        String content = null;

        try {
            content = new String(Files.readAllBytes(Paths.get(path)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public  double extractChromosomelength(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        // Split the content string at newline characters to get an array of lines
        String[] lines = content.split("\n");

        // Extract the first line
        String firstLine = lines[0].trim();
        try {
            // Parse and return the integer from the first line
            return Integer.parseInt(firstLine);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The first line is not a valid integer");
        }
    }
}
