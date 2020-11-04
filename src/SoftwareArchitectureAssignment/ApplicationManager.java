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
    //it use the second constructor with existent ID
    public static void LoadVariablesArrayList(){
        try {
            moviesListArray.clear();
            File file = new File("Movies.txt");
            Scanner scanner = new Scanner(file);
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

            }
            //this bellow code block gets the last id in the list and save in the
            //static Variable ID if the constructor is called with out the id
            //it will be added by the static value + 1
            int lastIndex = moviesListArray.size()-1;
            String idValue = moviesListArray.get(lastIndex).getId();
            Movies.setID(    Integer.parseInt(idValue)   );
            scanner.close();
        }catch (IOException e){
            e.printStackTrace();

        }

    }
    public static void SortTheTextFileArrayListByIDorGenres(){
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
                        +m.getGenresId()+"#"+m.getMovieLength()+"#"+m.getPrice()+"#"+m.getQuantity());
            }
            fileWriterPrint.flush();
            fileWriterPrint.close();
            tempFile.renameTo(new File("Movies.txt"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void DeleteFile() throws IOException {
        File file = new File("Movies.txt");
        FileWriter fileWriter = new FileWriter(file);
        FileInputStream fis = new FileInputStream(file);
        fileWriter.flush();
        fileWriter.close();
        fis.close();
        file.delete();
    }
    public static boolean DeleteAndBuildNew( String deleteThis)  {
        try {
            File file = new File("Movies.txt");
            File tempFile = new File("TempFile.txt");
            FileWriter fileWriter = new FileWriter(tempFile, true);
            BufferedWriter fileWriterBuffer = new BufferedWriter(fileWriter);
            PrintWriter fileWriterPrint = new PrintWriter(fileWriterBuffer);
            Scanner scanner = new Scanner(file);

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


                if (!id.equals(deleteThis) && !nameMovie.equals(deleteThis)) {
                    fileWriterPrint.println(id+"#"+producer+"#"+nameMovie+"#"
                            +genres+"#"+movieLength+"#"+moviePrice+"#"+quantity);
                }
            }
            scanner.close();
            fileWriterPrint.flush();
            fileWriterPrint.close();
            file.delete();
            tempFile.renameTo(new File("Movies.txt"));
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean ValidateIfIdExists(String id){
        for (Movies m:moviesListArray) {
            if(m.getId().equals(id) || m.getNameMovie().equals(id)){
                return  DeleteAndBuildNew(id);
            }
        }
        return  false;
    }
    public static boolean ADDLine(Movies m) {
        try {
            File file = new File("Movies.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter fileWriterPrint = new PrintWriter(fileWriter);
            fileWriterPrint.println(m.getId() + "#" + m.getProducer() + "#" + m.getNameMovie() + "#"
                    + m.getGenresId() + "#" + m.getMovieLength() + "#" + m.getPrice() + "#" + m.getQuantity());
            fileWriter.flush();
            fileWriter.close();
            fileWriterPrint.flush();
            fileWriterPrint.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
        final String WHITE = "\033[0;37m";   // WHITE
        System.out.println( RED_BOLD +"   HERE IS ALL YOUR INVENTORY SO FAR ");

        for (Movies m : moviesListArray) {
            System.out.print(PURPLE_BOLD +"ID:" + BLUE_BOLD  +m.getId() );
            System.out.print(PURPLE_BOLD +" PRODUCER:"+ BLUE_BOLD + m.getProducer());
            System.out.print(PURPLE_BOLD +" MOVIE NAME:"+ BLUE_BOLD + m.getNameMovie());
            System.out.print(PURPLE_BOLD +" GENRE:" + BLUE_BOLD +m.getGenres());
            System.out.print(PURPLE_BOLD +" LENGTH:"+ BLUE_BOLD+ m.getMovieLength());
            System.out.print(PURPLE_BOLD +" PRICE:$"+ BLUE_BOLD+m.getPrice());
            System.out.print(PURPLE_BOLD +" TICKETS LEFT:" + BLUE_BOLD + m.getQuantity());
            System.out.println(WHITE);

        }


    }
    public static boolean SearchForItemByNameOrId(String item){
        boolean b = false;
        final String BLUE_BOLD = "\033[1;34m";   // BLUE
        final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        final String WHITE = "\033[0;37m";   // WHITE
        for (Movies m: moviesListArray) {
            if(item.equals(m.getNameMovie())){
                b=true;
            }else if(item.equals(m.getId())){
                b=true;
            }
            if(b){
                System.out.print(PURPLE_BOLD +"ID:" + BLUE_BOLD  +m.getId() );
                System.out.print(PURPLE_BOLD +" PRODUCER:"+ BLUE_BOLD + m.getProducer());
                System.out.print(PURPLE_BOLD +" MOVIE NAME:"+ BLUE_BOLD + m.getNameMovie());
                System.out.print(PURPLE_BOLD +" GENRE:" + BLUE_BOLD +m.getGenres());
                System.out.print(PURPLE_BOLD +" LENGTH:"+ BLUE_BOLD+ m.getMovieLength());
                System.out.print(PURPLE_BOLD +" PRICE:$"+ BLUE_BOLD+m.getPrice());
                System.out.print(PURPLE_BOLD +" TICKETS LEFT:" + BLUE_BOLD + m.getQuantity());
                System.out.println(WHITE);
                return b;
            }
        }
        return b;
    }
    public static boolean SearchForItemByGenre(String genre){
        boolean b = false;
        final String BLUE_BOLD = "\033[1;34m";   // BLUE
        final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        final String WHITE = "\033[0;37m";   // WHITE
        for (Movies m: moviesListArray) {
            if(genre.equals(m.getGenresId())) {
                b = true;
                System.out.print(PURPLE_BOLD + "ID:" + BLUE_BOLD + m.getId());
                System.out.print(PURPLE_BOLD + " PRODUCER:" + BLUE_BOLD + m.getProducer());
                System.out.print(PURPLE_BOLD + " MOVIE NAME:" + BLUE_BOLD + m.getNameMovie());
                System.out.print(PURPLE_BOLD + " GENRE:" + BLUE_BOLD + m.getGenres());
                System.out.print(PURPLE_BOLD + " LENGTH:" + BLUE_BOLD + m.getMovieLength());
                System.out.print(PURPLE_BOLD + " PRICE:$" + BLUE_BOLD + m.getPrice());
                System.out.print(PURPLE_BOLD + " TICKETS LEFT:" + BLUE_BOLD + m.getQuantity());
                System.out.println(WHITE);
            }
        }

        return b;
    }

}

