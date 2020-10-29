package com.cn.weixuan.util;

public class PageUtil<T> {
    private Integer pageIndex;
    private Integer PageSize;
    private T form;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public T getForm() {
        return form;
    }

    public void setForm(T form) {
        this.form = form;
    }
}
