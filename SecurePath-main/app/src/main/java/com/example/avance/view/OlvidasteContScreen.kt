package com.example.avance.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.ui.theme.AvanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OlvidasteContScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.vista_2_light),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón para regresar a la segunda pantalla
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            modifier = Modifier.padding(16.dp).align(Alignment.TopStart)
        ) {
            Text("<", fontSize = 40.sp)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center), // Centers the column within the Box
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Olvidaste la",
                fontSize = 60.sp,
                color = Color.White,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                text = "contraseña?",
                fontSize = 60.sp,
                color = Color.White,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Por favor escribe tu email para poder recibir un código de verificación.",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
            )

            // Campo para el correo
            Text(
                text = "Email",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .fillMaxWidth(0.6f) // Ensures it's aligned as you desire in the center
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("") },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,                // Color de fondo del TextField
                    focusedIndicatorColor = Color.Blue,          // Color del borde cuando está en foco
                    unfocusedIndicatorColor = Color.Gray,        // Color del borde cuando no está en foco
                    focusedLabelColor = Color.Blue,              // Color de la etiqueta cuando el campo está en foco
                    unfocusedLabelColor = Color.Gray             // Color de la etiqueta cuando el campo no está en foco
                ),
                textStyle = TextStyle(color = Color.Black)
            )
            // Submit Button
            Button(
                onClick = { navController.navigate("Verificar_screen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3A5F0B),  // Dark green
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 48.dp)
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text(
                    text = "Enviar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
            }

        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOlvidasteContLight() {
    AvanceTheme(darkTheme = false) {
        OlvidasteContScreen(navController = NavController(LocalContext.current))
    }
}
