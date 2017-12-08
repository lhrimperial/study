package com.githup.study.jpa.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 */
@Repository
public class BaseDaoImpl<T, PK extends Serializable> extends BaseDao<T, PK> {

    private Class<T> entityClass;
    @Resource
    protected SessionFactory sessionFactory;

    public void getEntityClass() {
        this.entityClass = null;
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass(); //反射获取超类
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public T getByKey(PK id) {
        Assert.notNull(id, "id is required");
        getEntityClass();
        return (T) getSession().get(entityClass, id);
    }

    public T add(T entity) {
        Assert.notNull(entity, "entity is required");
        getSession().save(entity);
        return entity;
    }

    public T edit(T entity) {
        Assert.notNull(entity, "entity is required");
        getSession().update(entity);
        return entity;
    }

    public T deleteByKey(PK id) {
        Assert.notNull(id, "id is required");
        T t = (T) getSession().load(entityClass, id);
        getSession().delete(t);
        return t;
    }
}
