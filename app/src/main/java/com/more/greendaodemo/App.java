package com.more.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.more.greendaodemo.db.MyDbOpenHelper;
import com.more.greendaodemo.db.gen.DaoMaster;
import com.more.greendaodemo.db.gen.DaoSession;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author [作者]
 * @version [版本号，2018-03-05]
 */
public class App extends Application {

    //region - single App -
    private static App app;

    public static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
    //endregion

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    //region - single Dao -
    //private DaoMaster.DevOpenHelper dbHelper;
    private MyDbOpenHelper dbHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    //
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            LogUtil.log("  init db ...");
            dbHelper = new MyDbOpenHelper(getApp(), "book.db", null);
            if (db != null) {
                if (db.isOpen()) db.close();
            }
            db = dbHelper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
        LogUtil.log("  return daoSession ...");
        return daoSession;
    }

    public void closeDb() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (daoMaster != null) daoMaster = null;
        if (db != null) {
            if (db.isOpen()) db.close();
            db = null;
        }
        if (dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }
        LogUtil.log("  close db ...");
    }
    //endregion

}
