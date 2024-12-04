import java.time.LocalDate;
import java.util.ArrayList;

public class Event {

    private String title;
    private LocalDate date;
    public enum Priority {
        HIGH, MEDIUM, LOW
    }
    private ArrayList<EventTask> tasks = new ArrayList<EventTask>();

    public void addTask(EventTask task){
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

    public ArrayList<EventTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<EventTask> tasks) {
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
