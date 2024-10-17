package com.example.avance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun ThirdScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.vista1), // Cambia por el ID de tu imagen de fondo
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize() // La imagen ocupa toda la pantalla
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
                .padding(
                    horizontal = 16.dp,
                    vertical = 44.dp
                ), // Ajuste vertical para mover todo hacia arriba
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Crea una",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,  // Grosor del texto
                modifier = Modifier.padding(top = 80.dp) // Ajusta este valor para subir el texto individualmente
            )

            Text(
                text = "cuenta",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,  // Grosor del texto
                modifier = Modifier.padding(top = 3.dp) // Espacio leve entre las líneas
            )

            Text(
                text = "Regístrate",
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,  // Grosor del texto
                modifier = Modifier
                    .padding(top = 105.dp) // Ajusta el espaciado si es necesario
                    .fillMaxWidth()
            )
        }
    }

}
