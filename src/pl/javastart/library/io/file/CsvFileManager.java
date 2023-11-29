package pl.javastart.library.io.file;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.exception.InvalidDataException;
import pl.javastart.library.exception.UserAlreadyExistException;
import pl.javastart.library.model.*;

import java.io.*;
import java.util.Collection;

class CsvFileManager implements FileManager {
    private static final String PUBLICATIONS_FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Library_Users.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    private void importUsers(Library library) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USERS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(library::addUser);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + USERS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + USERS_FILE_NAME);
        }

    }

    private LibraryUser createUserFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        try {
            return createUser(split);
        } catch (UserAlreadyExistException e) {

        }
        return null;
    }

    private LibraryUser createUser(String[] data) {
        String first_name = data[0];
        String last_name = data[1];
        String pesel = data[2];

        return new LibraryUser(first_name, last_name, pesel);
    }

    private void importPublications(Library library) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(PUBLICATIONS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(library::addPublication);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + PUBLICATIONS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + PUBLICATIONS_FILE_NAME);
        }
    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji" + type);

    }

    private Book createBook(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        String author = data[4];
        int pages = Integer.valueOf(data[5]);
        String isbn = data[6];
        return new Book(title, author, year, pages, publisher, isbn);
    }

    private Magazine createMagazine(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        int month = Integer.valueOf(data[4]);
        int day = Integer.valueOf(data[5]);
        String language = data[6];
        return new Magazine(title, publisher, language, year, month, day);
    }

    @Override
    public void exportData(Library library) {
        exportPublication(library);
        exportUsers(library);

    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCsv(users,USERS_FILE_NAME);
    }


    private void exportPublication(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications,USERS_FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection <T> collection, String fileName) {

        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (T element : collection) {

                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + fileName);
        }
    }
}
