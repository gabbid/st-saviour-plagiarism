import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Detector {
    public static void main(String[] args) {
        int d = 256; // Size of the ASCII alphabet for hashing.
        int q = 101; // A small prime number for hashing.

        try {
            // Read the original file's content into a single string.
            File originalFile = new File("poem.txt");
            Scanner originalScanner = new Scanner(originalFile, "UTF-8");
            StringBuilder originalTextBuilder = new StringBuilder();

            while (originalScanner.hasNextLine()) {
                originalTextBuilder.append(originalScanner.nextLine()).append("\n");
            }
            String originalText = originalTextBuilder.toString();
            originalScanner.close();

            // Read the plagiarized file line by line.
            File cheatFile = new File("cheat.txt");
            Scanner cheatScanner = new Scanner(cheatFile, "UTF-8");

            int lineNumber = 0;
            while (cheatScanner.hasNextLine()) {
                String pattern = cheatScanner.nextLine();
                lineNumber++;

                // Skip blank or whitespace-only lines.
                if (pattern.trim().isEmpty()) {
                    continue;
                }

                // Use Rabin-Karp to search for this line in the original text.
                if (search(pattern, originalText, d, q)) {
                    System.out.println("Plagiarism detected on line " + lineNumber + ": " + pattern);
                }
            }
            cheatScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found! Make sure poem.txt and cheat.txt are in the same directory.");
        }
    }

    // Rabin-Karp substring search algorithm. Returns true if the pattern is found in the text.
    public static boolean search(String pattern, String text, int d, int q) {
        int M = pattern.length();
        int N = text.length();

        if (M == 0 || M > N) return false;

        int p = 0; // Hash value for pattern.
        int t = 0; // Hash value for current window of text.
        int h = 1;

        // The value of h would be pow(d, M - 1)% q.
        for (int i = 0; i < M - 1; i++) {
            h = (h * d) % q;
        }

        // Calculate hash value for pattern and first window of text.
        for (int i = 0; i < M; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        // Slide the pattern over text one by one.
        for (int i = 0; i <= N - M; i++) {
            // Check hash values of current window and pattern.
            if (p == t) {
                int j = 0;
                while (j < M && text.charAt(i + j) == pattern.charAt(j)) {
                    j++;
                }
                if (j == M) {
                    return true; // Match found.
                }
            }

            // Calculate hash value for next window of text
            if (i < N - M) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + M)) % q;
                if (t < 0) t += q;
            }
        }
        return false;
    }
}