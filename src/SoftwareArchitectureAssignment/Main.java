package SoftwareArchitectureAssignment;
import java.io.*;
import java.util.*;

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
       /* File oo = new File("Movies.txt");
        oo.createNewFile();*/
       /* new Main().CreatArrayListOBJ();
        new Main().DeleteAndBUild();
        new Main().ADDLine();
        new Main().CreatArrayListOBJ();*/
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
