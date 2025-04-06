package example;

import db.Trackable;
import java.sql.Date;

public class Document implements Trackable, extends Entity{
    private String content;
    private Date creationDate;
    private Date lastModificationDate;

    public Document(String content){
        this.content = content;
    }

    @Override
    public void setCreationDate(Date date){
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate(){
        return;creationDate;
    }

    @Override
    public void setLastModificationDate(Date date){
        this.lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }
}
