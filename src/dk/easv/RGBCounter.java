package dk.easv;

import javafx.scene.image.Image;

import java.awt.*;

public class RGBCounter implements Runnable {

    private ImageWithName localImageWithName;
    private Image localImage;
    private int rCounter;
    private int gCounter;
    private int bCounter;


    public RGBCounter(ImageWithName image){
        localImageWithName = image;
        localImage = localImageWithName.getImage();

    }

    @Override
    public void run() {
        int width = (int) localImage.getWidth();
        int height = (int) localImage.getHeight();
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Color color = new Color(localImage.getPixelReader().getArgb(y, x));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                if (red > green && red > blue) {
                    rCounter++;
                } else if(green > red && green > blue){
                    gCounter++;
                } else if (blue > red && blue > green){
                    bCounter++;
                }

                System.out.println("Red: " + rCounter);
                System.out.println("Green: " + gCounter);
                System.out.println("Blue: " + bCounter);
            }
        }
    }
}
