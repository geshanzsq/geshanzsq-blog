## 格姗知识圈博客

一个基于 Spring Boot、Spring Security、Vue3、Element Plus 的前后端分离的博客网站！本项目基本上是小格子一个人开发，由于工作和个人能力原因，部分技术都是边学习边开发，特别是前端（工作中是后端开发），基本上不熟悉，代码质量和设计，请大家多多指教。

欢迎加群一起学习交流技术。

QQ 群交流：673465233

微信群：添加我微信：geshanintell，备注：格姗知识圈

码云下载地址（国内推荐）：[https://gitee.com/geshanzsq/geshanzsq-blog](https://gitee.com/geshanzsq/geshanzsq-blog)

Github 下载地址：[https://github.com/geshanzsq/geshanzsq-blog](https://github.com/geshanzsq/geshanzsq-blog)

## 博客简介

- 前端使用 Vue3、Element Plus UI。
- 后端使用 Spring Boot、Spring Security、Redis、MySQL等技术
- 后台管理支持加载动态权限菜单，权限修改立即生效，不用再退出重新登录。
- 博客网站支持第三方登录，如：QQ、Gitee、Github

### 后台管理功能

1. 用户管理：用户是整个系统操作人，主要完成系统用户配置
2. 角色管理：配置角色菜单、分配用户角色等。
3. 菜单管理：配置系统菜单、按钮权限标识、关联 API 等。
4. 数据字典：系统中经常使用的一些较为固定的数据进行维护。
5. API 管理：后端所有接口地址、请求方式等。
6. 参数配置：系统动态配置常用参数。
7. 登录日志：系统登录日志记录查询。
8. 操作日志：系统操作日志记录和查询。
9. 文章管理：文章编辑、查询等。
10. 分类管理：分类操作。
11. 标签管理：标签操作。
12. 图片管理：图片分类管理、图片上传等。
13. 友情链接：添加其他网站链接
14. 评论管理：对文章和留言板的评论进行审核
15. 第三方登录配置：动态添加第三方登录配置及登录管理
16. 推荐管理：对文章的推荐进行管理

### 客户端功能

1. 首页：网站首页相关文章展示
2. 分类：展示所有分类及分类下的文章
3. 标签：展示所有标签及标签下的文章
4. 归档：历史上已发布的文章，按时间线展示
5. 关于我：展示网站介绍和个人介绍
6. 留言板：展示用户留言
7. 个人中心：用户通过第三方平台登录后，可对个人基本信息修改，查看我的足迹、评论、收藏、点赞

## 运行

前端：需要搭建 Vue 脚手架环境，如：安装 Node.js、Vue-cli 。

后端：需要搭建 Java 开发环境。此外，还需要安装 MySQL 数据库（推荐 MySQL 8）、Redis。

#### 相关环境版本：

| 软件名称 | 版本号    |
| -------- | --------- |
| Node     | 16.16.0   |
| NPM      | 6.14.15   |
| JDK      | 1.8.0_202 |
| MySQL    | 8.0.29    |
| Redis    | 5.0.9     |
| Nginx    | 1.9.9     |

### 项目目录结构说明

```yml
geshanzsq-blog-admin: 后台管理模块
    geshanzsq-blog-admin-application: 后台管理应用启动和博客业务
    geshanzsq-blog-admin-system: 后台管理的系统管理模块
    geshanzsq-blog-admin-system-common: 后台管理的系统管理模块通用模块，目前是客户端需要使用的枚举和 PO
geshanzsq-blog-cleint:
    geshanzsq-blog-cleint-common: 客户端通用模块，目前是后台管理需要使用的枚举和 PO
    geshanzsq-blog-cleint-appication: 客户端相关逻辑和应用启动
geshanzsq-blog-common: 通用模块
    geshanzsq-blog-common-core: 核心通用模块，包含一些通用工具类
    geshanzsq-blog-common-framework: 通用框架模块
    geshanzsq-blog-common-limiter: 通用限流模块
    geshanzsq-blog-common-log: 通用日志模块
    geshanzsq-blog-common-redis: 通用缓存模块
    geshanzsq-blog-common-security: 通用安全框架模块
    geshanzsq-blog-common-swagger: 通用接口文档模块
vue-geshanzsq-blog-admin: 前端后台管理
vue-geshanzsq-blog-client: 前端客户端
```

#### 前端运行

##### 管理端

```bash
# 进入项目目录
cd vue-geshanzsq-blog-admin

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run serve
```

浏览器访问 [http://localhost:8823](http://localhost:8823)

##### 客户端

```bash
# 进入项目目录
cd vue-geshanzsq-blog-client

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run serve
```

浏览器访问 [http://localhost:8824](http://localhost:8824)

#### 后端运行

新建数据库 geshanzsq_blog 数据库，导入 `doc/sql/geshanzsq_blog.sql ` 数据库脚本

在 `geshanzsq-blog/geshanzsq-blog-admin/geshanzsq-blog-admin-application` 和 `geshanzsq-blog/geshanzsq-blog-client/geshanzsq-blog-client-application 应用启动模块下，有三个配置文件：

| application.yml            | application-dev.yml                               | application-prod.yml                              |
| -------------------------- | ------------------------------------------------- | ------------------------------------------------- |
| 主要配置文件，配置公共信息 | 开发环境，配置 MySQL 数据库、Redis 缓存、文件路径 | 生产环境，配置 MySQL 数据库、Redis 缓存、文件路径 |

上述配置完成后，启动后台管理 `geshanzsq-blog-admin-application` 和 客户端 `geshanzsq-blog-client-application` 应用

##  使用

后台管理：[http://localhost:8823](http://localhost:8823)

客户端：[http://localhost:8824](http://localhost:8824)

默认用户：admin

默认密码：admin123

### 后端技术

| 技术            | 说明                       | 官网链接                                                     |
| --------------- | -------------------------- | ------------------------------------------------------------ |
| Spring Boot     | MVC 框架                   | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Spring Security | 认证和授权安全框架         | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security) |
| MyBatis Plus    | ORM 框架                   | [https://mp.baomidou.com](https://mp.baomidou.com)           |
| Knife4j         | 接口文档管理框架           | [https://doc.xiaominfo.com](https://doc.xiaominfo.com)       |
| Redis           | 缓存框架                   | [https://redis.io](https://redis.io)                         |
| Lombok          | 对象封装工具               | [https://github.com/projectlombok/lombok](https://github.com/projectlombok/lombok) |
| Nginx           | Http 和反向代理 Web 服务器 | [http://nginx.org](http://nginx.org)                         |
| JustAuth        | 第三方登录工具             | [https://www.justauth.cn](https://www.justauth.cn)           |

### 前端技术

| 说明             | 官网                                                         |
| ---------------- | ------------------------------------------------------------ |
| 前端框架         | [https://vuejs.org](https://vuejs.org)                       |
| 路由框架         | [https://router.vuejs.org](https://router.vuejs.org)         |
| 全局状态管理框架 | [https://vuex.vuejs.org](https://vuex.vuejs.org)             |
| 前端 UI 框架     | [https://element-plus.gitee.io](https://element-plus.gitee.io) |
| 前端 Http 框架   | [https://github.com/axios/axios](https://github.com/axios/axios) |
| 富文本编辑器     | [https://www.wangeditor.com](https://www.wangeditor.com)     |
| Markdown 编辑器  | [http://ckang1229.gitee.io/vue-markdown-editor/zh](http://ckang1229.gitee.io/vue-markdown-editor/zh) |
| 代码语法高亮插件 | [https://github.com/highlightjs/highlight.js](https://github.com/highlightjs/highlight.js) |

## License

Copyright © 2020-2022 **[格姗知识圈](https://geshanzsq.com)** Released under the **Apache-2.0**.

> 注：如果你使用这个开源项目，请**注明**本项目来源和说明。

## 感谢

若依后台管理系统：[RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue)



## 效果图

![首页](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/e7ac1b5a-0987-4a88-9271-cb499ffc9d50.png)



![我的足迹](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/8ad32789-af76-432d-ace4-e14945892087.png)



![菜单管理.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/f2ccf73d-ac96-4668-9891-b9511974a6ef.png)



![数据字典.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/35a50b16-01a3-41e0-b250-72ed3b3a7432.png)



![API 管理.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/b0eab7aa-a1ca-40a2-949b-12422968a568.png)



![参数配置.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/542b40c6-6b6b-4981-ad65-dd36421c711f.png)



![文章管理.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/0a637bff-17e6-4032-b1b6-6046a36e178f.png)



![编辑文章.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/2028f307-117e-4175-886f-c29b263f7e41.png)



![编辑文章-2.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/39755c2a-49b0-4958-997c-6c75d15eeb39.png)



![图片管理.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/3343b723-6e55-4c86-8571-9a30b39535c0.png)



![第三方登录配置.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/b5f7bedd-9a10-4bd4-92b9-235295b0babc.png)



![推荐管理.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/3cd60f46-5e8b-4b53-92bd-2ee4f3e3862b.png)



![后台管理接口文档.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/a98bb355-4489-4866-aa3f-1763bdfea424.png)



![客户端接口文档.png](https://geshanzsq.com/geshanzsq-file/profile/image/2022/10/31/441076aa-1964-47ec-ab3b-43feaf8006c5.png)