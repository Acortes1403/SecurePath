package com.example.avance.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.R

@OptIn(ExperimentalMaterial3Api::class)
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

        // Campo para el correo
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
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
        // Botón para crear la cuenta, ajustado solo al tamaño del texto
        Button(
            onClick = { navController.navigate("Verificar_screen") },//falta la logica para crear la cuenta
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(13, 110, 3),      // Fondo verde del botón
                contentColor = Color.White         // Color del texto dentro del botón
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 96.dp)
        ) {
            Text(
                text = "Crear Cuenta",
                fontSize = 20.sp
            )
        }

    }}
