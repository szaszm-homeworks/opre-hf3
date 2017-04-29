/**
 * Created by marci on 4/29/17.
 */
public abstract class Command {
    protected Task task;

    public abstract void execute();

    public void setTask(Task task) {
        this.task = task;
    }
}
