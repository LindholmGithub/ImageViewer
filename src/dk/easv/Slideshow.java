package dk.easv;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import java.util.List;

public class Slideshow extends Task<ImageWithName> {

    private int currentImageIndex = 0;
    private final List<ImageWithName> images;
    private int delay;

    public Slideshow(List<ImageWithName> images,int delay) {
        this.images = images;
        this.delay = delay;
    }

    @Override
    protected ImageWithName call() throws Exception {
        while (true){
            if (!images.isEmpty()) {
                currentImageIndex = (currentImageIndex + 1) % images.size();
            }
            ImageWithName image = images.get(currentImageIndex);
            this.updateValue(image);
            Thread.sleep(delay * 1000);
        }
    }

}
