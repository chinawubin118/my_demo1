package com.lotte.lottelibrary.database;

import android.content.Context;
import android.os.Environment;

import com.litesuits.orm.BuildConfig;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.lotte.lottelibrary.util.LotteUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lotte on 2016/9/1.
 * 自己该改造的,还不知道能不能用呢.....呵呵呵
 */
public class UOrm {
    private static LiteOrm mLiteOrm;

    private static String DB_NAME_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lite/";
    private static String DB_NAME = "my_lite_orm.db";

    private UOrm() {

    }

    public static LiteOrm getInstance(Context mContext) {
        if (null == mLiteOrm) {
            DataBaseConfig config = new DataBaseConfig(mContext);
            config.dbName = DB_NAME_PATH + File.separator + DB_NAME;
            config.dbVersion = 1;
//            config.onUpdateListener = this;
            config.debugged = BuildConfig.DEBUG;
//        可替换为 newCascadeInstance支持级联操作
//        可替换为 newSingleInstance
//        独立操作：使用 LiteOrm 的 single 实例，可与 cascade 方式平滑切换，性能高，仅处理该对象数据，其关系、和关联对象忽略；
//        级联操作：使用 LiteOrm 的 cascade 实例，可与 single 方式平滑切换，全递归，该对象数据，及其关系、和关联对象都被处理；
            mLiteOrm = LiteOrm.newCascadeInstance(config);
        }

        return mLiteOrm;
    }

//    @Override
//    public void onUpdate(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }

    public void save(Object o) {
        if (o == null) return;
        mLiteOrm.save(o);
    }

    public <T> void save(List<T> collection) {
        if (LotteUtil.isEmpty(collection)) return;
        mLiteOrm.save(collection);
    }

    public <T> void delete(Class<T> tClass) {
        if (tClass == null) return;
        mLiteOrm.delete(tClass);
    }

    //select all
    public <T> ArrayList<T> queryAll(Class<T> tClass) {
        if (tClass == null) return null;
        return mLiteOrm.query(tClass);
    }

    //select where
    public <T> ArrayList<T> queryWhere(Class<T> tClass, String columnName, String value) {
        if (tClass == null) return null;
        QueryBuilder qb = new QueryBuilder(tClass)
                .whereEquals(columnName, value);
        return mLiteOrm.query(qb);
    }

    public <T> ArrayList<T> queryWhereAndWhere(Class<T> tClass, String columnName1, String value1, String columnName2, String value2) {
        if (tClass == null) return null;
        QueryBuilder qb = new QueryBuilder(tClass)
                .whereEquals(columnName1, value1)
                .whereAppendAnd()
                .whereEquals(columnName2, value2);
        return mLiteOrm.query(qb);
    }

    //update-更新表
    //update tClass set columnName = value where where = whereValue
    public <T> void update(Class<T> tClass, String columnName, String value, String where, String whereValue) {
        if (tClass == null) return;

        HashMap<String, Object> updateValueMap = new HashMap<String, Object>();
        updateValueMap.put(columnName, value);
        ColumnsValue columnsValue = new ColumnsValue(updateValueMap);
        WhereBuilder whereBuilder = new WhereBuilder(tClass)
                .andEquals(where, whereValue);

        mLiteOrm.update(whereBuilder, columnsValue, ConflictAlgorithm.Fail);
    }

    //update-更新表
    //update tClass set columnName = value where where = whereValue
    public <T> void updateWhereAndWhere(Class<T> tClass, String columnName, String value, String where, String whereValue, String where2, String whereValue2) {
        if (tClass == null) return;

        HashMap<String, Object> updateValueMap = new HashMap<String, Object>();
        updateValueMap.put(columnName, value);
        ColumnsValue columnsValue = new ColumnsValue(updateValueMap);
        WhereBuilder whereBuilder = new WhereBuilder(tClass)
                .andEquals(where, whereValue)
                .andEquals(where2, whereValue2);

        mLiteOrm.update(whereBuilder, columnsValue, ConflictAlgorithm.Fail);
    }

    public <T> void updateWhereAndWhereAndWhere(Class<T> tClass, String columnName, String value, String where, String whereValue
            , String where2, String whereValue2, String where3, String whereValue3) {
        if (tClass == null) return;

        HashMap<String, Object> updateValueMap = new HashMap<String, Object>();
        updateValueMap.put(columnName, value);
        ColumnsValue columnsValue = new ColumnsValue(updateValueMap);
        WhereBuilder whereBuilder = new WhereBuilder(tClass)
                .andEquals(where, whereValue)
                .andEquals(where2, whereValue2)
                .andEquals(where3, whereValue3);

        mLiteOrm.update(whereBuilder, columnsValue, ConflictAlgorithm.Fail);
    }

    /**
     * 删除一条记录
     */
    public <T> void deleteWhere(Class<T> tClass, String columnName, String value) {
        mLiteOrm.delete(new WhereBuilder(tClass)
                .andEquals(columnName, value));
    }

    public ArrayList queryRelation(Class c1, Class c2, ArrayList<String> keyList) {
        if (null == c1 || null == c2 || null == keyList) return null;
        return mLiteOrm.queryRelation(c1, c2, keyList);
    }

    public void insertData(Object object) {
        if (null == object) return;
        mLiteOrm.insert(object);
    }
}
