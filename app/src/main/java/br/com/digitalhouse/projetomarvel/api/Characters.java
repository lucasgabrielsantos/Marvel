
package br.com.digitalhouse.projetomarvel.api;

import com.google.gson.annotations.Expose;

import java.util.List;

@SuppressWarnings("unused")
public class Characters {

    @Expose
    private String available;
    @Expose
    private String collectionURI;
    @Expose
    private List<Item> items;
    @Expose
    private String returned;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

}
