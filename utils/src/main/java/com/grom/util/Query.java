package com.grom.util;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//当前页码
    private int page = 1;
    //每页条数
    private int limit = 100;

    public Query(Map<String, Object> params){
        this.putAll(params);
        //分页参数
        if(params.get("start")!=null) {
            this.page = Integer.parseInt(params.get("start").toString());
        }
        if(params.get("length")!=null) {
            this.limit = Integer.parseInt(params.get("length").toString());
        }
        this.remove("page");
        this.remove("limit");
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
