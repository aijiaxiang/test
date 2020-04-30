package com.example.helloworld;

import com.example.helloworld.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HelloworldApplicationTests {
    @Autowired
    private Student student;


    @Test
    public void contextLoads() {
    }




    @Test
    public void testStudent(){
        log.info("学生配置信息：{}",student);
    }

}
