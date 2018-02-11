package com.github.study.dubbo.serialize.beancp;

/**
 *
 */
public interface BeanCopyFacade<S, T> {

    /**
     * bean copy.
     *
     * @param sourceBean source bean
     * @param targetBean target bean
     * @throws Exception root exception
     */
    void copyBean(S sourceBean, T targetBean) throws Exception;
}
