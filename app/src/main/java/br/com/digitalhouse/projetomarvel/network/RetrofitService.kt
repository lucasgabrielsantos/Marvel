package br.com.digitalhouse.projetomarvel.network

import br.com.digitalhouse.projetomarvel.BuildConfig
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.BASE_URL
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.PRIVATE_KEY
import br.com.digitalhouse.projetomarvel.constants.constantsAPI.PUBLIC_KEY
import br.com.digitalhouse.projetomarvel.extensions.Criptografia
import br.com.digitalhouse.projetomarvel.extensions.getTimeStamp
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {
    companion object {


        // Instancia que criaremos do retrofit
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) { // configurações da conexão
                val httpClient = OkHttpClient.Builder()
                httpClient.readTimeout(30, TimeUnit.SECONDS)
                httpClient.connectTimeout(30, TimeUnit.SECONDS)
                httpClient.writeTimeout(30, TimeUnit.SECONDS)
                // Se for Debug habilitamos os logs
                if (BuildConfig.DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    httpClient.addInterceptor(httpLoggingInterceptor)
                    httpClient.addNetworkInterceptor(StethoInterceptor())
                }
                // Inicializamos o retrofit
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()
            }
            return retrofit!!
        }

        // Retornamos a instancia da API criada com o retrofit
        fun getApiService(): MarvelAPI {
            return getRetrofit().create(MarvelAPI::class.java)
        }

        fun getHash(): String? {
            return Criptografia.md5(getTimeStamp() + PRIVATE_KEY + PUBLIC_KEY)
        }
    }
}