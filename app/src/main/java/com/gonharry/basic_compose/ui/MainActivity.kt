package com.gonharry.basic_compose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gonharry.basic_compose.R
import com.gonharry.basic_compose.ui.components.CustomButton
import com.gonharry.basic_compose.ui.theme.Basic_composeTheme
import com.gonharry.basic_compose.ui.theme.Typography
import com.gonharry.basic_compose.ui.views.register.RegisterActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current

            Basic_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Gray)
                    ) {
                        CustomButton(
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .fillMaxWidth(),
                            onClick = {
                                val intent = Intent(context, RegisterActivity::class.java)
                                context.startActivity(intent)
                            }) {
                            Text(text = "Ir a Registro")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    var name by remember { mutableStateOf("") }
    val maxLenght = 10

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ){
        Text(
            modifier = Modifier
                .align(Alignment.TopStart),
            text = "Este texto estará a la izq top"
        )
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.Center),
            value = name,
            onValueChange = {
                name = if(it.length <= maxLenght){
                    it
                } else {
                    it.take(maxLenght)
                }
            },
            label = {
                Text(
                    text = "Nombre",
                )
            },
            placeholder = {
                Text(text = "Escribe acá tu nombre")
            },
            maxLines = 1,
            textStyle = Typography.body1,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                autoCorrect = false,
                imeAction = ImeAction.Done
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp),
            text = "Este texto está bottom der"
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "image1"
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
            contentDescription = "image2"
        )
        Image(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            bitmap = ImageBitmap.imageResource(id = R.drawable.gato),
            contentDescription = "image2",
            contentScale = ContentScale.Crop,
        )
    }

}

@Preview(
    showBackground = true,
    apiLevel = 30,
    backgroundColor = 1111111111,
    showSystemUi = true,
    group = "Flujo Home",
    device = Devices.PIXEL_4
)
@Composable
fun DefaultPreview() {
    Basic_composeTheme {
        Greeting("Android")
    }
}

@Preview(
    showBackground = true,
    apiLevel = 30,
    backgroundColor = 1111111111,
    showSystemUi = true,
    group = "Flujo Boton",
    device = Devices.PIXEL_4
)
@Composable
fun BetaPreview() {
    Basic_composeTheme {
        CustomButton(onClick = { /*TODO*/ },
            modifier = Modifier.height(40.dp)
        ) {
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}