package service.interfacesService;

import entity.BookEntity;
import utils.LibrabryConsistBook;

import java.util.List;

public interface BookServiceInter {

    boolean addBook(String title, String auther) throws LibrabryConsistBook;

    int remove(String bookName);

    void editBook(String editbookname);

    List<BookEntity> getAllBook();

}
