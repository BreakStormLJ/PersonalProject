package com.liujin.domain;

import java.io.Serializable;

/**
 * @program: myDay14
 * @description:
 * @author: liujin
 * @create: 2019-07-31 17:25
 **/
public class City implements Serializable {
    private Integer id;
    private String name;
    private Integer pid;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                '}';
    }
}
