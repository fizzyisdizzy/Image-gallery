package example.android.fizzy.imagegallery.model;

/**
 * Created by Fizzy on 06/06/2017.
 */

public class Photo {

    private String title;
    private int image;
    private String details;

    public Photo() {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
