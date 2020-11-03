package SoftwareArchitectureAssignment;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ApplicationManager {
    public static  List<Movies> moviesListArray = new ArrayList<>();

    public ApplicationManager(){
        if(moviesListArray.isEmpty()) {
            LoadVariablesArrayList();
        }
    }
    //It load all the values from the txt Movies files to the arrayList moviesListArray
    public static void LoadVariablesArrayList(){
        try {
            moviesListArray.clear();
            File file = new File("Movies.txt");
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNext()) {
                String saveFromFile = scanner.nextLine();
               // System.out.println(saveFromFile);
                int get1 = saveFromFile.indexOf("#");
                int get2 = saveFromFile.indexOf("#", get1 + 1);
                int get3 = saveFromFile.indexOf("#", get2 + 1);
                int get4 = saveFromFile.indexOf("#", get3 + 1);
                int get5 = saveFromFile.indexOf("#", get4 + 1);
                int get6 = saveFromFile.indexOf("#", get5 + 1);

                String id =saveFromFile.substring(0, get1);
                String producer = saveFromFile.substring(get1 + 1, get2);
                String nameMovie = saveFromFile.substring(get2 + 1, get3);
                String genres = saveFromFile.substring(get3 + 1, get4);
                String movieLength = saveFromFile.substring(get4 + 1, get5);
                String moviePrice = saveFromFile.substring(get5 + 1, get6);
                String quantity = saveFromFile.substring(get6 + 1);
                moviesListArray.add(new Movies(id,producer,nameMovie,genres, Integer.parseInt(movieLength),Double.parseDouble(moviePrice),Integer.parseInt(quantity)));
           //     System.out.println(id+" "+producer+" "+nameMovie+" "+ genres+" "+movieLength+" "+ moviePrice+" "+quantity);
                i++;
            }
            //this bellow code block gets the last id in the list and save in the
            //static Variable ID if the constructor is called with out the id
            //it will be added by the static value + 1
            int lastIndex = moviesListArray.size()-1;
            String idValue = moviesListArray.get(lastIndex).getId();
            moviesListArray.get(0).setID(    Integer.parseInt(idValue)   );
            scanner.close();
        }catch (IOException e){
            e.printStackTrace();

        }

    }
    public static void SortTheTextFileArrayListByID(){
        try {
            DeleteFile();

            File tempFile = new File("TempFile.txt");
            tempFile.createNewFile();
            FileWriter fileWriter = new FileWriter(tempFile, true);
            BufferedWriter fileWriterBuffer = new BufferedWriter(fileWriter);
            PrintWriter fileWriterPrint = new PrintWriter(fileWriterBuffer);

            Collections.sort(moviesListArray);
            for (Movies m : moviesListArray) {
                fileWriterPrint.println(m.getId()+"#"+m.getProducer()+"#"+m.getNameMovie()+"#"
                        +m.getGenresid()+"#"+m.getMovieLength()+"#"+m.getPrice()+"#"+m.getQuantity());
            }
            fileWriterPrint.flush();
            fileWriterPrint.close();
            tempFile.renameTo(new File("Movies.txt"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void DeleteFile() throws IOException {
        File file = new File("C:\\Users\\LEANDRO\\IdeaProjects\\SoftwareArchitectureAssignment\\Movies.txt");
        FileWriter fileWriter = new FileWriter(file);
        FileInputStream fis = new FileInputStream(file);
        fileWriter.flush();
        fileWriter.close();
        fis.close();
        file.delete();
    }
    public static void DeleteAndBuildNew( String deleteThis)  {
        try {
            File file = new File("Movies.txt");
            File tempFile = new File("TempFile.txt");
            FileWriter fileWriter = new FileWriter(tempFile, true);
            BufferedWriter fileWriterBuffer = new BufferedWriter(fileWriter);
            PrintWriter fileWriterPrint = new PrintWriter(fileWriterBuffer);
            Scanner scanner = new Scanner(file);


            while (scanner.hasNext()) {
                String saveFromFile = scanner.nextLine();
                System.out.println(saveFromFile);
                int get1 = saveFromFile.indexOf("#");
                int get2 = saveFromFile.indexOf("#", get1 + 1);
                int get3 = saveFromFile.indexOf("#", get2 + 1);
                int get4 = saveFromFile.indexOf("#", get3 + 1);
                int get5 = saveFromFile.indexOf("#", get4 + 1);
                int get6 = saveFromFile.indexOf("#", get5 + 1);

                String id =saveFromFile.substring(0, get1);
                String producer = saveFromFile.substring(get1 + 1, get2);
                String nameMovie = saveFromFile.substring(get2 + 1, get3);
                String genres = saveFromFile.substring(get3 + 1, get4);
                String movieLength = saveFromFile.substring(get4 + 1, get5);
                String moviePrice = saveFromFile.substring(get5 + 1, get6);
                String quantity = saveFromFile.substring(get6 + 1);

                if (!id.equals(deleteThis)) {
                    fileWriterPrint.println(id+"#"+producer+"#"+nameMovie+"#"
                            +genres+"#"+movieLength+"#"+moviePrice+"#"+moviePrice+"#"+quantity);
                }
            }
            scanner.close();
            fileWriterPrint.flush();
            fileWriterPrint.close();
            file.delete();
            tempFile.renameTo(new File("Movies.txt"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean ValidateIfIdExists(String id){
        for (Movies m:moviesListArray) {
            if(m.getId().equals(id)){
                DeleteAndBuildNew(id);
                return true;
            }
        }
        return  false;
    }
    public static void ADDLine(Movies m) {
        try {
            File file = new File("Movies.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter fileWriterPrint = new PrintWriter(fileWriter);
            fileWriterPrint.println(m.getId() + "#" + m.getProducer() + "#" + m.getNameMovie() + "#"
                    + m.getGenresid() + "#" + m.getMovieLength() + "#" + m.getPrice() + "#" + m.getQuantity());
            fileWriter.flush();
            fileWriter.close();
            fileWriterPrint.flush();
            fileWriterPrint.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] ars){
        LoadVariablesArrayList();// this must go first to give a drive for the id
        //SortTheTextFileArrayListByID();
        //System.out.println(moviesListArray.get(0).getID());
        //String id,String producer, String nameMovie, String genres, int movieLength, double price, int quantity
      //  Movies m = new Movies("Marvel Studios", "Spider-Man: Homecoming", "5",80,7.40,12);
      //  ADDLine(m);
       // LoadVariablesArrayList();
        //DeleteAndBuildNew("1004");
        DisplayAllInventory();
    }
    public static void DisplayAllInventory(){
        final String BLACK_BOLD = "\033[1;30m";  // BLACK
        final String RED_BOLD = "\033[1;31m";    // RED
        final String GREEN_BOLD = "\033[1;32m";  // GREEN
        final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        final String BLUE_BOLD = "\033[1;34m";   // BLUE
        final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        final String CYAN_BOLD = "\033[1;36m";   // CYAN
        final String WHITE_BOLD = "\033[1;37m";  // WHIT
        System.out.println( RED_BOLD +"   HERE IS ALL YOUR INVENTORY SO FAR " + WHITE_BOLD );

        for (Movies m : moviesListArray) {
            System.out.println("ID:"+m.getId()+" PRODUCER:"+m.getProducer()+" MOVIE NAME:"+m.getNameMovie()+" GENRE:"
                    +m.getGenres()+" LENGTH:"+m.getMovieLength()+" PRICE:$"+m.getPrice()+" TICKETS LEFT:"+m.getQuantity());
        }


    }

}


  /* String numeroString ="1001";
        int myInt =  Integer.parseInt(numeroString);
        System.out.println(myInt);*/