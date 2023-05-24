package net.gshp.nestlesufrida.data.network.core.base_services

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import net.gshp.nestlesufrida.data.network.core.interfaces.RetrofitAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

class NestleApiService(private val context: Context): RetrofitAPIService {

    override fun getRetrofit(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            //.addInterceptor(SecurityInterceptor(context))
            //.addInterceptor(AuthorizationInterceptor(context))
            //.addInterceptor((HttpLoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://nestleaiqa.gshp-apps.com"/*context.getString(R.string.network_ip)*/)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}