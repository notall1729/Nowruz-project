package example;

import db.Entity;
import db.Trackable;
import java.util.*;

public class Document extends Entity implements Trackable{
    public String content;
    private Date creationDate;
    private Date lastModificationDate;
    public static final int DOCUMENT_ENTITY_CODE = 7;

    public Document(String content){
        this.content = content;
    }

    public void setCreationDate(Date date){
        if(date == null)
            this.creationDate = null;
        else
            this.creationDate = new Date(date.getTime());
    }

    @Override
    public Date getCreationDate(){
        return creationDate;
    }

    public void setLastModificationDate(Date date){
        if(date == null)
           this.lastModificationDate = null;
        else
            this.lastModificationDate = new Date(date.getTime());
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    @Override
    public Document copy(){
        Document copyDocument = new Document(content);
        copyDocument.id = id;
      if(this.creationDate != null)
          copyDocument.creationDate = new Date(this.creationDate.getTime());

      if(this.lastModificationDate != null)
          copyDocument.lastModificationDate = new Date(this.lastModificationDate.getTime());


        return copyDocument;
    }

    @Override
    public int getEntityCode(){
        return DOCUMENT_ENTITY_CODE;
    }
}
