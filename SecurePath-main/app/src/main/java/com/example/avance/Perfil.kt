package com.example.avance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Perfil() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Puedes personalizar la imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.vista4), // Cambia a la imagen que prefieras
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido de la pantalla de perfil
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Perfil", fontSize = 32.sp, color = Color.Black)
            // Aquí puedes agregar más contenido relacionado con el perfil
        }
    }
}
