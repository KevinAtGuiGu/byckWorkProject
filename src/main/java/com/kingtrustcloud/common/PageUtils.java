package com.kingtrustcloud.common;


import java.io.Serializable;
import java.util.List;

/**
 * @Author Shuaige 77509028@qq.com
 */
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数（查询数据库）
     */
    private int totalCount;
    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 总页数（totalCount/pageSize）
     */
    private int totalPage;
    /**
     * 当前页数(从页面传入)
     */
    private int currPage;
    /**
     * 开始索引
     */
    private int startIndex;
    /**
     * 每页要显示的数据
     */
    private List<?> rows;
    /**
     *开始页
     */
    private int start;
    /**
     * 结束页
     */
    private int end;

    public PageUtils(List<?> list, int totalCount) {
        this.rows = list;
        this.totalCount = totalCount;
    }

    /**
     * 分页
     */
    public PageUtils() {
        this.pageSize = 10;
        this.currPage = 1;
    }

    /**
     * 分页
     *
     * @param currPage   当前页数
     * @param pageSize   每页记录数
     * @param totalCount 总记录数
     */
    public PageUtils(int currPage, int pageSize, int totalCount) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        //总页数
       this.totalPage = getTotalPage();
        //开始索引
        this.startIndex = getStartIndex();
        this.start = 1;
        this.end = 5;
        if (totalPage < 5){
            this.end = this.totalPage;
        }else {
            this.start = currPage -2;
            this.end = currPage + 2;
            if (start < 0){
                this.start = 1;
                this.end = 5;
            }
            if (end > this.totalPage){
                this.end = totalPage;
                this.start = end -5;
            }
        }
    }

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
        this.rows = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> list) {
        this.rows = list;
    }

    /**
     * 页长（每页显示的记录条数）
     */
    public int getPageSize() {
        if (pageSize <= 0) {
            return 10;
        } else {
            return pageSize;
        }
    }

    /**
     * 页长（每页显示的记录条数）
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 总页数（由总条数/页长计算得到）
     */
    public int getTotalPage() {
        if (totalPage == 0) {
            int i = getTotalCount() % getPageSize();
            if (i == 0) {
                return getTotalCount() / getPageSize();
            }
            return getTotalCount() / getPageSize() + 1;
        }
        return totalPage;
    }

    /**
     * 总页数（由总条数/页长计算得到）
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 当前查询的页码(显示的第几页)
     */
    public int getCurrPage() {
        return currPage;
    }

    /**
     * 当前查询的页码(显示的第几页)
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotal() {
        return this.totalCount;
    }

    /**
     * 计算分页查询逻辑中起始的行号
     *
     * @return
     */
    public int getStartIndex() {
        return (currPage - 1) * pageSize;
    }
}
