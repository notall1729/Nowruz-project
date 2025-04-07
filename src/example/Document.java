package example;

import db.Entity;
import db.Trackable;
import java.sql.Date;

public class Document extends Entity implements Trackable{
    public String content;
    private Date creationDate;
    private Date lastModificationDate;
    public static final int DOCUMENT_ENTITY_CODE = 7;

    public Document(String content){
        this.content = content;
    }

    @Override
    public void setCreationDate(Date date){
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate(){
        return creationDate;
    }

    @Override
    public void setLastModificationDate(Date date){
        this.lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    @Override
    public Document copy(){
        Document copyDocument = new Document(content);
        copyDocument.id = id;

        return copyDocument;
    }

    @Override
    public int getEntityCode(){
        return DOCUMENT_ENTITY_CODE;
    }
}
