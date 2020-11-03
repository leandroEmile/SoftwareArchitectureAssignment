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
        System.out.println("2) Add A New Movie");
        System.out.println("3) Delete a Item in the List");

        String choice = input.nextLine();
        switch(choice) {
            case "1": {
                DisplayMainMenu();
                break;
            }
            case "2": {
                Get_User_Input_to_Add_new_Movie();
                DisplayMovieMenu();
                break;
            }
            case "3": {
                Delete_The_Item();
                /*System.out.println("System shutting down...");
                System.exit(0);
                break;*/
            }
            default: {
               /* System.out.println("Invalid option, try again!");
                DisplayMainMenu();*/
            }
        }
    }
    public static void Get_User_Input_to_Add_new_Movie(){
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

        Movies addMe = new Movies(producer,nameMovie, genres, Integer.parseInt(movieLength), Double.parseDouble(moviePrice),Integer.parseInt(quantity));
        if(appManager.ADDLine(addMe)){
            System.out.println(YELLOW_BOLD+"your new movie has been added"+WHITE);
            appManager.LoadVariablesArrayList();
        }

    }
    public static String Validate_All_User_Imput(String wordMessage ,int MinimumSize , boolean isAnumber , String Type){
        String in = "";

        if(!isAnumber) {
            do {
                System.out.println(wordMessage);
                in = input.nextLine();
                in = in.trim();
               // System.out.println(in);
                if (in.equals("") || in.equals("null") || in.length() < MinimumSize) {
                    System.out.println("you must type a valid value please try again");
                    in = "";
                    //input.next();
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
    public static void Delete_The_Item(){
        String userInput;
        System.out.println("Please to delete a field type the Name of the Movie or ID but first pick a option bellow");
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
                System.out.println("please type the ID or Movie Name Now");
                userInput = input.nextLine();
                userInput = userInput.trim();
                if(appManager.ValidateIfIdExists(userInput)){
                    System.out.println("Well done you deleted the file successfull");
                    appManager.LoadVariablesArrayList();
                }else{
                    System.out.println("ID or Movie Name not found or IOExeption please try again");
                    Delete_The_Item();
                }
                Delete_The_Item();
                break;
            case "3":
                appManager.DisplayAllInventory();
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
        //input.nextLine(); ValidateIfIdExists();
    }
}



/*
class Values implements Comparable<Values>{
    public String a, b,c;

    @Override
    public int compareTo(Values o) {
        int compare = a.compareTo(o.a);
        if(compare == 0 ){
            // iff they are the same the result we can compare the second value
        }
            return compare;
    }
}
public class Main {
    public Main() throws IOException {

    }
    public static void main(String[] args) throws IOException {
       */
/* File oo = new File("Movies.txt");
        oo.createNewFile();*//*

       */
/* new Main().CreatArrayListOBJ();
        new Main().DeleteAndBUild();
        new Main().ADDLine();
        new Main().CreatArrayListOBJ();*//*

    }
    public static void deletefile() throws IOException {
    File file = new File("C:\\Users\\LEANDRO\\IdeaProjects\\SoftwareArchitectureAssignment\\Toys.txt");
    FileWriter fileWriter = new FileWriter(file);
    FileInputStream fis = new FileInputStream(file);
    fileWriter.flush();
    fileWriter.close();
    fis.close();
    file.delete();
    }
    public void CreatArrayListOBJ() throws IOException{
        List<Values> addAllvalues = new ArrayList<Values>();
        File file = new File("Toys.txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNext()){

            String saveFromFile =scanner.nextLine();
            System.out.println(saveFromFile);
             int get1 = saveFromFile.indexOf(",");
             int get2 = saveFromFile.indexOf(",",get1+1);

             addAllvalues.add(new Values());

             addAllvalues.get(i).a  = saveFromFile.substring(0, get1);
             addAllvalues.get(i).b = saveFromFile.substring(get1 +1, get2);
             addAllvalues.get(i).c = saveFromFile.substring(get2 +1);
             i++;

        }
        scanner.close();
        System.out.println("------------------------------");
        Collections.sort(addAllvalues);
        for ( Values v: addAllvalues) {
            System.out.println(v.a+" "+v.b+" "+v.c);
        }
        scanner.close();
        deletefile();
        SortTxtFile(addAllvalues);

    }
    public void SortTxtFile(List<Values> arraylist) throws IOException {

             File tempFile = new File("TempFile.txt");
             tempFile.createNewFile();
             FileWriter fileWriter = new FileWriter(tempFile, true);
             BufferedWriter fileWriterBuffer = new BufferedWriter(fileWriter);
             PrintWriter fileWriterPrint = new PrintWriter(fileWriterBuffer);

             for (Values v: arraylist) {
                 fileWriterPrint.println(v.a+","+v.b+","+v.c);
             }
             fileWriterPrint.flush();
             fileWriterPrint.close();
             tempFile.renameTo(new File("Toys.txt"));

         }
    public void DeleteAndBUild()  {
            try {
                File file = new File("Toys.txt");
                File tempFile = new File("TempFile.txt");
                //tempFile.createNewFile();
                FileWriter fileWriter = new FileWriter(tempFile, true);
                BufferedWriter fileWriterBuffer = new BufferedWriter(fileWriter);
                PrintWriter fileWriterPrint = new PrintWriter(fileWriterBuffer);
                Scanner scanner = new Scanner(file);

                String id0 = "", id1 = "", id2 = "";
                while (scanner.hasNext()) {
                    String saveFromFile =scanner.nextLine();
                    System.out.println(saveFromFile);
                    int get1 = saveFromFile.indexOf(",");
                    int get2 = saveFromFile.indexOf(",",get1+1);
                    String a1,a2,a3;

                   a1  = saveFromFile.substring(0, get1);
                   a2 = saveFromFile.substring(get1 +1, get2);
                   a3 = saveFromFile.substring(get2 +1);
                    if (!a1.equals("1111")) {
                        fileWriterPrint.println(a1 + "," + a2 + "," + a3);
                    }
                }
                scanner.close();
                fileWriterPrint.flush();
                fileWriterPrint.close();
                file.delete();
                tempFile.renameTo(new File("Toys.txt"));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    public void ADDLine(){
            try{
                File file = new File("Toys.txt");
                FileWriter fileWriter = new FileWriter(file,true);
                PrintWriter fileWriterPrint = new PrintWriter(fileWriter);
                fileWriterPrint.println("1111,1111,1111");
                fileWriter.flush();
                fileWriter.close();
                fileWriterPrint.flush();
                fileWriterPrint.close();

            }catch (IOException e){
                e.printStackTrace();
            }
    }
}
*/
