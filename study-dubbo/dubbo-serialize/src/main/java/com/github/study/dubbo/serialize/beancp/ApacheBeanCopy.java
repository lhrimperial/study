package com.github.study.dubbo.serialize.beancp;

import com.github.study.dubbo.serialize.domain.SourceBean;
import com.github.study.dubbo.serialize.domain.TargetBean;
import org.springframework.beans.BeanUtils;

/**
 *
 */
public class ApacheBeanCopy implements BeanCopyFacade<SourceBean, TargetBean> {

    @Override
    public void copyBean(SourceBean sourceBean, TargetBean targetBean) throws Exception {
        long start = System.nanoTime();
        BeanUtils.copyProperties(targetBean, sourceBean);
        long end = System.nanoTime();

        System.out.println(String.format("%s consume %d microsecond", "apache  copy property", (end - start) / 1000));
    }
}
