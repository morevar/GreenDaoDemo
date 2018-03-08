
### Part1.greenDAO使用步骤：
1.添加引用 
```
http://greenrobot.org/greendao/documentation/introduction/
```

2.新建实体类（需要加上注解，如@Entity），make project（将自动在实体类中生成DAO文件等代码）
```
http://greenrobot.org/greendao/documentation/modelling-entities/
```

3.初始化DaoSession

4.CRUD
http://greenrobot.org/greendao/documentation/queries/

```
参考
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1127/2069.html
https://www.jianshu.com/p/84b91f7682ec
http://www.cnblogs.com/lenve/p/5865881.html
http://www.cnblogs.com/whoislcj/p/5651396.html
```

### Part2.其他功能
1，升级数据库

(1)重写DaoMaster.OpenHelper类，在onUpgrade方法中执行数据库的升级脚本，build.gradle文件中的schemaVersion加1


```
参考：http://blog.csdn.net/u013678930/article/details/50856351
```

(2)无效方案1：Github上的一个库(GreenDaoUpgradeHelper)，其实现有很多问题（数据表重命名、字段重命名等情况均不适用）
```
https://github.com/yuweiguocn/GreenDaoUpgradeHelper
```

(3)无效方案2：删除表的情况
```
http://blog.csdn.net/fancylovejava/article/details/46713445
```

2，关闭数据库

3，清除缓存
</p>
(1)所有DAO的缓存：daoSession.clear();

(2)指定表的DAO的缓存：daoSession.getNoteDao().detachAll();
```
http://greenrobot.org/greendao/documentation/sessions/
```

4，数据库加密

5，多线程使用数据库

6，使用多个数据库/切换数据库
```
参考：http://bbs.csdn.net/topics/392322216
```

### Part3.问题探讨
Q1，没有设定default值
```
https://www.cnblogs.com/enyusmile/p/4442271.html
https://github.com/greenrobot/greenDAO/issues/17
```