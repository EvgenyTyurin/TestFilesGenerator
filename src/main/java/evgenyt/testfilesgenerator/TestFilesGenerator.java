package evgenyt.testfilesgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Text files generator
 */

public class TestFilesGenerator {

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("ddMMyy-hhmmssSSS");
    private static final Random random = new Random();
    private static String[] words;

    public static void main(String[] args) throws FileNotFoundException {
        // Build dictionary
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/words.txt"));
        String wordsStr = scanner.useDelimiter("\\A").next();
        scanner.close();
        words = wordsStr.split(" ");
        System.out.println("Words loaded: " + words.length);
        // Ask user for size and count of files
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Input file size in Mb: ");
        int fileSize = inputScanner.nextInt();
        System.out.print("Input number of files: ");
        int fileCount = inputScanner.nextInt();
        // Generate files
        for (int count = 1; count <=fileCount; count++) {
            generateFile(fileSize);
        }
    }

    // Generate text file near of specified size in Mb
    public static void generateFile(int fileSize) throws FileNotFoundException {
        int targetLength = fileSize * 1000000 / 8; // Size in words
        int chunkLength = 10000000; // Size of write portion
        int chunks = targetLength / chunkLength;
        int modulo = targetLength - (chunkLength * chunks);
        String fileName = "test-" + String.format("%s", dateFormat.format(new Date())) + ".txt";
        System.out.println("Writing to:" + fileName);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(fileName)));
        for (int chunkIdx = 1; chunkIdx <= chunks; chunkIdx++) {
            System.out.println("Chunk: " + chunkIdx);
            StringBuilder chunkStr = new StringBuilder();
            for (int wordIdx = 1; wordIdx <= chunkLength; wordIdx++) {
                int randomWord = random.nextInt(words.length);
                chunkStr.append(words[randomWord]).append(" ");
            }
            printWriter.append(chunkStr);
        }
        StringBuilder moduloStr = new StringBuilder();
        for (int wordIdx = 1; wordIdx <= modulo; wordIdx++) {
            int randomWord = random.nextInt(words.length);
            moduloStr.append(words[randomWord]).append(" ");
        }
        printWriter.append(moduloStr);
        printWriter.close();
    }

}
