package com.example.model;
import java.util.List;

/**
 * 台站分页信息封装类
 */
public class Response {
    private Long total;
    private List list;

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setList(List list) {
        this.list = list;
    }

    public List getList() {
        return list;
    }
}
