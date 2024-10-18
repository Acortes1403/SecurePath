package com.example.avance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OlvidasteContScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.vista2),
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
            Text("<")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "¿Olvidaste la",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 40.dp)
        )
        Text(
            text = "contraseña?",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 3.dp)
        )
        Text(
            text = "Por favor escribe tu email para poder recibir un código de verificación.",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Email",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )

        // Campo para el correo
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        // Botón para crear la cuenta, ajustado solo al tamaño del texto
        Button(
            onClick = { navController.navigate("verificar_screen") }, // Navigate to VerificarScreen
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A5E23) // Verde militar
            ),
            modifier = Modifier
                .padding(top = 280.dp)
                .align(Alignment.CenterHorizontally)
                .wrapContentSize()
        ) {
            Text(
                text = "Enviar",
                fontSize = 20.sp
            )
        }

    }}
