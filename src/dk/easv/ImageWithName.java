package dk.easv;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageWithName {
    private Image image;
    private String imageName;
    private ExecutorService executor;
    private List<Integer> rgbList;


    public ImageWithName(Image image, File file) throws ExecutionException, InterruptedException {
        this.image = image;
        imageName = file.getName();
        executor = Executors.newFixedThreadPool(3);
        rgbThread();
    }

    public Image getImage() {
        return image;
    }

    public String getImageName() {
        return imageName;
    }

    public void rgbThread() throws ExecutionException, InterruptedException {
        RGBCounter rgbCounter = new RGBCounter(this);
        Future<ArrayList<Integer>> future = executor.submit(rgbCounter);
        rgbList = future.get();
    }

    public List<Integer> getRgbList() {
        return rgbList;
    }
}
