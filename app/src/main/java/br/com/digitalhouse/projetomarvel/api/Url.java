
package br.com.digitalhouse.projetomarvel.api;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Url {

    @Expose
    private String type;
    @Expose
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
