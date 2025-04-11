import db.Entity;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import todo.entity.Task;
import todo.entity.Step;
import db.Database;
import java.util.stream.Collectors;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws InvalidEntityException, ParseException {
        while(true){
            try{
                String command = scanner.nextLine();

                if(command.equalsIgnoreCase("exit"))
                    break;

                switch (command.toLowerCase()){
                    case "add task":
                      addTask();
                      break;

                    case "add step":
                        addStep();
                        break;

                    case "delete":
                        delete();
                        break;

                    case "update task":
                        updateTask();
                        break;

                    case "update step":
                        updateStep();
                        break;

                    case "get task-by-id":
                        getTaskByID();
                        break;

                    case "get all-tasks":
                        getAllTasks();
                        break;

                    case "get incomplete-tasks":
                        getIncompleteTasks();
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Error: " + e.getMessage());

            }
        }
    }
    public static void addTask() throws InvalidEntityException, ParseException {
        System.out.println("Title: ");
        String title = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Due date: ");
        Date date = dateFormat.parse(scanner.nextLine());
        try {
            Task task = new Task(title, description, date);
            Database.add(task);
            System.out.println("Task saved successfully.\nID: " + task.id);
        } catch (Exception e){
            System.out.println("Cannot save task.");
            throw new InvalidEntityException("Error: Task title cannot be empty.");
        }
    }

    public static void addStep() throws InvalidEntityException{
        System.out.println("TaskID: ");
        int TaskID = scanner.nextInt();

        System.out.println("Title: ");
        String title = scanner.nextLine();

        try{
            Step step = new Step(title, TaskID);
            Database.add(step);
            System.out.println("Step saved successfully.\nID: " + TaskID);
        }catch (Exception e){
            System.out.println("Cannot save step.");
            throw new InvalidEntityException("Error: Cannot find task with ID=" + TaskID);
        }
    }

    public static void delete() throws EntityNotFoundException{
        System.out.println("ID: ");
        int ID = scanner.nextInt();

        try {
            Database.delete(ID);
              Entity entity = Database.get(ID);
            if(entity instanceof Task) {
                List<Entity> steps = Database.getAll(Step.STEP_ENTITY_CODE);
                for (Entity e: steps) {
                    Step step = (Step) e;
                    if (step.getTaskRef() == ID)
                        Database.delete(step.id);
                }
                System.out.println("Entity with ID= " + ID + " successfully deleted.");
            }
            }catch (EntityNotFoundException e){
            System.out.println("Cannot delete entity with ID= " + ID + " .");
            throw new EntityNotFoundException("Error: something happend.");
        }
    }

    public static void updateTask() throws InvalidEntityException{
        System.out.println("ID: ");
        int ID = scanner.nextInt();

        System.out.println("Field: ");
        String field = scanner.nextLine();

        System.out.println("New Value: ");
        String newValue = scanner.nextLine();

        try {
            Task task = (Task) Database.get(ID);
            Task oldTask = task.copy();

            switch (field.toLowerCase()) {
                case "title":
                    task.setTitle(newValue);
                    break;

                case "description":
                    task.setDescription(newValue);
                    break;

                case "duedate":
                    task.setDueDate(dateFormat.parse(newValue));
                    break;

                case "status":
                    Task.Status newStatus = Task.Status.valueOf(newValue.toUpperCase());
                    task.setStatus(newStatus);
                    if (newStatus == Task.Status.COMPLETED) {
                        Entity entity = Database.get(ID);
                        List<Entity> steps = Database.getAll(entity.getEntityCode());
                        for (Entity e : steps) {
                            Step step = (Step) e;
                            if (step.getTaskRef() == task.id)
                                step.setStatus(Step.Status.COMPLETED);
                            Database.update(task);
                        }
                    }
                    break;

                default:
                    throw new InvalidEntityException("Invalid field name.");

            }

            Database.update(task);
            System.out.println("Successfully updated the task.");
            System.out.println("Field: " + field);
            System.out.println("Old Value: " + getFieldValue(oldTask, field));
            System.out.println("New Value: " + newValue);
            System.out.println("Modification Date: " + timestampFormat.format(task.getLastModificationDate()));
        } catch (Exception e){
            System.out.println("Cannot update task with ID=" + ID +".");
        }
    }

    private static String getFieldValue(Task task, String field){
        switch (field.toLowerCase()){
            case "title":
                return task.getTitle();
            case "description":
                return task.getDescription();
            case "duedate":
                return dateFormat.format(task.getDueDate());
            case "status":
                return task.getStatus().toString();
            default:
                return null;
        }
    }

    public static void updateStep() throws InvalidEntityException, EntityNotFoundException{
     System.out.println("ID: ");
     int ID = scanner.nextInt();

     System.out.println("Field: ");
     String field = scanner.nextLine();

     System.out.println("New Value: ");
     String newValue = scanner.nextLine();

     try{
         Step step = (Step) Database.get(ID);
         Step oldStep = step.copy();

         switch (field.toLowerCase()){
             case "title":
                 step.setTitle(newValue);
                 break;

             case "status":
                 Step.Status newStatus = Step.Status.valueOf(newValue.toUpperCase());
                 step.setStatus(newStatus);

             default:
                 throw new InvalidEntityException("Invalid field name.");
         }

         Database.update(step);
         System.out.println("Successfully updated the step.");
         System.out.println("Field: " + field);
         System.out.println("Old Value: " + getFieldValue(oldStep, field));
         System.out.println("New Value: " + newValue);
         System.out.println("Modification Date: " + timestampFormat.format(new Date()));
     } catch (Exception e) {
         System.out.println("Cannot update step with ID= " + ID + ".");
     }
    }
    private static String getFieldValue(Step step, String field) {
        switch (field.toLowerCase()) {
            case "title":
                return step.getTitle();
            case "status":
                return step.getStatus().toString();
            default:
                return null;
        }
    }
    private static void getTaskByID() throws EntityNotFoundException{
        System.out.println("ID: ");
        int ID = scanner.nextInt();

        try{
            Task task = (Task) Database.get(ID);
            printTaskDetails(task);
        } catch (EntityNotFoundException e){
            System.out.println("Cannot find with ID= " + ID + ".");
        }
    }

    private static void printTaskDetails(Task task) {
        System.out.println("\nID: " + task.id);
        System.out.println("Title: " + task.getTitle());
        System.out.println("Due Date: " + dateFormat.format(task.getDueDate()));
        System.out.println("Status: " + task.getStatus());

        List<Entity> steps = Database.getAll(Step.STEP_ENTITY_CODE);
        boolean hasSteps = false;

        for (Entity entity: steps) {
            Step step = (Step) entity;
            if (step.getTaskRef() == task.id) {
                if (!hasSteps) {
                    System.out.println("Steps:");
                    hasSteps = true;
                }
                System.out.println("    + " + step.getTitle() + ":");
                System.out.println("        ID: " + step.id);
                System.out.println("        Status: " + step.getStatus());
            }
        }
    }

    private static void getAllTasks(){
        List<Entity> entities = Database.getAll(Task.TASK_ENTITY_CODE);
        List<Task> tasks = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity instanceof Task) {
                tasks.add((Task) entity);
            }
        }

        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDueDate().compareTo(t2.getDueDate());
            }
        });

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            printTaskDetails(task);
            System.out.println();
        }
    }

    private static void getIncompleteTasks() {
        List<Task> incompleteTasks = Database.getAll(Task.TASK_ENTITY_CODE).stream()
                .filter(e -> e instanceof Task)
                .map(e -> (Task) e)
                .filter(t -> t.getStatus() != Task.Status.COMPLETED)
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());

        if (incompleteTasks.isEmpty()) {
            System.out.println("No incomplete tasks found.");
            return;
        }

        incompleteTasks.forEach(task -> {
            printTaskDetails(task);
            System.out.println();
        });
    }
}
