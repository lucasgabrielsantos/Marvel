package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class Item {
    @Expose
    var name: String? = null

    @Expose
    var resourceURI: String? = null

    @Expose
    var role: String? = null

    @Expose
    var type: String? = null

}