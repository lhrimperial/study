package com.github.study.dubbo.serialize.beancp;

import com.github.study.dubbo.serialize.domain.SourceBean;
import com.github.study.dubbo.serialize.domain.TargetBean;

/**
 *
 */
public class JavaBeanCopy implements BeanCopyFacade<SourceBean, TargetBean> {

    @Override
    public void copyBean(SourceBean sourceBean, TargetBean targetBean) throws Exception {
        long start = System.nanoTime();
        targetBean.setId(sourceBean.getId());
        targetBean.setName(sourceBean.getName());
        targetBean.setResult(sourceBean.isResult());
        targetBean.setContent(sourceBean.getContent());
        long end = System.nanoTime();

        System.out.println(String.format("%s consume %d microsecond", "use setter", (end - start) / 1000));
    }
}
