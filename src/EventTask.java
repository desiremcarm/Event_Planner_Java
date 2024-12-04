public class EventTask {

    private String text;
    private boolean isCompleted = false;

    public EventTask(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void manageTask(boolean b){
        this.isCompleted = b;
    }

    @Override
    public String toString() {
        return "EventTask{" +
                "text='" + text + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
