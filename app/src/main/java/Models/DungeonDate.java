package Models;

import java.io.Serializable;
import java.util.Date;


public class DungeonDate implements Serializable{
    private Date date;
    private  Status status;

    public DungeonDate(Date date, Status status) {
        this.date = date;
        this.status = status;
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
