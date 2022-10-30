package com.geshanzsq.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 前台启动类
 *
 * @author geshanzsq
 * @date 2022/4/17
 */
@SpringBootApplication(scanBasePackages = "com.geshanzsq")
@MapperScan("com.geshanzsq.**.mapper")
public class GeshanzsqBlogClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeshanzsqBlogClientApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  格姗知识圈博客客户端启动成功   ლ(´ڡ`ლ)ﾞ ");
    }

}
