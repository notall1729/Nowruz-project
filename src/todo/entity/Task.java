package todo.entity;

import db.Entity;
import db.Trackable;

import java.util.Date;

public class Task extends Entity implements Trackable{
    private String title;
    public String description;
    private Date dueDate;
    public status status;
    private Date creationDate;
    private Date lastModificationDate;
    public static final int TASK_ENTITY_CODE = 23;

    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status.NotStarted;
    }

    public void setCreationDate(Date date){
        if(creationDate == null)
            this.creationDate = null;
        else
            this.creationDate = new Date(date.getTime());
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
    public Date getCreationDate(){
        return creationDate;
    }

    @Override
    public int getEntityCode() {
        return TASK_ENTITY_CODE;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public Task copy(){
        Task copyTask = new Task(title, description, dueDate);
        copyTask.id = id;
        if(this.creationDate != null)
            copyTask.creationDate = new Date(this.creationDate.getTime());

        if(this.lastModificationDate != null)
            copyTask.lastModificationDate = new Date(this.lastModificationDate.getTime());

        return copyTask;
    }

    public enum status{
        NotStarted, InProgress, Completed
    }
}
