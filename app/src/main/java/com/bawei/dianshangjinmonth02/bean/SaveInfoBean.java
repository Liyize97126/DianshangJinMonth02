package com.bawei.dianshangjinmonth02.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 数据库保存
 */
@Entity
public class SaveInfoBean {
    @Id
    private long id;
    private String jsonData;
    @Generated(hash = 1485879159)
    public SaveInfoBean(long id, String jsonData) {
        this.id = id;
        this.jsonData = jsonData;
    }
    @Generated(hash = 319238435)
    public SaveInfoBean() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getJsonData() {
        return jsonData;
    }
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
