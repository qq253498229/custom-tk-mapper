# TK-MyBatis-Mapper自定义拓展

## 介绍

tk-mybatis-mapper的自定义拓展

## 安装教程

```xml

<dependency>
    <groupId>cn.codeforfun</groupId>
    <artifactId>custom-tk-mapper</artifactId>
    <version>0.0.1</version>
</dependency>
```

## 使用说明

h2数据库动态插入

```java
userMapper.insertSelective(user);
```

批量插入

```java
userMapper.insertBatch(userList);
```

## 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request



