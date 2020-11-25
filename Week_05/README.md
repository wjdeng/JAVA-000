
## 第五周-Spring框架作业

---
#### 本次作业主要做了以下工作
本次作业完成3道必做题，week05-1是第一次作业的一题必做题，week05-2是第二次作业的两道必做题，具体如下：

> （必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）

做题思路：
1. xml注入方式，在resource文件夹中创建了spring-beans.xml，并配置<bean>，再通过ClassPathXmlApplicationContext读取这个xml文件，通过getBean(指定的id)获取这个bean，具体可以看：https://github.com/wjdeng/JAVA-000/blob/main/Week_05/week05-1/src/main/java/com/beanassemble/javatask/week051/xmlDemo/XMLDemo.java
2. annotation注入方式，在School对象中配置了@Component，并且在spring-bean.xml文件中开启注解扫描，再通过ClassPathXmlApplicationContext读取这个xml文件，通过getBean(指定的类)获取这个bean，具体可以看：https://github.com/wjdeng/JAVA-000/blob/main/Week_05/week05-1/src/main/java/com/beanassemble/javatask/week051/annotationDemo/AnnotationDemo.java

---

> （必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

做题思路：
1. 参考了csdn的一篇文章制作了starter-demo这个jar包
2. 然后在starter-user中引入这个jar包，通过controller中通过@Autowire使用了自动装配的Student,Klass,School对象

具体可以看：https://github.com/wjdeng/JAVA-000/tree/main/Week_05/week05-2


问题：我写的additional-spring-configuration-metadata.json这个文件定义了默认值，不知道为啥，在user项目中如果没有在application.properties配置的话，就是null，不知道是不是配置有问题

---

> （必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
1）使用 JDBC 原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

做题思路：

1）2）这个相对比较简单，不行百度就OK了
具体可以看：https://github.com/wjdeng/JAVA-000/tree/main/Week_05/week05-2/starter-user/src/main/java/com/javatask/week05/starteruser/jdbc

3）写了配置类，并通过controller获取DateSource，获取connection，再执行sql语句，具体可以看：https://github.com/wjdeng/JAVA-000/tree/main/Week_05/week05-2/starter-user/src/main/java/com/javatask/week05/starteruser
