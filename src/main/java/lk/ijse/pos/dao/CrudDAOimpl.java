package lk.ijse.pos.dao;

import lk.ijse.pos.entity.SuperEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOimpl<T extends SuperEntity,ID extends Serializable> implements CrudDAO<T,ID>{

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entity;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public CrudDAOimpl(){
       entity =(Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

//    public void setSession(Session session){
//        this.session=session;
//    }

    public void save(T entity) throws Exception{
        getSession().save(entity);
    }
    public void update(T entity) throws Exception{
        getSession().update(entity);
    }
    public void delete(ID entityId)throws Exception{
        getSession().delete(getSession().load(entity,entityId));
    }
    public List<T> findAll()throws Exception{
       return getSession().createQuery("FROM " + entity.getName()).list();

    }
    public T find(ID entityId)throws Exception{
        return getSession().find(entity,entityId);
    }

}
