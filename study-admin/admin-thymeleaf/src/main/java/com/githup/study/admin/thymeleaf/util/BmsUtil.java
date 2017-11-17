package com.githup.study.admin.thymeleaf.util;


import com.caiwei.framework.util.string.StringUtils;

/**
 * @author longhr
 * @version 2017/11/9 0009
 */
public class BmsUtil {

    public static String getUUID() {
        return StringUtils.replace(java.util.UUID.randomUUID().toString(), "-", "").toUpperCase();
    }
}
