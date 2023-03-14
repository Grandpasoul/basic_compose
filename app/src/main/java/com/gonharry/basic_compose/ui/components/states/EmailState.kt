package com.gonharry.basic_compose.ui.components.states

class EmailState: TextFieldState(
    validator = { email -> isEmailValid(email) }, //::isEmailValid
    errorMessage = { email -> emailErrorMessage(email) }
)

private fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun emailErrorMessage(email: String) = "$email no es válido"