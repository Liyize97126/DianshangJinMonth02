package com.bawei.dianshangjinmonth02.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bawei.dianshangjinmonth02.bean.SaveInfoBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SAVE_INFO_BEAN".
*/
public class SaveInfoBeanDao extends AbstractDao<SaveInfoBean, Long> {

    public static final String TABLENAME = "SAVE_INFO_BEAN";

    /**
     * Properties of entity SaveInfoBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property JsonData = new Property(1, String.class, "jsonData", false, "JSON_DATA");
    }


    public SaveInfoBeanDao(DaoConfig config) {
        super(config);
    }
    
    public SaveInfoBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SAVE_INFO_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"JSON_DATA\" TEXT);"); // 1: jsonData
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SAVE_INFO_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SaveInfoBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String jsonData = entity.getJsonData();
        if (jsonData != null) {
            stmt.bindString(2, jsonData);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SaveInfoBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String jsonData = entity.getJsonData();
        if (jsonData != null) {
            stmt.bindString(2, jsonData);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public SaveInfoBean readEntity(Cursor cursor, int offset) {
        SaveInfoBean entity = new SaveInfoBean( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // jsonData
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SaveInfoBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setJsonData(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SaveInfoBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SaveInfoBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SaveInfoBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
