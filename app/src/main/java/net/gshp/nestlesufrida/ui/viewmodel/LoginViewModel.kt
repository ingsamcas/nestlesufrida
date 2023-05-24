package net.gshp.nestlesufrida.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.gshp.nestlesufrida.domain.GetTokenUseCase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.gshp.nestlesufrida.data.model.LoginModel

class LoginViewModel : ViewModel() {

    val loginModel = MutableLiveData<String>()
    var getTokenUseCase = GetTokenUseCase()


    fun login(etUser : String, etPass: String){
        viewModelScope.launch{
            val result = getTokenUseCase()
            if(!result.isNullOrEmpty()){
                loginModel.postValue(result)
            }
        }
    }


}