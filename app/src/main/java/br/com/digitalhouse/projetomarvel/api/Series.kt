package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class Series {
    @Expose
    var name: String? = null

    @Expose
    var resourceURI: String? = null

}