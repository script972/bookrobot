import dao.BookDAO;
import entity.AutherEntity;
import entity.BookEntity;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.BookService;

import javax.persistence.metamodel.EntityType;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) throws Exception {
        new Manipulator().start();
       /* BookService bookService = new BookService();
        System.out.println(bookService.remote("Название книги"));*/
    }
}