package com.github.study;

import com.github.study.transaction.sharding.ShardingApplication;
import com.github.study.transaction.sharding.aspect.Processor1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingApplication.class)
public class AppTest {

    @Autowired
    private Processor1 processor1;

    @Test
    public void test() {
        processor1.say("andy");
    }
}

