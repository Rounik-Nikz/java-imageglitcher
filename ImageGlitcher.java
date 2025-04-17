import java.io.*;
import java.util.Random;

public class ImageGlitcher {
    public static void main(String[] args) {
        String inputPath = "input.png";
        String outputPath = "glitched.png";

        try {
            //Reading the image into a byte array
            FileInputStream fis = new FileInputStream(inputPath);
            byte[] imageBytes = fis.readAllBytes();
            fis.close();

            //Fliping random bits
            Random rand = new Random();
            int numFlips = 100;

            // Skiping first 100 bytes to avoid corrupting file header
            for (int i = 0; i < numFlips; i++) {
                int index = 100 + rand.nextInt(imageBytes.length - 100);
                int bitPosition = rand.nextInt(8);
                imageBytes[index] ^= (1 << bitPosition); // XOR to flip bit
            }

            //Writing glitched image
            FileOutputStream fos = new FileOutputStream(outputPath);
            fos.write(imageBytes);
            fos.close();

            System.out.println("✅ Glitched image saved as '" + outputPath + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
