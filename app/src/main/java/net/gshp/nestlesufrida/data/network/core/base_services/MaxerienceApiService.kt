package net.gshp.nestlesufrida.data.network.core.base_services

import android.content.Context
import com.google.gson.GsonBuilder
import net.gshp.nestlesufrida.data.network.core.interfaces.RetrofitAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

class MaxerienceApiService(private val context: Context) : RetrofitAPIService {
    companion object {
        private const val BASE_URL = "services/"
    }

    override fun getRetrofit(): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            //.addInterceptor(MaxerienceSecurityInterceptor(context.applicationContext))
            //.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://maxerience-servicesqa.gshp-apps.com/"/*BuildConfig.MAXERIENCE_SERVICES_URL*/ + BASE_URL)
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
}