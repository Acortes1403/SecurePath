// Paquete de la aplicación
package com.example.avance.view

// Importación de componentes y funciones necesarias para la interfaz de usuario
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avance.R
import com.example.avance.ui.theme.SecondaryColor
import com.example.avance.viewmodel.FontSizeViewModel

// Función que representa la pantalla de configuración de la aplicación
@Composable
fun Settings(navController: NavController, fontSizeViewModel: FontSizeViewModel = viewModel()) {
    val fontSize by fontSizeViewModel.fontSize.collectAsState()
    var notificationsEnabled by remember { mutableStateOf(true) } // State for notifications

    // Background and Main Layout
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.vista_perfil_awaq), // Background image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp), // Consistent padding
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Back Button and Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigate("hola_samantha?refresh=true") },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Configuración",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // General Section
            Text(
                text = "GENERAL",
                fontSize = fontSize.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            ClickableText(
                text = AnnotatedString("Editar Perfil"),
                onClick = { navController.navigate("editar_perfil") },
                style = LocalTextStyle.current.copy(fontSize = fontSize.sp, color = Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ClickableText(
                text = AnnotatedString("Cambiar contraseña"),
                onClick = { /* Navigate to Change Password */ },
                style = LocalTextStyle.current.copy(fontSize = fontSize.sp, color = Color.White)
            )

            // Notifications Section
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "NOTIFICACIONES",
                fontSize = fontSize.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Notificaciones", fontSize = fontSize.sp, color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF4CAF50), // Active color
                        uncheckedThumbColor = Color.Gray // Inactive color
                    )
                )
            }

            // Font Size Adjustment
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "AJUSTE DE TAMAÑO DE LETRA",
                fontSize = fontSize.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Slider(
                value = fontSize,
                onValueChange = { fontSizeViewModel.updateFontSize(it) },
                valueRange = 14f..30f,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Tamaño de letra actual: ${fontSize.toInt()} sp",
                fontSize = 16.sp,
                color = Color.White
            )

            // Actions Section
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "ACCIONES",
                fontSize = fontSize.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                onClick = { navController.navigate("first_screen") },
                colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
            ) {
                Text("Cerrar sesión", fontSize = fontSize.sp)
            }
        }

        // Bottom Navigation Bar with semi-transparent background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SecondaryColor.copy(alpha = 0.2f)) // Semi-transparent background
                .align(Alignment.BottomEnd)
        ) {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier.padding(vertical = 8.dp) // Add padding if needed
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    val navController = rememberNavController()
    Settings(navController = navController)
}
