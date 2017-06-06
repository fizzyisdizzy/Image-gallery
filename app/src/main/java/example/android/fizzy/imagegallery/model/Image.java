package example.android.fizzy.imagegallery.model;

/**
 * Created by Fizzy on 06/06/2017.
 */

public class Image {

    private String title;
    private String image;
    private String details;

    public Image(String title, String image, String details) {
        setTitle(title);
        setImage(image);
        setDetails(details);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
