package com.kuciaapp.canbecheaperkmp.viewmodel

import com.kuciaapp.canbecheaperkmp.model.domain.User
import com.kuciaapp.canbecheaperkmp.model.dto.request.ConfirmAccountRequest
import com.kuciaapp.canbecheaperkmp.model.repository.UserRepository
import com.kuciaapp.canbecheaperkmp.utility.UserSession
import com.kuciaapp.canbecheaperkmp.utility.isValidEmail
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.core.component.KoinComponent
import kotlin.text.isBlank
import kotlin.text.isNullOrBlank
import kotlin.text.toInt
import kotlin.text.toIntOrNull
import kotlin.text.trim
import kotlin.toString



class RegisterViewModel (private val userRespository : UserRepository,
                         private val userSession: UserSession
    ) : BaseViewModel()  {
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())


    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    private val _uiStateCode = MutableStateFlow(RegisterUiState())
    val uiStateCode: StateFlow<RegisterUiState> = _uiStateCode


    private val _code = MutableStateFlow("")
    val code: StateFlow<String> = _code

    fun getShowDialog(): Boolean = showDialog.value
    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    fun updateCode(value: String) {
        _code.value = value
    }
    fun getCode(): String = _code.value.trim()


    fun updateAge(value: String) {
        _age.value = value
    }
    fun getAge(): String = _age.value

    fun updateName(value: String?) {
        _name.value = value.toString().trim()
    }
    fun getName(): String = _name.value

    fun updatePassword(value: String) {
        _password.value = value.trim()
    }
    fun getPassword(): String = _password.value

    fun updateEmail(value: String) {
       _email.value = value.trim()
    }
    fun getEmail(): String = _email.value

    private val navigationEvent = MutableSharedFlow<Boolean>()
    val NavigationEvent = navigationEvent.asSharedFlow()





        fun register() {
            viewModelScope.launch {
                try {
                    val ageInt = getAge().toIntOrNull()
                    when{
                        getEmail().isBlank() -> _uiState.value = RegisterUiState(message = "Podaj adres email", isSuccess = false)
                        getPassword().isBlank() ->  _uiState.value = RegisterUiState(message = "Podaj hasło", isSuccess = false)
                        getName().isBlank()  || getName().length > 50 ->   _uiState.value = RegisterUiState(message = "Podaj imię", isSuccess = false)
                        ageInt == null || ageInt  !in 0..100  ->  _uiState.value = RegisterUiState(message = "Podaj poprawny wiek", isSuccess = false)
                        !isValidEmail(getEmail()) -> _uiState.value = RegisterUiState(message = "Podaj poprawny email", isLoading = false, isSuccess = false)


                        else -> {

                            _uiState.value = RegisterUiState(isLoading = true)

                            var user = User(
                                0,
                                getEmail(),
                                getName(),
                                getAge().toInt(),
                                getPassword(),
                                null
                            )
                            var result = userRespository.register(user)
                                val body = result
                                if(body?.success == true){
                                    _uiState.value = RegisterUiState(message = body.message, isLoading = false, isSuccess = true)
                                    _uiState.value.clearMessage()
                                    setShowDialog(true)

                            }

                        }
                    }

                }
                catch (e : Exception){
                    println("Fail registration please try again: ${e.message ?: " "}" + e)
                    _uiState.value = RegisterUiState(message = "Coś poszło nie tak! Spróbuj ponownie ${e.message ?: " "}", isLoading = false, isSuccess = false)
                }
            }
        }

        fun confirmUser()  {
            try{
                viewModelScope.launch {

                        val codeInt = getCode().toIntOrNull()
                        if(getEmail().isNullOrBlank() || codeInt == null){
                            _uiStateCode.value = RegisterUiState(message = "Podaj email i poprawny kod", isLoading = false, isSuccess = false )
                            return@launch
                        }

                        _uiStateCode.value = RegisterUiState(isLoading = true)
                        var confirmAccountRequest = ConfirmAccountRequest(
                            getEmail(),
                            getCode().toInt())

                        val result = userRespository.confirmCode(confirmAccountRequest)
                        _uiStateCode.value = RegisterUiState(message = result?.message, isLoading = false, isSuccess = true )
                        userSession.saveUserId(result?.id)
                        navigationEvent.emit(true)

//                        if(result.isSuccessful){
//                           val body = result.body()
//
//                            if (body?.success == true) {
//
//                            } else {
//                                _uiStateCode.value = RegisterUiState(message = body?.message ?: "Nieprawidłowa odpowiedź serwera", isLoading = false, isSuccess = false )
//                            }
//
//                        }
//                         else _uiStateCode.value = RegisterUiState(message = "Coś poszło nie tak! Spróbuj ponownie", isLoading = false, isSuccess = false )

                }
            }
            catch (e : Exception){
                _uiStateCode.value = RegisterUiState(message = "Coś poszło nie tak! Spróbuj ponownie ${e.message ?: ""}", isLoading = false, isSuccess = false )
                println( "Fail registration please try again  ${e.message ?: "Spróbuj ponownie"}" + e)
            }
        }



}

data class RegisterUiState(
    val isLoading : Boolean = false,
    val message : String? = null,
    val isSuccess: Boolean = false
){
    fun clearMessage() : RegisterUiState{
        return copy(message = null)
    }
}


