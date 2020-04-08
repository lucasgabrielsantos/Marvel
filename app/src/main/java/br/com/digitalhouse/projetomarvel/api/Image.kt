package br.com.digitalhouse.projetomarvel.api

import com.google.gson.annotations.Expose

class Image {
    @Expose
    var extension: String? = null

    @Expose
    var path: String? = null

}