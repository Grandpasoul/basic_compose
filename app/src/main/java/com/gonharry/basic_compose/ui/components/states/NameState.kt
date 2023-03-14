package com.gonharry.basic_compose.ui.components.states

class NameState: TextFieldState(
    validator = { name -> isNameValid(name) },
    errorMessage = { name -> nameErrorMessage(name) }
)

private fun isNameValid(name: String): Boolean {
    return name.length >= 3
}

private fun nameErrorMessage(name: String) = "$name debe tener 3 o más caracteres"