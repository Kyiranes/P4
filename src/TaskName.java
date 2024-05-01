import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TaskName implements Iterable<TaskName>, Comparable<TaskName> {
    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", priority=" + priority +
                ", description='" + description + '\'';
    }

    private String name;
    private int priority;
    private String description;


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        while (true) {
            try {
                if (priority < 0) {
                    this.priority = 0;
                    break;
                } else if (priority > 5) {
                    this.priority = 5;
                    break;
                } else {
                    this.priority = priority;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid priority value");
            }
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(TaskName other) {
        if(Objects.equals(this.priority, other.priority)) {
            return this.name.compareTo(other.name);
        } else if (this.priority < other.priority) {
            return 1;
        }
        else return -1;
    }

    @Override
    public Iterator<TaskName> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super TaskName> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<TaskName> spliterator() {
        return Iterable.super.spliterator();
    }
}
