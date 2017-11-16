# lucene-wiki-demo
一个基于lucene的全局搜索demo

## 项目使用了Spring boot，如果不熟悉Spring boot，需要先了解下

## 启动项目

    1. 把项目导入IDE, 选择maven工程
    2. 找到WikiApp， 运行main方法
    3. 打开浏览器，输入http://localhost:8000，进入首页

## 修改配置

    打开application.yml如下：

    server:
      port: 8000 //端口号
    
    wiki:
     config:
       path:
          images: D:/wiki/images/ //图片保存地址
          articles: D:/wiki/articles/ //文章保存地址
