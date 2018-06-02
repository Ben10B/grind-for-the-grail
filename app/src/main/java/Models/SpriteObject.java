package Models;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class SpriteObject implements Parcelable{
    private Image image;
    private int cost;
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SpriteObject createFromParcel(Parcel in) {
            return new SpriteObject(in);
        }

        public SpriteObject[] newArray(int size) {
            return new SpriteObject[size];
        }
    };

    public SpriteObject(Image image, int cost) {
        this.image = image;
        this.cost = cost;
    }

    public SpriteObject(Parcel in){
        //TODO Do I need to change what the image is stored as
//        this.image = in.read();
        this.cost = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //TODO Do I need to change what the image is stored as
//        dest.write();
        dest.writeInt(getCost());
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
