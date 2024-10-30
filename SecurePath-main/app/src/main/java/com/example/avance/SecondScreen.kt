package com.example.avance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.vista1), // Asegúrate de que la imagen exista
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón para regresar
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                modifier = Modifier.offset(x = (-10).dp)
            ) {
                Text("<")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 105.dp)
            )

            Text(
                text = "Inicia Sesión",
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 110.dp)
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

            // Campo para la contraseña
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray
                ),
                textStyle = TextStyle(color = Color.Black)
            )

            // Row para el botón "Olvidaste la contraseña?"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate("olvidaste_cont_screen") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent, // Fondo transparente
                        contentColor = Color.Black // Texto negro
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Olvidaste tu contraseña?", fontSize = 14.sp)
                }
            }

            Button(
                onClick = { navController.navigate("hola_samantha") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(13, 110, 3),      // Fondo verde del botón
                    contentColor = Color.White         // Color del texto dentro del botón
                ),
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
