package com.githup.study;

import com.githup.study.admin.thymeleaf.Main;
import com.githup.study.admin.thymeleaf.domain.po.NewsTypeDO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;
import com.githup.study.admin.thymeleaf.service.NewsTypeService;
import com.githup.study.admin.thymeleaf.util.FastJsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author longhr
 * @version 2017/11/1 0001
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class AdminTest {

    @Autowired
    private NewsTypeService newsTypeService;

    @Test
    public void testFindByPage() {
        PageResultVO<List<NewsTypeDO>> resultVo = newsTypeService.findByPage(new NewsTypeDO(), 2, 10);
        System.out.println(FastJsonUtil.toJsonString(resultVo));
    }

    @Test
    public void testFindById() {
        NewsTypeDO newsTypeDO = newsTypeService.findById(1L);
        Assert.assertNotNull(newsTypeDO);
    }

//    @Test
    public void testInsert() {
        NewsTypeDO newsTypeDO = null;
        for (int i = 0; i <= 20; i++) {
            newsTypeDO = new NewsTypeDO();
            newsTypeDO.setCreateTime(new Date());
            newsTypeDO.setModifyTime(new Date());
            newsTypeDO.setOpened(1);
            newsTypeDO.setPriority(i%10+1);
            newsTypeDO.setType("type"+i);
            newsTypeService.insert(newsTypeDO);
        }

    }
}
