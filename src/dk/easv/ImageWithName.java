package dk.easv;

import javafx.scene.image.Image;

import java.io.File;

public class ImageWithName {
    private Image image;
    private String imageName;
    private int red;
    private int green;
    private int blue;
    private int mixed;


    public ImageWithName(Image image, File file) {
        this.image = image;
        imageName = file.getName();
        RGBCounter rgbCounter = new RGBCounter(this);
        rgbCounter.run();
    }

    public Image getImage() {
        return image;
    }

    public String getImageName() {
        return imageName;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getMixed() {
        return mixed;
    }

    public void setMixed(int mixed) {
        this.mixed = mixed;
    }
}
