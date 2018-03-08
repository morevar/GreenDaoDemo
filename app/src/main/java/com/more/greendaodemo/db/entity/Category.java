package com.more.greendaodemo.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author [作者]
 * @version [版本号，2018-03-06]
 */
@Entity
public class Category {
    @Property
    private String catName;

    @Property
    private String catType;

    @Generated(hash = 2051412145)
    public Category(String catName, String catType) {
        this.catName = catName;
        this.catType = catType;
    }

    @Generated(hash = 1150634039)
    public Category() {
    }

    public String getCatName() {
        return this.catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatType() {
        return this.catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catName='" + catName + '\'' +
                ", catType='" + catType + '\'' +
                '}';
    }
}
