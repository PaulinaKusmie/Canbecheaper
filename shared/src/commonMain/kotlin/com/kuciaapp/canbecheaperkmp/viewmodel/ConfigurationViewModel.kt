package com.kuciaapp.canbecheaperkmp.viewmodel


import com.kuciaapp.canbecheaperkmp.model.repository.UserRepository
import com.kuciaapp.canbecheaperkmp.utility.UserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class ConfigurationViewModel(
    private val repo: UserRepository,
    private val userSession: UserSession
) : BaseViewModel()  {
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private var userId: Int = 0

    fun getShowDialog(): Boolean = showDialog.value

    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    init {
        viewModelScope.launch {
            userId = userSession.getUserIdOnce() ?: 0
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            try {
                val success = repo.deleteUser(userId)
                if (success) {
                    //ToastManager.showToast("Operacja zakończona sukcesem! Zapraszamy ponownie!")
                    userSession.clearUserId()
                } else {
                    //ToastManager.showToast("Coś poszło nie tak! Spróbuj ponownie")
                }
            } catch (e: Exception) {
                println("Error: Fail delete user $e")
            }
        }
    }
}
