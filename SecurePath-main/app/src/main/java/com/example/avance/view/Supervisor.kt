package com.example.avance.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.R

@Composable
fun SupervisorScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.vista3),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Botón izquierdo con logo
        IconButton(
            onClick = { /* Acción para el botón izquierdo */ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(56.dp)
                .background(Color.White, shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Botón derecho para perfil
        Button(
            onClick = { navController.navigate("perfil") },
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Text("Imagen perfil")
        }

        // Título central y listas
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título de "Supervisor"
            Text(
                text = "Supervisor",
                fontSize = 36.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de grupos de biomonitores
            Text("Grupos de Biomonitores", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp)
            ) {
                items(listOf("Grupo 1", "Grupo 2", "Grupo 3")) { grupo ->
                    Text(
                        text = grupo,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista corta de formularios
            Text("Formularios Recientes", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 8.dp)
            ) {
                items(listOf("Formulario A", "Formulario B", "Formulario C")) { formulario ->
                    Text(
                        text = formulario,
                        fontSize = 18.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        // Botones en la parte inferior
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(onClick = { navController.navigate("supervisor") }, iconResId = R.drawable.ic_home)
                    Text(text = "Inicio", fontSize = 12.sp, color = Color.Black)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(onClick = { navController.navigate("search_todos") }, iconResId = R.drawable.ic_search)
                    Text(text = "Buscar", fontSize = 12.sp, color = Color.Black)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(onClick = { navController.navigate("settings") }, iconResId = R.drawable.ic_settings)
                    Text(text = "Configuración", fontSize = 12.sp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun CustomSupervisorButton(onClick: () -> Unit, iconResId: Int) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(50.dp) // Ajuste del tamaño
            .background(if (isSelected) Color.LightGray else Color.Transparent, shape = CircleShape)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isSelected = true
                        onClick()
                        tryAwaitRelease()
                        isSelected = false
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}