package dao.interfacesDAO;

import entity.AutherEntity;

public interface AutherDAOInter {
    AutherEntity getAutherByName(String name);

    boolean update(AutherEntity oldAugher, AutherEntity newAutehr);

    void save(AutherEntity autherEntity);
}
