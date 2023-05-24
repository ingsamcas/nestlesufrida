package net.gshp.nestlesufrida.data.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.gshp.nestlesufrida.data.network.core.Client
import net.gshp.nestlesufrida.data.network.core.RetrofitClientFactory
import net.gshp.nestlesufrida.data.network.core.interfaces.APIContext
import net.gshp.nestlesufrida.data.network.login.interfaces.LoginService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAPI (override val context: Context): APIContext {
    private val retrofit: LoginService by RetrofitClientFactory.lazyRetrofit(Client.NESTLE) {
        create(LoginService::class.java)
    }
    suspend fun getToken() : String {
        return withContext(Dispatchers.IO){
            /*

            retrofit.getToken().enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
             */
            val response: String = "eyJraWQiOiJhZXMta2V5LXJlbW8iLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJHU0hQIiwiYXVkIjoiZGV2aWNlcyIsImp0aSI6ImhscWtCSWFWS1Baa3lhejgwcnpDbVEiLCJpYXQiOjE2ODQ4ODQ1MzMsInVzZXJuYW1lIjoiTVhDbGF1ZGlhUyIsIlVVSUQiOiIwYTNhYWZkZWIzZGU0MWQzIiwicGVyc29uIjoiQ2xhdWRpYSBTw6FuY2hleiBEdXLDoW4iLCJhdXRob3JpdGllcyI6WyJyZXN0X3Byb21vdG9yIiwidmlld19pbmRleCIsInZpZXdfZXN0YWRpc3RpY2FzIiwidmlld19nb2RfbW9kZV9tYXAiXSwicm9sZXMiOlsidXNlciIsImthbSJdLCJjb250cm9sIjoibmVzdGxlYWlxYS5nc2hwLWFwcHMuY29tIiwicm9sZUlkcyI6WyIxIiwiOCJdLCJ1c2VySWQiOiIxMTA4NyJ9.jKldCquA3s5JJI1zaZ7ajXM8KaMBoGvaB1oWS0D8zNk"
            response
        }
    }
}