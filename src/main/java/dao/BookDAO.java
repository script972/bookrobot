package dao;

import dao.interfacesDAO.BookDAOInter;
import entity.AutherEntity;
import entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class BookDAO implements BookDAOInter {

    public BookDAO() {
    }

    public boolean saveBook(BookEntity book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<BookEntity> getAllBook() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<BookEntity> list = session.createQuery("FROM BookEntity").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<BookEntity> getAllBook(String title) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM BookEntity WHERE title = :titleBook");
        List<BookEntity> list = query.setParameter("titleBook", title).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<BookEntity> getAllBook(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM BookEntity WHERE id = :idBook");
        List<BookEntity> list = query.setParameter("idBook", id).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public int remove(String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE BookEntity WHERE title = :titleBook");
        query.setParameter("titleBook", name);
        int numbers = query.executeUpdate();
        session.close();
        System.out.println("удаление");
        return numbers;
    }

    public int remove(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE BookEntity WHERE id = :idBook");
        query.setParameter("idBook", id);
        int numbers = query.executeUpdate();
        session.close();
        return numbers;
    }

    public boolean edit(String name, BookEntity book) {
        return false;
    }

    public boolean edit(int id, BookEntity book) {
        return false;
    }

    public boolean edit(BookEntity oldBook, BookEntity newBook) {
        return false;
    }
}
