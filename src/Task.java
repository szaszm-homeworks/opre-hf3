import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marci on 4/29/17.
 */
public class Task extends GraphVertex {
    public ArrayList<Command> commands;
    public Resource trynalock;
    public Resource waitingFor;
    public boolean stopped;

    /**
     * Instruction pointer
     */
    public int ip;

    public Task(String name) {
        this.name = name;
        commands = new ArrayList<>();
        ip = 0;
        stopped = false;
    }

    public void addCommand(Command cmd) {
        cmd.setTask(this);
        commands.add(cmd);
    }

    public void step() {
        if(waitingFor != null) {
            tryLock();
            return;
        }

        if(ip >= commands.size()) {
            stopped = true;
            return;
        }
        Command cmd = commands.get(ip);

        cmd.execute();

        ip++;
    }

    private void tryLock() {
        waitingFor.tryLock(this);
    }

    @Override
    public GraphVertex getEdge() {
        return trynalock;
    }
}
