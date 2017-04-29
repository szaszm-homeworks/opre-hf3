/**
 * Created by marci on 4/29/17.
 */
public class UnlockCommand extends Command {
    private Resource resource;

    public UnlockCommand(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void execute() {
        resource.release();
    }
}
