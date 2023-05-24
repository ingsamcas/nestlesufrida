package net.gshp.nestlesufrida.data.network.core

import android.content.Context
import net.gshp.nestlesufrida.data.network.core.base_services.MaxerienceApiService
import net.gshp.nestlesufrida.data.network.core.base_services.NestleApiService
import net.gshp.nestlesufrida.data.network.core.interfaces.APIContext
import retrofit2.Retrofit
import kotlin.reflect.KProperty

enum class Client{NESTLE, MAXERIENCE}
class RetrofitClientFactory private constructor(){
    companion object{
        val instance = RetrofitClientFactory()
    }

    private val clients = mutableMapOf<Client, Retrofit>()

    fun getClient(client: Client, context: Context /*= Contextapp.getContext()*/): Retrofit {
        if (clients[client] == null)
            clients[client] = when (client) {
                Client.NESTLE -> NestleApiService(context).getRetrofit()
                Client.MAXERIENCE -> MaxerienceApiService(context).getRetrofit()
            }
        return clients[client] ?: throw Error("Can't create ${client.name} client")
    }

    class lazyRetrofit<T>(val client: Client, private val scope: Retrofit.() -> T){
        var cache: T? = null
        operator fun getValue(thisRef: APIContext, property: KProperty<*>): T {
            if(cache == null)
                cache = scope.invoke(instance.getClient(client, thisRef.context))
            return cache?: throw Error("lazyRetrofit can´t fetch value")
        }

    }
}