package com.example.avance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SecondScreen(navController: NavController) {
    var email by remember { mutableStateOf("example@gmail.com") }
    var password by remember { mutableStateOf("example123") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.vista1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón para regresar
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Ajusta el padding general para el contenido
            contentAlignment = Alignment.TopStart // Alinea el botón en la esquina superior izquierda
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Fondo transparente
                    contentColor = Color.White // Texto blanco
                ),
                modifier = Modifier.offset(x = (-10).dp) // Desplazarlo más a la izquierda con un offset negativo
            ) {
                Text("<") // Texto del botón de regreso
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 64.dp), // Ajuste de padding para el contenido general
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,  // Grosor del texto
                modifier = Modifier.padding(top = 105.dp)
            )

            Text(
                text = "Inicia Sesión",
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,  // Grosor del texto
                modifier = Modifier
                    .padding(top = 115.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Email",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            )

            // Campo para el correo
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Contraseña",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            )

            // Campo para la contraseña
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            // Row para el botón "Olvidaste la contraseña?"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /* Navegar a la pantalla de recuperar contraseña */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "Olvidaste la contraseña?", fontSize = 14.sp)
                }
            }

            Button(
                onClick = { /* Acción de inicio de sesión */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 96.dp)
            ) {
                Text(
                    text = "ENTRAR",
                    fontSize = 20.sp
                )
            }
        }
    }
}
