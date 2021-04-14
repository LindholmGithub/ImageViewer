package dk.easv;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class RGBCounter implements Runnable {

    private ImageWithName localImageWithName;


    public RGBCounter(ImageWithName image){
        localImageWithName = image;
    }

    @Override
    public void run() {

    }
}
