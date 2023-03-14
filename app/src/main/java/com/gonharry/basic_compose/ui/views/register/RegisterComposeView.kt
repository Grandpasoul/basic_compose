package com.gonharry.basic_compose.ui.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonharry.basic_compose.ui.theme.Purple200
import com.gonharry.basic_compose.ui.views.register.RegisterViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterComposeView(rvm: RegisterViewModel = viewModel()) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .shadow(8.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "User Registration",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )

            ShowUserName(rvm)
            ShowEmail(rvm)
            ShowPassword(rvm)
            ShowConfirmPassword(rvm)

            ShowButton(rvm, context)

            ShowPlanText(rvm)
        }
    }
}

@Composable
fun ShowPlanText(rvm: RegisterViewModel) {
    Text(
        text = "name: ${rvm.userName.text}",
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
    )
    Text(
        text = "email: ${rvm.email.text}",
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
    Text(
        text = "password: ${rvm.password.text}",
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
    Text(
        text = "confirmPass: ${rvm.confirmPassword.text}",
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
private fun ShowButton(
    rvm: RegisterViewModel,
    context: Context
) {
    Button(onClick = {
        rvm.register()
        Toast.makeText(
            context,
            "${rvm.userName.text} : ${rvm.email.text} : ${rvm.password.text} : ${rvm.confirmPassword.text} ",
            Toast.LENGTH_SHORT
        ).show()
    }, enabled = rvm.isEnabledRegisterButton.value) {
        Text("Registrarme")
    }
}

@Composable
private fun ShowUserName(rvm: RegisterViewModel) {
    Column(modifier = Modifier.padding(5.dp)) {
        TextField(
            value = rvm.userName.text,
            onValueChange = {
                rvm.userName.text = it
                rvm.validateUserName()
            },
            isError = rvm.errMsgName.value != null,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("Nombre") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, keyboardType = KeyboardType.Text)
        )

        rvm.errMsgName.value?.let {
            ErrorField(it)
        }
    }
}

@Composable
private fun ShowEmail(rvm: RegisterViewModel) {
    Column(modifier = Modifier.padding(5.dp)) {
        TextField(
            value = rvm.email.text,
            onValueChange = {
                rvm.email.text = it
                rvm.validateEmail()
            },
            isError = rvm.errMsgEmail.value != null,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, keyboardType = KeyboardType.Email),
        )

        rvm.errMsgEmail.value?.let {
            ErrorField(it)
        }
    }
}

@Composable
private fun ShowPassword(rvm: RegisterViewModel) {
    val showPassword = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(5.dp)) {
        TextField(
            value = rvm.password.text,
            onValueChange = {
                rvm.password.text = it
                rvm.validatePassword()

            },
            isError = rvm.errMsgPass.value != null,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPassword.value) {
                    Pair(
                        Icons.Rounded.Lock,
                        Purple200
                    )
                } else {
                    Pair(
                        Icons.Filled.Lock,
                        Purple200
                    )
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
        )

        rvm.errMsgPass.value?.let {
            ErrorField(it)
        }
    }
}

@Composable
private fun ShowConfirmPassword(rvm: RegisterViewModel) {
    val showPassword = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(5.dp)) {
        TextField(
            value = rvm.confirmPassword.text,
            onValueChange = {
                rvm.confirmPassword.text = it
                rvm.validateConfirmPassword()
            },
            isError = rvm.errMsgPassB.value != null,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("Confirmar Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPassword.value) {
                    Pair(
                        Icons.Rounded.Lock,
                        Purple200
                    )
                } else {
                    Pair(
                        Icons.Filled.Lock,
                        Purple200
                    )
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
        )

        rvm.errMsgPassB.value?.let {
          ErrorField(it)
        }
    }
}


@Composable
private fun ErrorField(error: String){
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = error,
        style = TextStyle(
            color = MaterialTheme.colors.error
        )
    )
}
