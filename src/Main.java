import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Map<String, Task> tasks = new HashMap<>();
    private static Map<String, Resource> resources = new HashMap<>();

    private static Resource getResourceByName(String name) {
        resources.putIfAbsent(name, new Resource(name));
        return resources.get(name);
    }

    private static Task getTaskByName(String name) {
        tasks.putIfAbsent(name, new Task(name));
        return tasks.get(name);
    }

    public static void main(String[] args) {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        List<GraphVertex> vertices = new ArrayList<>();
        br.lines().forEach(line -> {
            String[] lineComponents = line.split(",");
            if(lineComponents.length == 0) return;
            String taskName = lineComponents[0];
            Task task = getTaskByName(taskName);

            for(int i = 1; i < lineComponents.length; ++i) {
                String component = lineComponents[i].trim();
                if(component.equals("0")) {
                    task.addCommand(new NoOpCommand());
                } else {
                    String resourceName = component.substring(1);
                    Resource res = getResourceByName(resourceName);
                    if(component.charAt(0) == '+') {
                        task.addCommand(new LockCommand(res, vertices));
                    } else {
                        task.addCommand(new UnlockCommand(res));
                    }
                }
            }
        });

        ArrayList<Task> ts = new ArrayList<>(tasks.values());
        vertices.addAll(ts);
        vertices.addAll(resources.values());

        while (ts.size() > 0) {
            ts.forEach(Task::step);
            ts.removeIf(t -> t.stopped);
        }
    }
}
