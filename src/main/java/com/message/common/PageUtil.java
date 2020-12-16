package com.message.common;

import java.beans.PropertyVetoException;
import java.util.List;

public class PageUtil<T> {

    private Integer pageNum;//当前页

    private Integer pageSize=5;//每页条数

    private Integer total;//总条数

    private Integer pages;//总页数

    private Integer prePage = 1;//上一页

    private Integer nextPage;//下一页

    private  Integer statrNum;//起始行数

    private Integer finalPage;//最后一页

    private List<T> result;//结果集

    public Integer getFinalPage() {
        return finalPage;
    }

    public void setFinalPage(Integer finalPage) {
        this.finalPage = finalPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        pages = total % pageSize == 0?total/pageSize : total/pageSize +1;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrePage() {
        return pageNum == 1 ? 1 : pageNum-1;

    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return pageNum == pages ?pages:pageNum + 1;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getStatrNum() {
        return (pageNum -1)*pageSize;
    }

    public void setStatrNum(Integer statrNum) {
        this.statrNum = statrNum;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

}
