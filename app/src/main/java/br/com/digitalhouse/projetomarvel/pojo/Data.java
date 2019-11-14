
package br.com.digitalhouse.projetomarvel.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

@SuppressWarnings("unused")
public class Data {

    @Expose
    private String count;
    @Expose
    private String limit;
    @Expose
    private String offset;
    @Expose
    private List<Result> results;
    @Expose
    private String total;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
