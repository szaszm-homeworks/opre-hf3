import java.util.List;

/**
 * Created by marci on 4/29/17.
 */
public abstract class GraphVertex {
    public String name;
    /**
     * @return directed edges from this to the elements
     */
    public abstract GraphVertex getEdge();
}
