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
        List<AutherEntity> list = query.setParameter("nameAuther", name).list();
        session.getTransaction().commit();
        session.close();
        if (list.size() >0)
            return list.get(0);
        return null;
    }

    @Override
    public boolean update(AutherEntity oldAugher, AutherEntity newAutehr) {
        return false;
    }

    @Override
    public void save(AutherEntity autherEntity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(autherEntity);
        session.getTransaction().commit();
        session.close();
    }
}
