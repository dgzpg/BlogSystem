## 系统简介
　　本系统分为前台和后台两部分。
　　前台主要是展示博客，资源。
　　后台主要是显示博客的数据，显示访客的数据，显示访客记录，针对博客，博客类别，访客，资源等进行相应的管理操作（增加，删除，修改，查找）。前台可以浏览根据不同类别展示的博客，可以根据不同类别查看相应的博客，可以根据关键字搜索博客，可以查看具体某篇博客的具体内容。另外本系统提供了一些学习资料，用户可以通过链接获取相应资源。用户可以通过登录页面进入后台后台管理主要分为主页，操作日志，统计图表，博客模块，资源模块，访客模块以及本地图库等若干个模块。其中，主页主要负责展示博客的发表数，草稿数，访客数以及访问量。操作日志主要负责记录不同用户在本系统中进行了哪些的操作，并且可以根据不同的情况查询相应的操作记录。统计图表主要是用图表的形式来表现不同时间段里网站的访问人数，博客浏览排行，用户操作量以及博客的发表数。博客模块主要输针对博客进行一系列的操作，包括修改博客不同的状态，修改博客的内容，根据类别查询博客，增加、修改、查询、删除类别，并且可以通过关键字查询博客。资源模块用户可以添加资源，修改资源链接或状态，根据名称搜索资源，并且可以删除资源。访客模块主要负责记录系统操作的人数以及系统的总访问量。本地图库负责上传图片到服务端，用作博客的图片。

## 系统的项目模块图

![img](https://github.com/pgoup/BlogSystem/blob/master/%E5%9B%BE%E7%89%87/BlogSystem1.png)

## 系统的项目设计图

![img](https://github.com/pgoup/BlogSystem/blob/master/%E5%9B%BE%E7%89%87/BlogSystem2.png)
<br>

## 系统的数据库设计图

![img](https://github.com/pgoup/BlogSystem/blob/master/%E5%9B%BE%E7%89%87/BlogSystem3.jpg)

## 系统截图：

![img](https://github.com/pgoup/BlogSystem/blob/master/图片/front.jpg)

![img](https://github.com/pgoup/BlogSystem/blob/master/%E5%9B%BE%E7%89%87/index.jpg)

![img](https://github.com/pgoup/BlogSystem/blob/master/图片/blogs.jpg)


##  系统主要的技术
前端：jQuery、bootstrap、echarts、web uploader summernote

后端： spring boot、eureka、jpa、spring session、spring security、redis、spring AOP。

其中spring boot使用了Thymeleaf模板，主要应用在后台，而前台主要应用jsp来展示页面。另外还添加了spring boot的热部署，这样在每次修改项目源代码后不用自己手动重启项目。

eureka主要负责项目的微服务的注册和发现，使项目构成基本的分布式结构。另外项目中通过使用feign构建feignclient来从eureka中获取微服务并调用相应的微服务来完成指定的功能。

model层通过使用jpa来完成实体的持久化，通过jpa来与底层数据库进行关联，这样整个系统就可以应用面向对象设计。另外，还运用到了jpa中的分页功能，这样就可以很方便的对数据库进行分页查询。

本系统中的session设置是保存在缓存redis中，这样，之后再分布式中使用session就会方便很多。然后使用spring security来管理session，当session失效的时候可以设置跳转到指定的请求路径。

最后，项目使用了spring AOP来完成日志部分，用户发送一个请求，系统都可以获取到请求的相应的数据，这样就可以保存用户的操作和请求的参数，再进行保存。
       
##  在编码阶段遇到的坑：l
https://blog.csdn.net/qq_40133908/article/details/87972904


