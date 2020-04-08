package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class Collection {
    @Expose
    var name: String? = null

    @Expose
    var resourceURI: String? = null

}