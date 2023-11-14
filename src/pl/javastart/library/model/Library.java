package pl.javastart.library.model;

public class Library {


    private static final int MAX_PUBLICATIONS = 2000;

    private int publicationsNumber = 0;
    private Publication [] publications = new Publication [MAX_PUBLICATIONS];



    public void addBook(Book book) {
        if (publicationsNumber < MAX_PUBLICATIONS) {
            publications[publicationsNumber] = book;
            publicationsNumber++;
        } else {
            System.out.println("Maxymalna liczba książek została osiągnięta");
        }

    }

    public void printBooks() {

        for (int i = 0; i < publicationsNumber; i++) {
            int countBook = 0;
            if (publications[i] instanceof Book) {
                System.out.println(publications[i]);
                countBook++;
            }
            if (countBook == 0) {
                System.out.println("Brak książek w bibliotece");
            }
        }
    }

    public void addMagazine(Magazine magazine) {
        if(publicationsNumber < MAX_PUBLICATIONS) {
            publications[publicationsNumber] = magazine;
            publicationsNumber++;
        } else {
            System.out.println("Maxymalna liczba magazynów została osiągnięta");
        }

    }

    public void printMagazines() {
        for (int i = 0; i < publicationsNumber; i++) {
            int countMagazine = 0;
            if (publications[i] instanceof Magazine) {
                System.out.println(publications[i]);
                countMagazine++;
            }
            if (countMagazine == 0) {
                System.out.println("Brak magazynów w bibliotece");
            }
        }
    }
}