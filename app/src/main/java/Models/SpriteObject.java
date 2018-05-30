package Models;

import android.media.Image;

public class SpriteObject {
    private Image image;
    private int cost;

    public SpriteObject(Image image, int cost) {
        this.image = image;
        this.cost = cost;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
