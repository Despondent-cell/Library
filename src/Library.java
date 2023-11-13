public class Library {
    public static void main(String[] args) {
        final String appName = "Biblioteka v0.1";
        Book Book1 = new Book();
        Book1.title = "W pustyni i w puszczy";
        Book1.author = "Henryk Sienkiewicz";
        Book1.releaseDate =  2010;
        Book1.pages=296;
        Book1.publisher = "Greg";
        Book1.isbn = "9712924912812";

        System.out.println(appName);
        System.out.println("Książki dostepne w bibliotece");
        System.out.println(Book1.title);
        System.out.println(Book1.author);
        System.out.println(Book1.releaseDate);
        System.out.println(Book1.pages);
        System.out.println(Book1.publisher);
        System.out.println(Book1.isbn);

    }

}
