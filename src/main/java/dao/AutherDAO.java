package dao;

import dao.interfacesDAO.AutherDAOInter;
import entity.AutherEntity;
import entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;

import java.util.List;

public class AutherDAO implements AutherDAOInter {
    @Override
    public AutherEntity getAutherByName(String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM AutherEntity WHERE name = :nameAuther");
        AutherEntity book = (AutherEntity) query.setParameter("nameAuther", name);
        session.getTransaction().commit();
        session.close();
        return book;
    }
}
