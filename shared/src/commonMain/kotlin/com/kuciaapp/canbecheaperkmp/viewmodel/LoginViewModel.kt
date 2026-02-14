package com.kuciaapp.canbecheaperkmp.viewmodel

import com.kuciaapp.canbecheaperkmp.model.dto.request.LoginRequest
import com.kuciaapp.canbecheaperkmp.model.repository.UserRepository
import com.kuciaapp.canbecheaperkmp.utility.UserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.text.isBlank


class LoginViewModel (private val userRespository : UserRepository,
                            private val userSession: UserSession)  : BaseViewModel()  {
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val navigationEvent = MutableSharedFlow<Boolean>()
    val NavigationEvent = navigationEvent.asSharedFlow()


    fun setPassword(value: String) {
        _password.value = value
    }

    fun setEmail(value: String) {
        _email.value = value
    }


    fun forgotPassword() {
        viewModelScope.launch {

        }
    }




     fun login() {
         viewModelScope.launch {

             if (_email.value.isBlank() || _password.value.isBlank()) {
                 _uiState.value = LoginUiState(
                     isLoading = false,
                     message = "Wypełnij wszystkie pola"
                 )
                 return@launch
             }

             try {
                 _uiState.value = LoginUiState(isLoading = true)
                 val loginRequest = LoginRequest(email.value, password.value)
                 var result = userRespository.login(loginRequest)
                 userSession.saveUserId(result?.id)
                 navigationEvent.emit(true)


//                  if (result.isSuccessful){
//                      result.body()?. let {
//                          userSession.saveUserId(it?.id)
//                          navigationEvent.emit(true)
//                      } ?:{
//                          _uiState.value = LoginUiState(
//                              isLoading = false,
//                              message = "Błąd: brak danych użytkownika"
//                          )
//                      }
//                  } else {
//                      _uiState.value = LoginUiState(
//                          isLoading = false,
//                          message = "Nieprawidłowy email lub hasło"
//                      )
//                  }

             } catch(e: Exception) {
                 println("Error login "+ e)
                 _uiState.value = LoginUiState(
                     isLoading = false,
                     message = "Wystąpił błąd: ${e.message ?: "Spróbuj ponownie"}"
                 )
             }
         }
    }


    fun clearMessage() {
        _uiState.value = LoginUiState(message = "")
    }


}

data class LoginUiState(
    val isLoading : Boolean = false,
    val message : String? = null,
)