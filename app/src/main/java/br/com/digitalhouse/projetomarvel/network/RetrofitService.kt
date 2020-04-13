package br.com.digitalhouse.projetomarvel.network

import br.com.digitalhouse.projetomarvel.BuildConfig
import br.com.digitalhouse.projetomarvel.constants.ConstantsAPI.BASE_URL
import br.com.digitalhouse.projetomarvel.constants.ConstantsAPI.PRIVATE_KEY
import br.com.digitalhouse.projetomarvel.constants.ConstantsAPI.PUBLIC_KEY
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


        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                val httpClient = OkHttpClient.Builder()
                httpClient.readTimeout(30, TimeUnit.SECONDS)
                httpClient.connectTimeout(30, TimeUnit.SECONDS)
                httpClient.writeTimeout(30, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    httpClient.addInterceptor(httpLoggingInterceptor)
                    httpClient.addNetworkInterceptor(StethoInterceptor())
                }
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()
            }
            return retrofit!!
        }

        fun getApiService(): MarvelAPI {
            return getRetrofit().create(MarvelAPI::class.java)
        }

        fun getHash(): String? {
            return Criptografia.md5(getTimeStamp() + PRIVATE_KEY + PUBLIC_KEY)
        }
    }
}