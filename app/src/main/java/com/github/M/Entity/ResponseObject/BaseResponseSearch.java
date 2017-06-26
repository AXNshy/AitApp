package com.github.M.Entity.ResponseObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by axnshy on 2017/6/23.
 */

public class BaseResponseSearch<T> {
    /*total_count": 5629,
            "incomplete_results": false,
            "items":*/

    @SerializedName("total_count")
    int totalCount;

    @SerializedName("incomplete_results")
    boolean incompleteResults;

    List<T> items;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<T> getItems() {
        return items;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
