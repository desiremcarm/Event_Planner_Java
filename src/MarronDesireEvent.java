import java.time.LocalDate;
import java.util.ArrayList;

public class MarronDesireEvent {

    private String title;
    private LocalDate date;
    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    Priority priority;
    private ArrayList<MarronDesireEventTask> tasks = new ArrayList<MarronDesireEventTask>();

    public MarronDesireEvent(String name, LocalDate eventDate, Priority prior){
        this.title = name;
        this.date = eventDate;
        this.priority = prior;
    }

    public void addTask(MarronDesireEventTask task){
        this.tasks.add(task);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<MarronDesireEventTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<MarronDesireEventTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", tasks=" + tasks +
                '}';
    }
}
