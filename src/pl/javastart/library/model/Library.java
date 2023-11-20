package pl.javastart.library.model;

import pl.javastart.library.exception.PublicationAlreadyExistException;
import pl.javastart.library.exception.UserAlreadyExistException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Map<String, Publication>  getPublications() {
        return (Map<String, Publication>) publications.keySet();

    }

    public void addPublication(Publication publication) {
    if (publications.containsKey(publication.getTitle())){
        throw new PublicationAlreadyExistException("Podana pozycja już istnieje w bazie: " + publication.getTitle());
    } else
        publications.put(publication.getTitle(),publication);

    }
    public void addUser(LibraryUser user) {
        if (users.containsKey(user.getPesel())) {
            throw new UserAlreadyExistException("Podany użytkownik już istnieje w bazie: " + user.toString());
        } else
            users.put(user.getPesel(), user);
    }


    public boolean removePublication (Publication pub)
    {
        if (publications.containsValue(pub)) {
            publications.remove(pub.getTitle());
            return true;
        }
        else {return false;}

    }
}