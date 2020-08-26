package evgenyt.testfilesgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class TestFilesGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/words.txt"));
        String wordsStr = scanner.useDelimiter("\\A").next();
        scanner.close();
        String[] words = wordsStr.split(" ");
        System.out.println("Words loaded: " + words.length);
        StringBuilder newStr = new StringBuilder();
        Random random = new Random();
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Input words num");
        int targetLength = inputScanner.nextInt();
        int chunkLength = 10000000;
        int chunks = targetLength / chunkLength;
        int modulo = targetLength - (chunkLength * chunks);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(
                new File("test.txt")));
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
