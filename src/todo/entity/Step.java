package todo.entity;
import db.Entity;
public class Step extends Entity{
    private String title;
    public status status;
    private int taskRef;
    public static final int STEP_ENTITY_CODE = 29;

    public enum status{
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
}
