package Models;

import android.os.Parcelable;
import android.os.Parcel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import com.google.auto.value.AutoValue;

//@AutoValue
public class DungeonDate implements Parcelable{
    private Date date;
    private  Status status;
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DungeonDate createFromParcel(Parcel in) {
            return new DungeonDate(in);
        }

        public DungeonDate[] newArray(int size) {
            return new DungeonDate[size];
        }
    };

    public DungeonDate(Date date, Status status) {
        this.date = date;
        this.status = status;
    }

    public DungeonDate(Parcel in){
        this.status = Status.valueOf(in.readString());
        String d = in.readString();
        try {
            this.date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status.name());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        dest.writeString(dateFormat.format(getDate()));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
