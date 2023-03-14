package com.gonharry.basic_compose.model

data class RegisterUser(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    //var rut: String = "",
    //var phone: String = "",
    var confirmPassword: String = ""
)