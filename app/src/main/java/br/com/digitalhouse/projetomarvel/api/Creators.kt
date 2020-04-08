package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class Creators {
    @Expose
    var available: String? = null

    @Expose
    var collectionURI: String? = null

    @Expose
    var items: List<Item>? = null

    @Expose
    var returned: String? = null

}