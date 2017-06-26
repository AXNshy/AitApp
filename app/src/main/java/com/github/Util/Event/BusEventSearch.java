package com.github.Util.Event;

/**
 * Created by axnshy on 2017/6/23.
 */

public class BusEventSearch {
    private String qualifier;
    private String sort = "match";
    private String order = "desc";

    public BusEventSearch(String qualifier, String sort, String order) {
        this.qualifier = qualifier;
        this.sort = sort;
        this.order = order;
    }


    public String getQualifier() {
        return qualifier;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }
}
