package com.hexhoc.springbootblog.common.util;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

    private int totalCount;
    private int pageSize;
    private int totalPages;
    private int currPage;
    private List<?> list;

    /**
     *
     * @param list List data
     * @param totalCount total articles
     * @param pageSize Number of records per page (capacity)
     * @param currPage current page number
     */
    public PageResult(List<?> list, int totalCount, int pageSize, int currPage){
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
    }

    ////////////////////////////
    //GETTER AND SETTER
    ////////////////////////////

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
