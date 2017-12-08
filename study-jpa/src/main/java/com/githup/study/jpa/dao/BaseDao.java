package com.githup.study.jpa.dao;


/**
 *
 */
public abstract class BaseDao<T, PK> {

    public abstract T getByKey(PK id) ;

    public abstract T add(T entity);

    public abstract T edit(T entity) ;

    public abstract T deleteByKey(PK id) ;
}
