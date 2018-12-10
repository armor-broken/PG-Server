package com.example.model;

/**
 * 任务类型实体类
 */
public class CategorieInfo {
    public int getId() {
        return id;
    }

    public String getCat() {
        return cat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    private int id;
    private String cat;
}
