package dk.easv;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class RGBCounter implements Callable {

    private ImageWithName localImageWithName;
    private Image localImage;
    private int rCounter;
    private int gCounter;
    private int bCounter;
    private int mCounter;


    public RGBCounter(ImageWithName image){
        localImageWithName = image;
        localImage = localImageWithName.getImage();

    }

    @Override
    public ArrayList<Integer> call() throws InterruptedException {
        ArrayList<Integer> rgbList = new ArrayList<>();
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
                } else {
                    mCounter++;
                }
            }
        }
        rgbList.add(rCounter);
        rgbList.add(gCounter);
        rgbList.add(bCounter);
        rgbList.add(mCounter);
        return rgbList;

    }

}
