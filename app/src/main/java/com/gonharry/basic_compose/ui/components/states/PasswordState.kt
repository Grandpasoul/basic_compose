package com.gonharry.basic_compose.ui.components.states

class PasswordState: TextFieldState(
    validator = { password -> isPasswordValid(password) },
    errorMessage = { password -> passwordErrorMessage(password) }
)

private fun isPasswordValid(password: String): Boolean {
    return password.length > 6
}

private fun passwordErrorMessage(password: String) = "$password debe tener más de 6 caracteres"