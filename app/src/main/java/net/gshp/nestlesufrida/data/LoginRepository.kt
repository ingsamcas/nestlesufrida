package net.gshp.nestlesufrida.data

import net.gshp.nestlesufrida.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun getTokenRepository() : String {
        return api.getToken()
    }

}