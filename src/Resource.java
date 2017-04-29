import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by marci on 4/29/17.
 */
public class Resource extends GraphVertex {
    private Task heldBy;
    public Queue<Task> queue;

    public Resource(String name) {
        this.name = name;
        this.queue = new LinkedList<>();
    }

    public void tryLock(Task task) {
        task.trynalock = this;
        ArrayList<GraphVertex> visited = new ArrayList<>();

        GraphVertex current = task;

        while(current != null && !visited.contains(current)) {
            visited.add(current);
            current = current.getEdge();
        }

        task.trynalock = null;

        if(current != null) {
            // deadlock
            System.out.println(task.name + "," + Integer.toString(task.ip + 1) + "," + name);
            return;
        }


        // no deadlock, try to lock
        if(isLocked() || (!queue.isEmpty() && queue.element() != task) || !lock(task)) {
            // couldn't lock, add task to waiting fifo
            task.waitingFor = this;
            task.trynalock = this;
            if(!queue.contains(task)) queue.add(task);
        }
    }

    private boolean lock(Task locker) {
        if(heldBy != null) {
            return false;
        }

        heldBy = locker;
        if(queue.peek() == locker) {
            queue.remove();
        }
        locker.trynalock = null;
        locker.waitingFor = null;
        return true;
    }

    private boolean isLocked() { return heldBy != null; }

    public void release() { heldBy = null; }

    @Override
    public GraphVertex getEdge() {
        return heldBy;
    }
}
