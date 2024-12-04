
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MarronDesireMain {

    // CONST COLORS
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_WHITE = "\u001B[37m";

    // MENU MSG
    final String MENU_MSG = ANSI_BLUE + "\n1️⃣ To add a new event \n" +
            "2️⃣ To delete an event \n" +
            "3️⃣ To list all existing events  \n" +
            "4️⃣ To change a task status (completed/uncompleted) \n" +
            "and 5️⃣ To exit (even thought we're sad to see you go!🥹) \n";


    // ARRAY OF EVENTS CREATED
    ArrayList<MarronDesireEvent> marronDesireEvents = new ArrayList<MarronDesireEvent>();
    // Scanner global instance
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MarronDesireMain program = new MarronDesireMain();
        program.init();
    }

    public void init(){
        showMenu();
    }

    /**
     * Shows the app menu
     * @return void
     *
     */
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

    /**
     * Handles the menu option chosen by the user
     * @return void
     * @param option the option chosen by the user
     */
    public void handleOption(int option){

        switch (option){
            case 1:
                addNewEvent();
                break;
            case 2:
                deleteNewEvent();
                break;
            case 3:
                showListOfEvents();
                break;
            case 4:
                updateTaskManager();
                break;
            case 5:
                System.out.println(ANSI_WHITE + "\n" + "Thank you for checking this prototype!");
                break;
            default:
                System.out.println("Please choose a valid option!");
                break;
        }
    }

    // HELPERS - MARK TASK DONE/NOT DONE
    /**
     * Manages the update of an existing task inside an existing event
     * @return void
     *
     */
    public void updateTaskManager(){
        boolean taskExists;

        System.out.println("Type an event name: ");
        String evName = sc.next();

        ArrayList<MarronDesireEventTask> tasks = listTaskForEvent(evName);

        if (tasks.isEmpty()){
            System.out.println("That event doesn't have tasks or the event doesn't exist.");
            return;
        } else {
            System.out.println("What task do you want to manage?");
            String taskName = sc.next();

            taskExists = doesTaskExist(taskName, tasks);

            if(taskExists){
                System.out.println("What status would you like to add? \n" +
                        "Type 'true' for done, 'false' to not done");
                Boolean status = sc.nextBoolean();

                updateTask(evName, taskName, status, tasks);
                listTaskForEvent(evName);
            } else {
                System.out.println("Task doesn't exist.");
            }
        }
    }

    /**
     * Checks if a task actually exists inside a list of event tasks from an specific event
     * @return boolean
     * @param taskName the task name
     * @param tasks the list of an specific event's tasks
     */
    public boolean doesTaskExist(String taskName, ArrayList<MarronDesireEventTask> tasks){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getText().equals(taskName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all the tasks of an specific event
     * @return ArrayList<MarronDesireEventTask>
     * @param eventName the name of the event
     */
    public ArrayList<MarronDesireEventTask> listTaskForEvent(String eventName){
        ArrayList<MarronDesireEventTask> tasks = new ArrayList<MarronDesireEventTask>();

        for (int i = 0; i < marronDesireEvents.size(); i++) {
            if(eventName.equals(marronDesireEvents.get(i).getTitle())){
                System.out.println(marronDesireEvents.get(i).getTasks());
                tasks = marronDesireEvents.get(i).getTasks();
            }
        }
        return tasks;
    }

    /**
     * Finds a specific task of an event and changes its status to TRUE or FALSE
     * @return void
     * @param taskName the name of the task
     * @param evName the name of the event
     * @param tasks the list of the event tasks
     * @param b the status -> true or false
     */
    public void updateTask(String evName, String taskName, boolean b, ArrayList<MarronDesireEventTask> tasks){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getText().equals(taskName)){
                tasks.get(i).manageTask(b);
            }
        }
    }

    // HELPER - SHOWING ALL EVENTS
    /**
     * Shows a list of all existing events
     * @return void
     */
    public void showListOfEvents(){
        if(isEventListEmpty()){
            System.out.println("There are no events.");
        } else {
            for (int i = 0; i < marronDesireEvents.size(); i++) {
                System.out.println(marronDesireEvents.get(i).toString());
            }
        }
    }

    // HELPER - DELETING EVENT
    /**
     * Asks for the event title to be deleted, manages the deletion of it
     * @return void
     */
    public void deleteNewEvent(){
        System.out.println("Type the title of the event you want to delete");
        String eventName = sc.next();

        deleteFromEvents(eventName);
        getListOfEvents();
    }

    // AUX
    /**
     * Checks if there are existing events or not
     * @return boolean
     */
    public boolean isEventListEmpty(){
        if (marronDesireEvents.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Deletes an existing event
     * @return void
     * @param evName the name of the event to delete
     */
    public void deleteFromEvents(String evName){
        for (int i = 0; i < marronDesireEvents.size(); i++) {
            if (marronDesireEvents.get(i).getTitle().equals(evName)){
                marronDesireEvents.remove(i);
                System.out.println("Event successfully removed");
            }
        }
    }

    /**
     * Gets the list of all existing events in the program
     * @return void
     */
    public void getListOfEvents(){
        System.out.println("Current events registered in the system:");

        if (isEventListEmpty()){
            System.out.println("There aren't any events");
        } else {
            showListOfEvents();
        }
    }

    // HELPERS - CREATING EVENT
    /**
     * Adds a new event to the arraylist Events
     * @return void
     */
    public void addNewEvent(){

        System.out.println(ANSI_WHITE + "Type your event name");
        String newName = sc.next();

        LocalDate date = askForEventDate();
        MarronDesireEvent.Priority prio = setEventPriority();
        MarronDesireEvent newMarronDesireEvent = new MarronDesireEvent(newName, date, prio);

        addTasksToEvent(newMarronDesireEvent);

        marronDesireEvents.add(newMarronDesireEvent);
    }

    /**
     * Asks for a date to the user
     * @return localdate
     */
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

    /**
     * Checks if a date is valid or not
     * @return boolean
     * @params int day, month and year
     */
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

    /**
     * Sets the priority of an event
     * @return a priority
     */
    public MarronDesireEvent.Priority setEventPriority(){
        int prioSelected;
        MarronDesireEvent.Priority prios[] = MarronDesireEvent.Priority.values(); // all Priorities
        MarronDesireEvent.Priority prio = null;

        System.out.println("Set the priority for the event \n" +
                "Type 0 for LOW, 1 for MEDIUM, 2 for HIGH");
        prioSelected = sc.nextInt();

        for (int i = 0; i < prios.length; i++) {

            if(prioSelected == i){
                prio = prios[i];
            }
        }
        return prio;
    }

    /**
     * Add tasks to an event
     * @return void
     */
    public void addTasksToEvent(MarronDesireEvent ev){
        String taskName;
        int yn = 1;

        do {
            System.out.println("Would you like to add tasks?\n" +
                    "Type 0 for NO and 1 for YES");
            yn = sc.nextInt();

            if(yn == 1){
                System.out.println("Type the task name:");
                taskName = sc.next();

                MarronDesireEventTask evTask = new MarronDesireEventTask(taskName);

                ev.addTask(evTask);
                System.out.println(ev.getTasks());
            }

        }while(yn!=0);
    }
}