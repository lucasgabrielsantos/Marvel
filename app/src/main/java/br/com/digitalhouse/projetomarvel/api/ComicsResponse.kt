package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class ComicsResponse {
    @Expose
    var attributionHTML: String? = null

    @Expose
    var attributionText: String? = null

    @Expose
    var code: String? = null

    @Expose
    var copyright: String? = null

    @Expose
    lateinit var data: Data

    @Expose
    var etag: String? = null

    @Expose
    var status: String? = null

}