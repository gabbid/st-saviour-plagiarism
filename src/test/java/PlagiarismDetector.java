import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlagiarismDetector {
    public static void main(String[] args) {
        try {
            // Change the file paths as needed.
            File originalFile = new File("poem.txt");
            File plagiarizedFile = new File("cheat.txt");
            
            Scanner originalScanner = new Scanner(originalFile, "UTF-8");
            Scanner plagiarizedScanner = new Scanner(plagiarizedFile, "UTF-8");

            // Store each line in a list.
            ArrayList<String> originalLines = new ArrayList<String>();
            while (originalScanner.hasNextLine()) {
                originalLines.add(originalScanner.nextLine());
            }

            // Join all lines into one string, separated by new lines.
            String originalContent = String.join("\n", originalLines);

            int lineNumber = 0;
            while (plagiarizedScanner.hasNextLine()) {
                lineNumber++;

                String pattern = plagiarizedScanner.nextLine();

                // Use Rabin-Karp to check if this line exists in the original file.
                if (RabinKarp.search(pattern, originalContent)) {
                    System.out.println("Plagiarism detected: \"" + pattern + "\" at line " + lineNumber);
                }
            }
            originalScanner.close();
            plagiarizedScanner.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}
    

    