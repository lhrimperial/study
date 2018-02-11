package com.github.study.dubbo.serialize.beancp;

import com.github.study.dubbo.serialize.domain.SourceBean;
import com.github.study.dubbo.serialize.domain.TargetBean;
import org.springframework.cglib.beans.BeanCopier;

/**
 *
 */
public class CglibBeanCopy implements BeanCopyFacade<SourceBean, TargetBean> {

    private BeanCopier beanCopier = BeanCopier.create(SourceBean.class, TargetBean.class, false);

    @Override
    public void copyBean(SourceBean sourceBean, TargetBean targetBean) throws Exception {
        long start = System.nanoTime();
        beanCopier.copy(sourceBean, targetBean, null);
        long end = System.nanoTime();

        System.out.println(String.format("%s consume %d microsecond", "cglib BeanCopier", (end - start) / 1000));
    }
}
