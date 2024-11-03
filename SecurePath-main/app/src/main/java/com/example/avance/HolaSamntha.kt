package com.example.avance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController

@Composable
fun HolaSamantha(navController: NavController) { // Asegúrate de recibir el navController
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.vista3), // Background for the screen
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Left Icon Button with Logo
        IconButton(
            onClick = { /* Action for left button */ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(56.dp) // Size of the button
                .background(Color.White, shape = CircleShape) // Background of the button
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Use your logo here
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize() // Image fills the button
            )
        }

        // Right Button
        Button(
            onClick = { navController.navigate("perfil") }, // Navegar a la pantalla de perfil
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Text("Imagen perfil") // Text for the button
        }

        // Centered Text
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Hola,",
                fontSize = 44.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Samantha",
                fontSize = 44.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp)) // Space between text and button

            // Circular Button
            IconButton(
                onClick = { navController.navigate("formulario_activity") },
                modifier = Modifier
                    .size(56.dp) // Size of the button
                    .background(Color(0xFF4A4B2A), shape = CircleShape) // Military green color
                    .padding(8.dp) // Padding for the text
            ) {
                Text(
                    text = "+",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Bottom Buttons
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f)) // Space between buttons and bottom

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Space between buttons
            ) {
                // Home Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(onClick = { /* Home action */ }, iconResId = R.drawable.ic_home)
                    Text(text = "Inicio", fontSize = 12.sp, color = Color.Black)
                }

                // Search Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(onClick = { /* Search action */ }, iconResId = R.drawable.ic_search)
                    Text(text = "Buscar", fontSize = 12.sp, color = Color.Black)
                }

                // Settings Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(onClick = { /* Settings action */ }, iconResId = R.drawable.ic_settings)
                    Text(text = "Configuración", fontSize = 12.sp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun CustomIconButton(onClick: () -> Unit, iconResId: Int) {
    var isSelected by remember { mutableStateOf(false) }

    val buttonColor = if (isSelected) Color.White else Color.Transparent

    Box(
        modifier = Modifier
            .size(100.dp, 50.dp) // Size of the button
            .background(buttonColor, shape = CircleShape) // Change background color based on selection
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isSelected = true // Change to white on press
                        onClick() // Call the provided action
                        tryAwaitRelease() // Wait for release
                        isSelected = false // Restore state on release
                    }
                )
            },
        contentAlignment = Alignment.Center // Center content in the Box
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp) // Size of the icon
        )
    }
}