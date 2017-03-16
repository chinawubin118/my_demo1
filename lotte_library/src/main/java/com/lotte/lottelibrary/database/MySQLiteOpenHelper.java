package com.lotte.lottelibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wubin on 2016/3/2.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 数据库文件创建成功后调用
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //naire_record,字段(主键:id(问卷id) name(问卷名称) )
        String sql = "CREATE TABLE naire_record(id TEXT PRIMARY KEY,nickname TEXT,headerPath TEXT)";
        db.execSQL(sql);
    }

    /**
     * 数据文件需要更新时调用(比如App版本升级时)
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
