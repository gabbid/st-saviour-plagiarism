import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Detector {
    public static void main(String[] args) {
        // System.out.println("new dawn, new day");
        File cheat = new File("./resources/cheat.txt");

        try {
            Scanner scanner = new Scanner(cheat);

            // This is where we should have access to the file content.
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}