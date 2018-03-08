package com.more.greendaodemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.more.greendaodemo.LogUtil;
import com.more.greendaodemo.db.gen.DaoMaster;

import java.util.Calendar;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author [作者]
 * @version [版本号，2018-03-06]
 */
public class MyDbOpenHelper extends DaoMaster.OpenHelper {

    public MyDbOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //super.onUpgrade(db, oldVersion, newVersion);
        // 操作数据库的更新：
        // 1.如果版本跨度较大或者数据库架构变动较大，则采用直接从旧版本到新版本的一次性升级
        // 2.否则，采取渐进式升级
        LogUtil.log("  start." + Calendar.getInstance().getTime().toString());
        /*if (newVersion - oldVersion > 2) {
            // 一次性升级到位
            MigrationHelper.migrate(db, BookDao.class, RelationDao.class);
        } else {
            // 渐进式升级（注意没有break）
            switch (oldVersion) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                default:
            }
        }*/
        switch (oldVersion) {
            case 1:
                LogUtil.log("  upgrade.version_1");
            case 2:
                LogUtil.log("  upgrade.version_2");
            case 3:
                LogUtil.log("  upgrade.version_3");
            case 4:
                LogUtil.log("  upgrade.version_4");
            case 5:
                LogUtil.log("  upgrade.version_5");
            case 6:
                LogUtil.log("  upgrade.version_6");
            case 7:
                LogUtil.log("  upgrade.version_7");
            case 8:
                LogUtil.log("  upgrade.version_8");
            default:
                LogUtil.log("  upgrade.version_??");
        }
        /*// 传入的Dao列表中只需要包含oldVersion数据库中已经创建了的表的Dao
        MigrationHelper.migrate(db, BookDao.class);*/
        LogUtil.log("  end." + Calendar.getInstance().getTime().toString());
    }
}
