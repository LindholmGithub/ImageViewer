package dk.easv;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageViewerWindowController implements Initializable
{
    private final List<ImageWithName> images = new ArrayList<>();
    private ExecutorService es = Executors.newFixedThreadPool(1);
    private Slideshow ss;

    @FXML
    private Slider secondsSlider;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnNext;
    @FXML
    private TextField secondsTextField;
    @FXML
    private Button btnStartSlideshow;
    @FXML
    private Button btnStopSlideshow;
    @FXML
    private Button btnLoad;

    @FXML
    Parent root;

    @FXML
    private ImageView imageView;

    @FXML
    private void handleBtnLoadAction()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (!files.isEmpty())
        {
            files.forEach((File f) ->
            {
                Image image = new Image(f.toURI().toString());
                images.add(new ImageWithName(image, f));
            });
            displayImage(images.get(0).getImage());
        }
    }

    @FXML
    private void handleBtnPreviousAction()
    {
        /*
        if (!images.isEmpty())
        {
            currentImageIndex =
                    (currentImageIndex - 1 + images.size()) % images.size();
            displayImage();
        }

         */
    }

    @FXML
    private void handleBtnNextAction()
    {
        /*
        if (!images.isEmpty())
        {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            displayImage();
        }

         */
    }

    private void displayImage(Image image)
    {
        imageView.setImage(image);
    }

    public void handleBtnStartSlideshow(ActionEvent actionEvent) {
        int delay = (int)secondsSlider.getValue();
        ss = new Slideshow(images,delay);
        ss.valueProperty().addListener((obs, o, n)->{
            displayImage(n.getImage());
        });
        ss.setOnCancelled(e -> {
            btnStartSlideshow.setDisable(false);
            btnStopSlideshow.setDisable(true);
            secondsSlider.setDisable(false);
        });
        ss.setOnRunning(e -> {
            btnStartSlideshow.setDisable(true);
            btnStopSlideshow.setDisable(false);
            secondsSlider.setDisable(true);
        });
        es.submit(ss);
    }

    public void handleBtnStopSlideshow(ActionEvent actionEvent) {
        ss.cancel();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnStartSlideshow.setDisable(false);
        btnStopSlideshow.setDisable(true);
    }
}