package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class Data {
    @Expose
    var count: String? = null

    @Expose
    var limit: String? = null

    @Expose
    var offset: String? = null

    @Expose
    var results: List<Result>? = null

    @Expose
    var total: String? = null

}