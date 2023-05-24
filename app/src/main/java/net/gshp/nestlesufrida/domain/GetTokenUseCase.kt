package net.gshp.nestlesufrida.domain

import net.gshp.nestlesufrida.data.LoginRepository

class GetTokenUseCase {

    private val repository = LoginRepository()

    suspend operator fun invoke() : String {
        return repository.getTokenRepository()
    }

}