package com.gonharry.basic_compose.ui.views.register

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gonharry.basic_compose.model.RegisterUser
import com.gonharry.basic_compose.ui.components.states.EmailState
import com.gonharry.basic_compose.ui.components.states.NameState
import com.gonharry.basic_compose.ui.components.states.PasswordState

class RegisterViewModel: ViewModel() {

    var regUser: RegisterUser = RegisterUser()

    var userName: NameState = NameState()
    var errMsgName: MutableState<String?> = mutableStateOf(null)

    var email: EmailState = EmailState()
    var errMsgEmail: MutableState<String?> = mutableStateOf(null)

    var password: PasswordState = PasswordState()
    var errMsgPass: MutableState<String?> = mutableStateOf(null)

    var confirmPassword: PasswordState = PasswordState()
    var errMsgPassB: MutableState<String?> = mutableStateOf(null)

    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)

    init {}

    fun validateUserName() {
        userName.validate()
        errMsgName.value = userName.error
        shouldEnabledRegisterButton()
    }

    fun validateEmail() {
        email.validate()
        errMsgEmail.value = email.error
        shouldEnabledRegisterButton()
    }

    fun validatePassword() {
        password.validate()
        errMsgPass.value = password.error
        validateConfirmPassword()
        shouldEnabledRegisterButton()
    }

    fun validateConfirmPassword() {
        if(confirmPassword.text == password.text){
            confirmPassword.validate()
            errMsgPassB.value = password.error
        } else {
            errMsgPassB.value = "Las contraseñas no coinciden"
        }
        shouldEnabledRegisterButton()
    }

    private fun shouldEnabledRegisterButton() {
        isEnabledRegisterButton.value = errMsgName.value.isNullOrBlank()
                && userName.text.isNotEmpty()
                && errMsgEmail.value.isNullOrBlank()
                && email.text.isNotEmpty()
                && errMsgPass.value.isNullOrBlank()
                && password.text.isNotEmpty()
                && errMsgPassB.value.isNullOrBlank()
                && confirmPassword.text.isNotEmpty()
    }

    fun register() {
        regUser.name = userName.text
        regUser.email = email.text
        regUser.password = password.text
        regUser.confirmPassword = confirmPassword.text

        Log.d("username", userName.text)
        Log.d("email", email.text)
        Log.d("password", password.text)
        Log.d("confirmPassword", confirmPassword.text)
        Log.d("Register", regUser.toString())
    }

}