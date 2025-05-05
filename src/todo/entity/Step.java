package todo.entity;
import db.Entity;
public class Step extends Entity{
    private String title;
    public Status status;
    private int taskRef;
    public static final int STEP_ENTITY_CODE = 29;
    public static int stepCount = 1;

    public enum Status{
        NOTSTARTED, COMPLETED
    }

    public Step (String title, int taskRef, int count){
        this.title = title;
        this.taskRef = taskRef;
        this.status = status.NOTSTARTED;
        this.id = count;
    }

    public Step copy(){
        Step copyStep = new Step(title, taskRef, Step.stepCount);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
