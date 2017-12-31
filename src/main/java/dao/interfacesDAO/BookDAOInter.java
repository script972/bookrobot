package dao.interfacesDAO;

import entity.BookEntity;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface BookDAOInter {

    boolean saveBook(BookEntity book);

    List<BookEntity> getAllBook();

    List<BookEntity> getAllBook(String title);

    List<BookEntity> getAllBook(int id);

    int remove(String name);

    int remove(int id);

    boolean edit(String name, BookEntity book);

    boolean edit(int id, BookEntity book);

    boolean edit(BookEntity oldBook, BookEntity newBook);

}
