package com.more.greendaodemo.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author [作者]
 * @version [版本号，2018-03-05]
 */
@Entity
public class Book {
    @Id
    private Long id;

    @Property(nameInDb = "BOOK_NAME")
    private String name;

    @NotNull
    private int repos;

    @NotNull
    private int remark;

    @Property
    private String note1;

    @Transient // @Transient 标注，表示该字段不会被数据库持久化写进数据库
    private int tempUsageCount; // not persisted

    @Generated(hash = 687176295)
    public Book(Long id, String name, int repos, int remark, String note1) {
        this.id = id;
        this.name = name;
        this.repos = repos;
        this.remark = remark;
        this.note1 = note1;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepos() {
        return this.repos;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }

    public int getRemark() {
        return this.remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", repos=" + repos +
                ", remark=" + remark +
                ", note1='" + note1 + '\'' +
                ", tempUsageCount=" + tempUsageCount +
                '}';
    }

    public String getNote1() {
        return this.note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }
}
