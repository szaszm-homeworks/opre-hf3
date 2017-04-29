import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marci on 4/29/17.
 */
public class LockCommand extends Command {
    private Resource trynalock;
    private List<GraphVertex> vertices;

    public LockCommand(Resource res, List<GraphVertex> vertices) {
        trynalock = res;
        this.vertices = vertices;
    }

    @Override
    public void execute() {
        trynalock.tryLock(task);
    }
}
