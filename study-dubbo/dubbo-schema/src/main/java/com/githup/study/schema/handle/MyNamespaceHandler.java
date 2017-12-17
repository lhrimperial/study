package com.githup.study.schema.handle;

import com.githup.study.schema.parser.PeopleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author longhairen
 * @create 2017/8/11 0011 下午 6:40
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
    }
}
