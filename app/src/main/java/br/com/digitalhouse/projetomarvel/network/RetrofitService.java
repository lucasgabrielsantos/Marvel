package br.com.digitalhouse.projetomarvel.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import br.com.digitalhouse.projetomarvel.BuildConfig;
import br.com.digitalhouse.projetomarvel.util.Criptografia;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel.PRIVATE_KEY;
import static br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel.PUBLIC_KEY;

public class RetrofitService {

    // Url base da api
    private static final String BASE_URL = "https://gateway.marvel.com:443/v1/public/";

    // Instancia que criaremos do retrofit
    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {

        if (retrofit == null) {

            // configurações da conexão
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);

            // Se for Debug habilitamos os logs
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(httpLoggingInterceptor);
                httpClient.addNetworkInterceptor(new StethoInterceptor());
            }

            // Inicializamos o retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    // Retornamos a instancia da API criada com o retrofit
    public static MarvelAPI getApiService() {
        return getRetrofit().create(MarvelAPI.class);
    }

    public  static String getTimeStamp() {
        Long ts = Calendar.getInstance().getTimeInMillis() / 1000;

        return ts.toString();
    }

    public static String getHash(){
        return Criptografia.md5( getTimeStamp() + PRIVATE_KEY + PUBLIC_KEY);

    }
}