package com.example.avance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun VerificarScreen(navController: NavController) {
    var digit1 by remember { mutableStateOf(TextFieldValue("")) }
    var digit2 by remember { mutableStateOf(TextFieldValue("")) }
    var digit3 by remember { mutableStateOf(TextFieldValue("")) }
    var digit4 by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.vista2), // Asegúrate de tener una imagen de fondo adecuada
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón para regresar a la pantalla anterior
        Button(
            onClick = { navController.popBackStack() }, // Regresar a la pantalla OlvidasteContScreen
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Text("<") // Texto del botón
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Verificación",
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp) // Mantener en la parte superior
            )
            Text(
                text = "Por favor escribe el código de verificación de 4 dígitos enviado a example@gmail.com",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 130.dp)
            )

            // Recuadros para los dígitos (centrados)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = digit1,
                        onValueChange = { if (it.text.length <= 1) digit1 = it }, // Limita la entrada a un carácter
                        modifier = Modifier
                            .width(50.dp)
                            .padding(4.dp),
                        maxLines = 1
                    )
                    TextField(
                        value = digit2,
                        onValueChange = { if (it.text.length <= 1) digit2 = it },
                        modifier = Modifier
                            .width(50.dp)
                            .padding(4.dp),
                        maxLines = 1
                    )
                    TextField(
                        value = digit3,
                        onValueChange = { if (it.text.length <= 1) digit3 = it },
                        modifier = Modifier
                            .width(50.dp)
                            .padding(4.dp),
                        maxLines = 1
                    )
                    TextField(
                        value = digit4,
                        onValueChange = { if (it.text.length <= 1) digit4 = it },
                        modifier = Modifier
                            .width(50.dp)
                            .padding(4.dp),
                        maxLines = 1
                    )
                }
            }

            Button(
                onClick = { /* Aquí puedes agregar cualquier lógica que desees al hacer clic en el botón */ },
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

        }
        }
    }

