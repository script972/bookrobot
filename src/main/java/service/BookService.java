package service;

import dao.AutherDAO;
import dao.BookDAO;
import dao.interfacesDAO.AutherDAOInter;
import dao.interfacesDAO.BookDAOInter;
import entity.AutherEntity;
import entity.BookEntity;
import service.interfacesService.BookServiceInter;
import utils.LibrabryConsistBook;
import utils.LibrabryHavnotBook;

import javax.swing.text.html.parser.Entity;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookService implements BookServiceInter {

    private BookDAOInter bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public boolean addBook(String title, String auther) throws LibrabryConsistBook {
        //Проверка на наличие книги
        List<BookEntity> list = bookDAO.getAllBook(title);
        if (list.size() > 0) {
            for (BookEntity bookEntity : list) {
                if (bookEntity.getAuther().getName().equals(auther))
                    throw new LibrabryConsistBook("This book already added in library");
            }
        }
        //проверяем наличие автора
        AutherEntity autherObj;
        AutherDAOInter iter = new AutherDAO();
        autherObj = iter.getAutherByName(auther);
        if (autherObj == null)
            autherObj = new AutherEntity(auther);
        //создаем и сохраняем
        BookEntity book = new BookEntity(title, autherObj);
        if (bookDAO.saveBook(book)) {
            System.out.println(book + " was added");
            return true;
        }
        return false;
    }

    public int remove(String bookName) {
        List<BookEntity> list = bookDAO.getAllBook(bookName);
        if (list.size() > 1)
            return manyRemote(list);
        if (list.size() == 0) {
            System.out.println("Any book with title " + bookName);
            return 0;
        }
        if (bookDAO.remove(bookName) == 1) {
            System.out.println(list.get(0) + " was removed");
            return 1;
        }
        return 0;
    }

    private int manyRemote(List<BookEntity> list) {
        System.out.println("we have few books with such name please choose " +
                "one by typing a number of book:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
        Scanner scanner = new Scanner(System.in);
        int delNumber = scanner.nextInt();
        if (bookDAO.remove(list.get(delNumber - 1).getId()) == 1) {
            System.out.println(list.get(delNumber - 1) + " was removed");
        }
        return 0;
    }

    public void editBook(String editbookname) throws LibrabryHavnotBook {
        List<BookEntity> list = bookDAO.getAllBook(editbookname);
        if (list.size() == 0)
            throw new LibrabryHavnotBook("Library have`t the book");
        if (list.size() > 1)
            manyEditor(list);
        else
            editItemBook(list.get(0));

    }

    private void editItemBook(BookEntity bookEntity) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editing " + bookEntity);
        System.out.println("\t Input new title: ");
        String title = scanner.nextLine();
        System.out.println("\t Input new auther name: ");
        String autherName = scanner.nextLine();
        AutherDAO autherDAO = new AutherDAO();
        AutherEntity autherEntity;
        autherEntity = autherDAO.getAutherByName(autherName);
        if (autherEntity != null)
            autherEntity.setName(autherName);
        else {
            autherEntity = new AutherEntity(autherName);
            autherDAO.save(autherEntity);
        }
        BookEntity newBook = new BookEntity(title, autherEntity);
        if (bookDAO.edit(bookEntity, newBook))
            System.out.println("Book editing success");

    }

    private void manyEditor(List<BookEntity> list) throws LibrabryHavnotBook {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose book by id");
        for (BookEntity item : list)
            System.out.println("\t id=" + item.getId() + " " + item);
        int id = scanner.nextInt();
        List<BookEntity> books = bookDAO.getAllBook(id);
        if (books.size() == 0)
            throw new LibrabryHavnotBook("The libraby have not the book");
        list = bookDAO.getAllBook(id);
        editItemBook(list.get(0));
    }

    public List<BookEntity> getAllBook() {
        List<BookEntity> list = bookDAO.getAllBook();
        System.out.println("Our books: ");
        list.stream().sorted(Comparator.comparing(BookEntity::getTitle)).forEach(System.out::println);
        return list;
    }
}
