package com.liujin.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 */

public class PageBean<T> implements Serializable {
    private Integer totalCount; // 总记录数
    private Integer totalPage ; // 总页码
    private List<T> list ; // 每页的数据
    private Integer currentPage ; //当前页码
    private Integer rows;//每页显示的记录数
    private Integer beginPage; //起始页
    private Integer endPage; //尾页

    public PageBean() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(Integer beginPage) {
        this.beginPage = beginPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                '}';
    }
}
