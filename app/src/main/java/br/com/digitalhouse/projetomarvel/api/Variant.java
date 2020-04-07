
package br.com.digitalhouse.projetomarvel.api;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Variant {

    @Expose
    private String name;
    @Expose
    private String resourceURI;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

}
