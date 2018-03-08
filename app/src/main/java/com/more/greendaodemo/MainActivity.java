package com.more.greendaodemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.more.greendaodemo.db.entity.Book;
import com.more.greendaodemo.db.entity.Category;
import com.more.greendaodemo.db.gen.BookDao;
import com.more.greendaodemo.db.gen.CategoryDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.Calendar;
import java.util.List;

/**
 * 此DEMO用于研究使用ORM库实现对SQLite进行基本操作：
 * 1.CRUD
 * 2.DB升级
 * 3.一个App使用多个DB的情况
 * <p>
 * 目前Github上Star最多的AndroidORM库是GreenDAO
 * https://github.com/greenrobot/greenDAO
 * http://greenrobot.org/greendao
 * <p>
 * 1.集成GreenDAO
 * 2.创建Model，然后make project
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //region - Dao -
    BookDao getBookDao() {
        return App.getApp().getDaoSession().getBookDao();
    }

    CategoryDao getCatDao() {
        return App.getApp().getDaoSession().getCategoryDao();
    }
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_query).setOnClickListener(this);
        findViewById(R.id.tv_insert).setOnClickListener(this);
        findViewById(R.id.tv_update).setOnClickListener(this);
        findViewById(R.id.tv_delete).setOnClickListener(this);
        findViewById(R.id.tv_clear).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getApp().closeDb();
        LogUtil.log("  onDestroy ...");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_query:
                query();
                break;
            case R.id.tv_update:
                update();
                break;
            case R.id.tv_delete:
                delete();
                break;
            case R.id.tv_insert:
                insert();
                break;
            case R.id.tv_clear:
                clear();
                break;
        }
    }

    //region - CRUD -
    private void clear() {
        getBookDao().deleteAll();
        LogUtil.log("  finished clear op.");
    }

    private void delete() {
        List<Book> list = getBookDao().queryBuilder().build().list();
        if (list != null && list.size() > 0) {
            Book item = list.get(list.size() - 1);
            getBookDao().delete(item);
            LogUtil.log("  finished delete op.");
        } else {
            LogUtil.log("  book_list is null or empty.");
        }
    }

    private void update() {
        List<Book> list = getBookDao().queryBuilder().limit(1).build().list();
        if (list != null && list.size() > 0) {
            Book item = list.get(0);
            item.setRepos(item.getRepos() + 1);
            getBookDao().update(item);
            LogUtil.log("  finished update op.");
        } else {
            LogUtil.log("  book_list is null or empty.");
        }
    }

    private void insert() {
        Book item = new Book();
        item.setName("time_" + getTime());
        item.setRepos(0);
        getBookDao().insert(item);
        LogUtil.log("  finished insert op.");
    }

    private void query() {
        // 1.最简单的查询
        List<Book> list = getBookDao().queryBuilder().build().list();
        LogUtil.log("  size = " + (list != null ? list.size() : "null"));
        if (list != null) {
            for (Book item : list) {
                LogUtil.log("  item: " + item.toString());
            }
        }

        // 2.简单查询条件
        List<Book> list2 = getBookDao().queryBuilder()
                .where(BookDao.Properties.Name.eq("Name2"))
                .list();

        // 3.多个查询条件
        QueryBuilder qb3 = getBookDao().queryBuilder();
        qb3.where(BookDao.Properties.Name.eq("Name3"),
                qb3.or(BookDao.Properties.Repos.lt(5), BookDao.Properties.Repos.lt(10)),
                qb3.and(BookDao.Properties.Id.lt(10), BookDao.Properties.Id.gt(5))
        );
        List list3 = qb3.list();

        // 4.复用Query
        Query query4 = getBookDao().queryBuilder()
                .where(BookDao.Properties.Id.eq(100))
                .build();
        query4.setParameter(0, 101);
        List list4 = query4.list();

        // 5.原始SQL语句查询
        Query query5 = getBookDao().queryBuilder().where(new WhereCondition.StringCondition("BOOK_NAME IN (1, 100, 200)")).build();
        List list5 = query5.list();

        List<Category> listCat = getCatDao().loadAll();
        if (listCat != null && listCat.size() > 0) {
            for (Category item : listCat) {
                LogUtil.log("  item : " + item.toString());
            }
        } else {
            LogUtil.log("  cat.list.is." + (listCat == null ? "null" : "empty"));
        }

        SQLiteDatabase db = (SQLiteDatabase) getBookDao().getDatabase().getRawDatabase();
        Cursor c = db.rawQuery("select name, tbl_name from sqlite_master WHERE type='table'", null);
        if (c != null && c.getCount() > 0) {
            LogUtil.log("  count : " + c.getCount());
//            String[] lstColName = c.getColumnNames();
//            for (String n : lstColName) {
//                LogUtil.log("  " + n);
//            }
            c.moveToFirst();
            do {
                LogUtil.log("  name:" + c.getString(0) + " , tbl_name:" + c.getString(1));
            } while(c.moveToNext());
        }
    }
    //endregion

    private Long getTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
