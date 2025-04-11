package todo.entity;
import db.Entity;
public class Step extends Entity{
    private String title;
    public Status status;
    private int taskRef;
    public static final int STEP_ENTITY_CODE = 29;

    public enum Status{
        NotStarted, Completed
    }

    public Step (String title, int taskRef){
        this.title = title;
        this.taskRef = taskRef;
        this.status = status.NotStarted;
    }

    public Step copy(){
        Step copyStep = new Step(title, taskRef);
        copyStep.id = id;

        return copyStep;
    }

    @Override
    public int getEntityCode() {
        return STEP_ENTITY_CODE;
    }

    public String getTitle() {
        return title;
    }

    public int getTaskRef() {
        return taskRef;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
