
package br.com.digitalhouse.projetomarvel.api;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class TextObject {

    @Expose
    private String language;
    @Expose
    private String text;
    @Expose
    private String type;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
