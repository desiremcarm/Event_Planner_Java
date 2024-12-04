
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // CONST COLORS
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_WHITE = "\u001B[37m";

    // MENU MSG
    final String MENU_MSG = ANSI_BLUE + "1️⃣ To add a new event \n" +
            "2️⃣ To delete an event \n" +
            "3️⃣ To list all existing events  \n" +
            "4️⃣ To change a task status (completed/uncompleted) \n" +
            "and 5️⃣ To exit (even thought we're sad to see you go!🥹)";


    // ARRAY OF EVENTS CREATED
    ArrayList<Event> events = new ArrayList<Event>();
    // Scanner global instance
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main program = new Main();
        program.init();
    }

    public void init(){
        showMenu();
    }

    public void showMenu(){

        int option;

        do {

            System.out.println(ANSI_BLUE + MENU_MSG); // Show menu instructions

            // Checking if the option is a valid int - else, asks a new value
            while(!sc.hasNextInt()){
                System.out.println(ANSI_RED + "⚠️ That value is not valid. Try again");
                sc.next();
            }

            option = sc.nextInt(); // Getting the option

            handleOption(option);

        }while(option!=5);
    }

    public void handleOption(int option){

        switch (option){
            case 1:
                addNewEvent();
                break;
            case 2:
                deleteNewEvent();
                System.out.println("It was 2");
                break;
            case 3:
                System.out.println("It was 3");
                break;
            case 4:
                System.out.println("It was 4");
                break;
            case 5:
                System.out.println("It was 5");
                break;
            default:
                System.out.println("It was other");
                break;
        }

    }

    // HELPER - DELETING EVENT
    public void deleteNewEvent(){
        System.out.println("Type the title of the event you want to delete");
        String eventName = sc.next();

        deteleFromEvents(eventName);
        getListOfEvents();
    }

    // AUX
    public void deteleFromEvents(String evName){
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getTitle().equals(evName)){
                events.remove(i);
                System.out.println("Event successfully removed");
            }
        }
    }

    public void getListOfEvents(){
        System.out.println("Current events registered in the system:");

        if (events.size() == 0){
            System.out.println("There aren't any events");
        } else {
            for (int i = 0; i < events.size(); i++) {
                System.out.println(events.get(i));
            }
        }
    }

    // HELPERS - CREATING EVENT
    public void addNewEvent(){

        System.out.println(ANSI_WHITE + "Type your event name");
        String newName = sc.next();

        LocalDate date = askForEventDate();
        Event.Priority prio = setEventPriority();
        Event newEvent = new Event(newName, date, prio);

        addTasksToEvent(newEvent);

        events.add(newEvent);
    }

    public LocalDate askForEventDate(){

        boolean isDateValid;
        LocalDate eventDate = null;

        // Ask for a valid event date
        do {
            System.out.println("Type the event day");
            int day = sc.nextInt();

            System.out.println("Type the event month");
            int month = sc.nextInt();

            System.out.println("Type the event year");
            int year = sc.nextInt();

            isDateValid = checkIfDateIsValid(day, month, year);

            if(isDateValid){
                eventDate = LocalDate.of(year, month, day);
            }

        }while(!isDateValid);

        return eventDate;
    }

    public boolean checkIfDateIsValid(int day, int month, int year){
        try {
            // Intentamos crear un LocalDate con los valores dados
            LocalDate.of(year, month, day);
            // Si no se lanza excepción, la fecha es válida
            return true;
        } catch (DateTimeException e) {
            System.out.println(ANSI_RED + "⚠️ That date is not valid. Try again");
            // Si se lanza excepción, la fecha no es válida
            return false;
        }
    }

    public Event.Priority setEventPriority(){

        int prioSelected;
        Event.Priority prios[] = Event.Priority.values(); // all Priorities
        Event.Priority prio = null;

        System.out.println("Set the priority for the event \n" +
                "Type 0 for LOW, 1 for MEDIUM, 2 for HIGH");
        prioSelected = sc.nextInt();

        for (int i = 0; i < prios.length; i++) {

            if(prioSelected == i){
                prio = prios[i];
            }
        }

        System.out.println(prio);

        return prio;
    }

    public void addTasksToEvent(Event ev){
        String taskName;
        int yn = 1;

        do {
            System.out.println("Would you like to add tasks?\n" +
                    "Type 0 for NO and 1 for YES");
            yn = sc.nextInt();

            if(yn == 1){
                System.out.println("Type the task name:");
                taskName = sc.next();

                EventTask evTask = new EventTask(taskName);

                ev.addTask(evTask);
                System.out.println(ev.getTasks());
            }

        }while(yn!=0);
    }
}