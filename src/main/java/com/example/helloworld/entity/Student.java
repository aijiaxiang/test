package com.example.helloworld.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author:aijiaxiang
 * Date:2019/8/5
 * Description:通过yaml给对象注入值，map和对象属性用大括号{}，集合数组用中括号[]
 * 中括号可以省，大括号不可以省略
 *
 * @ConfigurationProperties(prefix = "student")与@Value同时使用的时候，
 * @ConfigurationProperties(prefix = "student")优先级更高，同时也可以互补
 *
 * nickName---nick-name yaml支持松散写法
 *
 *              @ConfigurationProperties            @Value
 * 注值          批量注入                             单个
 * 松散语法       支持                                不支持
 * SpEl         不支持                               支持
 * JSR303数据校验 支持                                不支持
 * 注入复杂类型    支持                                不支持
 *
 * 简单类型：（8大基本类型/Stirng/Date）
 *
 * @PropertySource:默认会加载application.properties/yml文件中的数据(若需要指定则@PropertySource(value={"classpath:xxx.yml"})
 *
 * @ImportResource
 *      springboot自动装配/自动配置
 *      spring等配置文件默认会被springboot自动给配置好
 *      如果自己要写spring等配置文件，springboot默认不识别
 *      springboot不推荐手写spring配置文件
 *      配置：xml配置文件，通过注解配置。
 *      springboot推荐时候用注解方式进行配置：@Configuration @Bean
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "student")
//@Validated//开启jsr303校验的注解
public class Student {
    @Email
    private String email;
    //@Value("${student.uname}")
    private String name;
    //@Value("233")
    private int age;
    private boolean sex;
    private Date birthday;
    private Map<String,Object> location;
    private String[] hobbies;
    private List<String> skills;
    private Pet pet;

}
