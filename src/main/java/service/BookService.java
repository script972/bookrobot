package service;

import dao.BookDAO;
import dao.interfacesDAO.BookDAOInter;
import entity.AutherEntity;
import entity.BookEntity;
import service.interfacesService.BookServiceInter;
import utils.LibrabryConsistBook;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookService implements BookServiceInter{

    private BookDAOInter bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public boolean addBook(String title, String auther) throws LibrabryConsistBook {
        //Проверка на наличие книги
        List<BookEntity> list = bookDAO.getAllBook(title);
        if(list.size()>0) {
            for (BookEntity bookEntity : list) {
                if(bookEntity.getAuther().getName().equals(auther))
                    throw new LibrabryConsistBook("This book already added in library");
            }
        }



        BookEntity book=new BookEntity(title, new AutherEntity(auther));
        if(bookDAO.saveBook(book)) {
            System.out.println(book + " was added");
            return true;
        }
        return false;
    }

    public int remove(String bookName) {
        List<BookEntity> list = bookDAO.getAllBook(bookName);
        if(list.size()>1)
            return manyRemote(list);
        if(list.size()==0) {
            System.out.println("Any book with title " + bookName);
            return 0;
        }
        if(bookDAO.remove(bookName)==1) {
            System.out.println(list.get(0)+" was removed");
            return 1;
        }
        return 0;
    }

    private int manyRemote(List<BookEntity> list) {
        System.out.println("we have few books with such name please choose " +
                "one by typing a number of book:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i+". "+list.get(i-1));
        }
        Scanner scanner=new Scanner(System.in);
        int delNumber=scanner.nextInt();
        if(bookDAO.remove(list.get(delNumber-1).getId())==1){
            System.out.println(list.get(delNumber-1)+" was removed");
        }
        return 0;
    }

    public void editBook(String editbookname) {

    }

    public List<BookEntity> getAllBook() {
        List<BookEntity> list=bookDAO.getAllBook();
        System.out.println("Our books: ");
        list.stream().forEach(System.out::println);
        return list;
    }
}
