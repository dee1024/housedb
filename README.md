
Housedb 1.0
===========
房产信息网站大数据爬虫。部分数据来源于链家网站（http://www.lianjia.com )，请勿用于商业用途，仅供交流和个人娱乐。

特性
===
- __定期更新房源数据__　
- __提供房源价格变动通知服务__

数据统计
====
- __各个区域的房源数量情况__
![](https://raw.githubusercontent.com/dee1024/housedb/master/src/main/resources/images/demo3.png)
- __各个区域的均价情况__
![](https://raw.githubusercontent.com/dee1024/housedb/master/src/main/resources/images/demo4.png)
- __不同楼龄的房源数量情况__
![](https://raw.githubusercontent.com/dee1024/housedb/master/src/main/resources/images/demo5.png)
- __关注数最高的房源情况__
![](https://raw.githubusercontent.com/dee1024/housedb/master/src/main/resources/images/demo6.png)

房源价格变更
======
![](https://raw.githubusercontent.com/dee1024/housedb/master/src/main/resources/images/demo.PNG)
![](https://raw.githubusercontent.com/dee1024/housedb/master/src/main/resources/images/demo2.PNG)

安装说明
====
运行环境：JDK1.7+、Mysql、Elastic Stack(用于出报表，可选)

编译环境：JDK1.7+、Maven3

- __步骤一：新建库命名为housedb并导入基础表结构和数据，执行 db/housedb.sql文件即可__

- __步骤二：配置 application.properties 文件__

    1.Mysql数据源配置，redis配置请忽略

    2.设置每天定时任务执行的小时区间

    3.设置SMTP账号和接收通知的邮箱地址

    4.dev如果设置为true，则为"开发者模式"，忽略定制任务执行小时区间的限制

    5.如果没有代理服务器，则把 needproxy 设置为 false

- __步骤三：启动服务__

```
java -jar housedb-20180410003739.jar
```

- __步骤四：定时任务启动爬虫__

    默认启动服务后，5分钟爬虫会启动，爬虫会先生成当天的执行任务。记录在process 表中，然后逐一执行抓取房源索引，记录在houseindex，接着会抓取房源明细，记录在house表中。爬虫初次抓，数据量大约为1w ~ 2w之前，这些都是目前在线房源数，初次不会做邮件通知。通知会在第二天爬虫做房源信息检查的时候，发现有新房源或者是房源价格调整，则会发起通知。

