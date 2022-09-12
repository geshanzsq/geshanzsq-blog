package com.geshanzsq.blog.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台启动类
 *
 * @author geshanzsq
 * @date 2022/3/14
 */
@SpringBootApplication(scanBasePackages = "com.geshanzsq.blog")
@MapperScan("com.geshanzsq.blog.**.mapper")
public class GeshanzsqBlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeshanzsqBlogAdminApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  格姗知识圈博客后台启动成功   ლ(´ڡ`ლ)ﾞ ");
    }

}
