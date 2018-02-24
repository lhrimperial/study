package com.github.study.dubbo.serialize.beancp;

import com.github.study.dubbo.serialize.domain.SourceBean;
import com.github.study.dubbo.serialize.domain.TargetBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class BeanCopyDemo {
    private static BeanCopyFacade apacheBeanCopy;
    private static BeanCopyFacade cglibBeanCopy;
    private static BeanCopyFacade springBeanCopy;
    private static BeanCopyFacade javaBeanCopy;

    static {
        apacheBeanCopy = new ApacheBeanCopy();
        cglibBeanCopy = new CglibBeanCopy();
        springBeanCopy = new SpringBeanCopy();
        javaBeanCopy = new JavaBeanCopy();
    }

    public static void main(String[] args) throws Exception {
        final Integer loopCount = 10000;

        SourceBean sourceBean = new SourceBean();
        sourceBean.setId(1);
        sourceBean.setName("yzq");
        sourceBean.setResult(Boolean.TRUE);
        sourceBean.setContent("bean copy test.");

        TargetBean targetBean = new TargetBean();

//        multiThread(loopCount, sourceBean, targetBean);

        singleThreadTest(loopCount, sourceBean, targetBean);
    }

    private static void multiThread(Integer loopCount, SourceBean sourceBean, TargetBean targetBean) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < loopCount; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        apacheBeanCopy.copyBean(sourceBean, targetBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void singleThreadTest(Integer loopCount, SourceBean sourceBean, TargetBean targetBean)
            throws Exception {
        System.out.println("---------------- apache ----------------------");

        for (int i = 0; i < loopCount; i++) {

            apacheBeanCopy.copyBean(sourceBean, targetBean);
        }

        System.out.println("---------------- cglib ----------------------");

        for (int i = 0; i < loopCount; i++) {
            cglibBeanCopy.copyBean(sourceBean, targetBean);
        }

        System.out.println("----------------- spring ---------------------");

        for (int i = 0; i < loopCount; i++) {
            springBeanCopy.copyBean(sourceBean, targetBean);
        }

        System.out.println("----------------- setter ---------------------");

        for (int i = 0; i < loopCount; i++) {
            javaBeanCopy.copyBean(sourceBean, targetBean);
        }
    }

}
