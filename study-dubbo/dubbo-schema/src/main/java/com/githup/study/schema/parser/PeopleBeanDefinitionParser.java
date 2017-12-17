package com.githup.study.schema.parser;

import com.githup.study.schema.domain.People;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author longhairen
 * @create 2017/8/11 0011 下午 6:40
 */
public class PeopleBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return People.class;
    }
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String name = element.getAttribute("name");
        String age = element.getAttribute("age");
        String id = element.getAttribute("id");
        if (StringUtils.hasText(id)) {
            bean.addPropertyValue("id", id);
        }
        if (StringUtils.hasText(name)) {
            bean.addPropertyValue("name", name);
        }
        if (StringUtils.hasText(age)) {
            bean.addPropertyValue("age", Integer.valueOf(age));
        }
    }
}
