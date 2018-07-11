package com.frame.boot.frame.security.base;

import com.frame.boot.frame.WebApplication;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class BaseTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());
}
