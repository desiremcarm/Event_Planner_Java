
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // MENU MSG
    final String MENU_MSG = "1️⃣ To add a new event \n" +
            "2️⃣ To delete an event \n" +
            "3️⃣ To list all existing events  \n" +
            "4️⃣ To change a task status (completed/uncompleted) \n" +
            "and 5️⃣ To exit (even thought we're sad to see you go!🥹)";

    // CONST COLORS
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_WHITE = "\u001B[37m";

    // ARRAY OF EVENTS CREATED
    ArrayList<Event> events = new ArrayList<Event>();

    public static void main(String[] args) {
        Main program = new Main();
        program.init();
    }

    public void init(){
        showMenu();
    }

    public void showMenu(){

        int option;
        Scanner sc = new Scanner(System.in); // Creating scanner

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
                System.out.println("It was 1");
                break;
            case 2:
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
}