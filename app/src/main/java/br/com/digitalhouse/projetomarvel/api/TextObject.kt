package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class TextObject {
    @Expose
    var language: String? = null

    @Expose
    var text: String? = null

    @Expose
    var type: String? = null

}