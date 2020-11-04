package SoftwareArchitectureAssignment;

public class Movies implements Comparable<Movies>{

    private final String producer;
    private final String nameMovie;
    private  Genres genres;
    private final String genresid;
    private final int movieLength;
    private final double price;
    private int quantity;
    private String id;
    private static int ID;
    public static boolean typeOfSort = false;

    public Movies(String producer, String nameMovie, String genres, int movieLength, double price, int quantity) {
        this.producer = producer;
        this.nameMovie = nameMovie;
        this.movieLength = movieLength;
        this.price = price;
        this.quantity = quantity;
        genresid = genres;
        switch (genres){
            case "1":
                this.genres = Genres.Action;
                break;
            case "2":
                this.genres = Genres.Adventure;
                break;
            case "3":
                this.genres = Genres.Comedy;
                break;
            case "4":
                this.genres = Genres.Drama;
                break;
            case "5":
                this.genres = Genres.Fantasy;
                break;
            case "6":
                this.genres = Genres.Mystery;
                break;
            case "7":
                this.genres = Genres.Romance;
                break;
            case "8":
                this.genres = Genres.Thriller;
                break;
            case "9":
                this.genres = Genres.Western;
                break;
        }
        ID++;
        id = String.valueOf(ID);
    }
    public Movies(String id,String producer, String nameMovie, String genres, int movieLength, double price, int quantity) {
        this.id = id;
        genresid = genres;
        this.producer = producer;
        this.nameMovie = nameMovie;
        this.movieLength = movieLength;
        this.price = price;
        this.quantity = quantity;
        switch (genres){
            case "1":
                this.genres = Genres.Action;
                break;
            case "2":
                this.genres = Genres.Adventure;
                break;
            case "3":
                this.genres = Genres.Comedy;
                break;
            case "4":
                this.genres = Genres.Drama;
                break;
            case "5":
                this.genres = Genres.Fantasy;
                break;
            case "6":
                this.genres = Genres.Mystery;
                break;
            case "7":
                this.genres = Genres.Romance;
                break;
            case "8":
                this.genres = Genres.Thriller;
                break;
            case "9":
                this.genres = Genres.Western;
                break;
        }
    }
    public static void setID(int ID) {Movies.ID = ID; }
    public String getProducer(){ return producer; }
    public String getNameMovie(){ return nameMovie;}
    public Genres getGenres() { return genres; }
    public int getMovieLength() { return movieLength; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getId() { return id; }
    public static int getID(){ return ID; }
    public String getGenresid() { return genresid;}

    @Override
    public int compareTo(Movies o) {
        if(typeOfSort == false){
            int compare = id.compareTo(o.getId());
            return compare;
        }else{
            int compare = getGenres().name().compareTo(o.getGenres().name());
            return compare;
        }
       /* if(compare == 0 ){
            // iff they are the same the result we can compare the second value
        }*/

    }

}
enum Genres {
    Comedy,
    Fantasy,
    Mystery,
    Drama,
    Action,
    Thriller,
    Adventure,
    Romance,
    Western
}
