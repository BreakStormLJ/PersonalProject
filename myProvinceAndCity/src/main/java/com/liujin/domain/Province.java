package com.liujin.domain;

import java.io.Serializable;

/**
 * @program: myDay14
 * @description:
 * @author: liujin
 * @create: 2019-07-31 17:12
 **/
public class Province implements Serializable {
    private Integer id;
    private String name;

    public Province() {
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

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
