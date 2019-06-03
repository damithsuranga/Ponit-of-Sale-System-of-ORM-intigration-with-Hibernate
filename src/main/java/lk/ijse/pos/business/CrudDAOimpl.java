package lk.ijse.pos.business;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.SuperEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOimpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {


    private Class<T> entity;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    public CrudDAOimpl() {
        entity = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void save(T entity) throws Exception {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        getSession().merge(entity);
    }

    @Override
    public void delete(ID entityId) throws Exception {
        getSession().delete(getSession().load(entity, entityId));
    }

    @Override
    public List<T> findAll() throws Exception {
        return getSession().createQuery("FROM " + entity.getName()).list();
    }

    @Override
    public T find(ID entityId) throws Exception {
        return getSession().find(entity, entityId);
    }
}



