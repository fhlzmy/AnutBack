package com.fhlzmy.web.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    @Test
    void findMy(){
        System.out.println(userRepository.findAll());
    }

    @Test
    void findByAccount(){
        System.out.println(userRepository.findByAccount("fhl"));
    }


    @Test
    void test(){
        logger.info("info日志");
        logger.warn("warn日志");
        logger.debug("debug日志");
        logger.error("error日志");
    }

}
