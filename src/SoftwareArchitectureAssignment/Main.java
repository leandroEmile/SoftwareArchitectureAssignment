package SoftwareArchitectureAssignment;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.io.*;
import java.util.*;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static ApplicationManager appManager = new ApplicationManager();

    public static void main(String[] args){
        DisplayMainMenu();

    }
    private static void DisplayMainMenu(){
        final String WHITE = "\033[0;37m";   // WHITE
        final String BLUE_BOLD = "\033[1;34m";   // BLUE
        System.out.println( BLUE_BOLD +"*** Welcome to The Movie Inventory System ***"+WHITE);
        System.out.println("1) Movies Menu");
        System.out.println("2) Show all Movies Availible");
        System.out.println("3) Shut System Down");

        String choice = input.nextLine();
        switch(choice) {
            case "1": {
                DisplayMovieMenu();
                break;
            }
            case "2": {
                appManager.DisplayAllInventory();
                DisplayMainMenu();
                break;
            }
            case "3": {
                System.out.println("System shutting down...");
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Invalid option, try again!");
                DisplayMainMenu();
            }
        }

    }
    private static void DisplayMovieMenu(){
        final String GREEN_BOLD = "\033[1;32m";  // GREEN
        final String WHITE = "\033[0;37m";   // WHITE
        System.out.println(GREEN_BOLD+"*** Welcome to The Movie Menu***" + WHITE);
        System.out.println("1) Main Menu");
        System.out.println("2) Display all Inventory");
        System.out.println("3) Add A New Movie");
        System.out.println("4) To Search for Item");
        System.out.println("5) Sort the text File Data");
        System.out.println("6) Delete a Item in the List");
        System.out.println("7) Shut Down System");

        String choice = input.nextLine();
        switch(choice) {
            case "1":
                DisplayMainMenu();
                break;
            case "2":
                ApplicationManager.DisplayAllInventory();
                DisplayMovieMenu();
                break;
            case "3":
                Get_User_Input_to_Add_new_Movie();
                DisplayMovieMenu();
                break;
            case "4":
                    Call_The_Search_DATA();
                    DisplayMovieMenu();
                break;
            case "5":
                SortDataInTheTextFile();
                DisplayMovieMenu();
                break;
            case "6":
                Delete_The_Item();
                DisplayMovieMenu();
                break;

            case "7":
                System.out.println("System shutting down...");
                System.exit(0);
                break;


            default: {
               /* System.out.println("Invalid option, try again!");
                DisplayMainMenu();*/
            }
        }
    }
    private static void Get_User_Input_to_Add_new_Movie(){
        final String RED_BOLD = "\033[1;31m";    // RED
        final String YELLOW_BOLD = "\033[1;33m";
        final String WHITE = "\033[0;37m";   // WHITE
        //String id ="";
        String Message = "Please type the name of the Producer";
        String producer = Validate_All_User_Imput(Message,5,false, "");
        Message = "Please type the name if the Movie";
        String nameMovie = Validate_All_User_Imput(Message,5,false, "");
        Message = "Please Type a number if \n" +
                "Action = 1\n" +
                "Adventure = 2 \n" +
                "Comedy = 3\n" +
                "Drama = 4\n" +
                "Fantasy = 5\n" +
                "Mystery = 6 \n" +
                "Romance = 7\n" +
                "Thriller = 8\n" +
                "Western = 9" ;
        String genres =  Validate_All_User_Imput(Message,1,true ,"int");
        Message = "Please type in minutes the Length of the movie:";
        String movieLength = Validate_All_User_Imput(Message,3,true ,"int");
        Message = "Please type as a double the price of the ticket:";
        String moviePrice = Validate_All_User_Imput(Message,5,true ,"double");
        Message = "Please type as a int  the Quantity ticket avaliable:";
        String quantity = Validate_All_User_Imput(Message,3,true ,"int");
        Movies.typeOfSort = false;// it will compare by id so there is no duplicates of the same id I sort it by id
        ApplicationManager.SortTheTextFileArrayListByIDorGenres();

        Movies addMe = new Movies(producer,nameMovie, genres, Integer.parseInt(movieLength), Double.parseDouble(moviePrice),Integer.parseInt(quantity));
        if(ApplicationManager.ADDLine(addMe)){
            System.out.println(YELLOW_BOLD+"your new movie has been added"+WHITE);
            ApplicationManager.LoadVariablesArrayList();
        }

    }
    private static String Validate_All_User_Imput(String wordMessage ,int MinimumSize , boolean isAnumber , String Type){
        String in;

        if(!isAnumber) {
            do {
                System.out.println(wordMessage);
                in = input.nextLine();
                in = in.trim();
               // System.out.println(in);
                if (in.equals("") || in.equals("null") || in.length() < MinimumSize) {
                    System.out.println("you must type a valid value please try again");
                    in = "";
                } else {
                    return in;
                }
            } while (true);
        }else{
            do {
                System.out.println(wordMessage);
                in = input.nextLine();
                in = in.trim();
                try{
                    if(Type.equals("int")){
                        Integer.parseInt(in);
                    }else if (Type.equals("double")){
                      Double.parseDouble(in);
                    }
                }catch (NumberFormatException e){
                    System.out.println("you must type a valid "+Type+" number");
                }
                if (in.equals("") || in.equals("null") || in.length() > MinimumSize) {
                    System.out.println("you must type a valid "+ Type +"  number");
                    in = "";

                } else {
                    return in;
                }
            } while (true);
        }
    }
    private static void Delete_The_Item(){
        final String RED_BOLD = "\033[1;31m";
        final String WHITE = "\033[0;37m";
        String userInput;
        System.out.println(RED_BOLD+"Please to delete a field type the Name of the Movie or ID but first pick a option bellow"+WHITE);
        System.out.println("1) Back to the Movies Menu");
        System.out.println("2) To delete by ID or Name");
        System.out.println("3) To Display all Movies");
        System.out.println("4) to EXIT the program");

        userInput = input.nextLine();
        userInput = userInput.trim();
        switch (userInput){
            case "1":
                System.out.println("Back to the Movies Menu");
                DisplayMovieMenu();
                break;
            case "2":
                if(ApplicationManager.moviesListArray.size() != 1) {
                    System.out.println(RED_BOLD+"please type the ID or Movie Name Now"+WHITE);
                    userInput = input.nextLine();
                    userInput = userInput.trim();
                    if (ApplicationManager.ValidateIfIdExists(userInput)) {
                        System.out.println("Well done you deleted the file successfull");
                        ApplicationManager.LoadVariablesArrayList();
                    } else {
                        System.out.println(RED_BOLD+"ID or Movie Name not found or IOExeption please try again"+WHITE);
                        Delete_The_Item();
                    }
                }else{
                    System.out.println("Sorry your inventory is equal to 1 you must add more movies before you can delete");
                }
                break;
            case "3":
                ApplicationManager.DisplayAllInventory();
                Delete_The_Item();
                break;
            case "4":
                System.out.println("System shutting down...");
                System.exit(0);
                break;
            default:
                System.out.println("Please delete from the options bellow");
                Delete_The_Item();
        }
    }
    private static void Call_The_Search_DATA() {
        final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        final String WHITE = "\033[0;37m";   // WHITE
        System.out.println(YELLOW_BOLD+"You can search your Item by Name or ID ");
        System.out.println("if you want to search By Genre all data to that Genre will be display");
        String  Message = "Please Type a number if \n" +
                "Action = 1\n" +
                "Adventure = 2 \n" +
                "Comedy = 3\n" +
                "Drama = 4\n" +
                "Fantasy = 5\n" +
                "Mystery = 6 \n" +
                "Romance = 7\n" +
                "Thriller = 8\n" +
                "Western = 9" ;
        System.out.println(Message);
        System.out.println(WHITE+"Please type Name or ID or Genre code now");
        String userInput = input.nextLine();
        userInput = userInput.trim();
        if(ApplicationManager.SearchForItemByNameOrId(userInput)){
            System.out.println("Thats whats was found!!");
        }else if(ApplicationManager.SearchForItemByGenre(userInput)){
            System.out.println("Thats all it was found!!");
        }else{
            System.out.println("Sorry nothing found try again!!");
        }
    }
    private static void SortDataInTheTextFile() {
        final String GREEN_BOLD = "\033[1;32m";
        final String WHITE = "\033[0;37m";   // WHITE
        System.out.println(GREEN_BOLD+"you can Sort by ID type = 1 \n or sort by Genre type =2 \n or back to Movies Menu type =3 enter now:" +WHITE);
        String userInput = input.nextLine();
        userInput = userInput.trim();
        switch (userInput){
            case"1":
                Movies.typeOfSort = false;// it will compare by id
                ApplicationManager.SortTheTextFileArrayListByIDorGenres();
                System.out.println("Sussefull sort by By ID");
                break;
            case"2":
                Movies.typeOfSort = true;// it will compare by id
                ApplicationManager.SortTheTextFileArrayListByIDorGenres();
                System.out.println("Sussefull sort by Genre");
                break;
            case"3":
                DisplayMovieMenu();
                break;
            default:
                SortDataInTheTextFile();
        }
    }
}




