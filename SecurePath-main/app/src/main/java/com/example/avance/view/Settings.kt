// Paquete de la aplicación
package com.example.avance.view

// Importación de componentes y funciones necesarias para la interfaz de usuario
import androidx.compose.foundation.Image
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
import com.example.avance.viewmodel.FontSizeViewModel

// Función que representa la pantalla de configuración de la aplicación
@Composable
fun Settings(navController: NavController, fontSizeViewModel: FontSizeViewModel = viewModel()) {
    val fontSize by fontSizeViewModel.fontSize.collectAsState()


    // Caja principal que ocupa toda la pantalla
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo que ocupa toda la pantalla
        Image(
            painter = painterResource(id = R.drawable.vista6), // Fondo de pantalla
            contentDescription = "Fondo de pantalla",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Columna principal que contiene todos los elementos de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Fila para el botón de "Regresar" y el texto de "Configuración"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = {
                        navController.navigate("hola_samantha?refresh=true") // Navega a la pantalla principal con actualización
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent, // Fondo transparente
                        contentColor = Color.Black // Texto negro
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp), // Sin sombra
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = "<", color = Color.Black, fontSize = 28.sp) // Texto del botón de regreso
                }

                // Texto "Configuración"
                Text(
                    text = "Configuración",
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }

            // Sección de "General"
            Spacer(modifier = Modifier.height(24.dp))
            Text("GENERAL", fontSize = fontSize.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            ClickableText(
                text = AnnotatedString("Editar Perfil"),
                onClick = { /* Navegar a la pantalla de Editar Perfil */ },
                style = LocalTextStyle.current.copy(fontSize = fontSize.sp, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ClickableText(
                text = AnnotatedString("Cambiar contraseña"),
                onClick = { /* Navegar a la pantalla de Cambiar Contraseña */ },
                style = LocalTextStyle.current.copy(fontSize = fontSize.sp, color = Color.Black)
            )

            // Sección de "Notificaciones"
            Spacer(modifier = Modifier.height(24.dp))
            Text("NOTIFICACIONES", fontSize = fontSize.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Notificaciones", fontSize = fontSize.sp, color = Color.Black)
                Spacer(modifier = Modifier.weight(1f))
                var notificationsEnabled by remember { mutableStateOf(true) } // Estado de notificaciones
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF4CAF50), // Color para "activado"
                        uncheckedThumbColor = Color.Gray // Color para "desactivado"
                    )
                )
            }

            // Sección de ajuste de tamaño de letra
            Spacer(modifier = Modifier.height(24.dp))
            Text("AJUSTE DE TAMAÑO DE LETRA", fontSize = fontSize.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Slider(
                value = fontSize,
                onValueChange = {
                    fontSizeViewModel.updateFontSize(it) // Actualiza el tamaño de letra en el ViewModel
                },
                valueRange = 14f..30f,
                modifier = Modifier.fillMaxWidth()
            )

            Text("Tamaño de letra actual: ${fontSize.toInt()} sp", fontSize = 16.sp, color = Color.Black)

            // Sección de "Acciones"
            Spacer(modifier = Modifier.height(24.dp))
            Text("ACCIONES", fontSize = fontSize.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            // Botón de texto para "Cerrar sesión"
            TextButton(
                onClick = {
                    navController.navigate("first_screen") // Navega a la pantalla de inicio de sesión
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black
                ),
                modifier = Modifier.padding(0.dp)
            ) {
                Text("Cerrar sesión", fontSize = fontSize.sp, fontWeight = FontWeight.Normal)
            }
        }

        // Menú inferior
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Fila de botones de navegación en el menú inferior
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón de Inicio
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(
                        onClick = {
                            if (navController.currentDestination?.route != "hola_samantha") {
                                navController.navigate("hola_samantha?refresh=true") // Navega al inicio si no está ya en él
                            }
                        },
                        iconResId = R.drawable.ic_home
                    )
                    Text(text = "Inicio", fontSize = 12.sp, color = Color.Black)
                }

                // Botón de Buscar
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(
                        onClick = {
                            navController.navigate("search_todos") // Navega a la pantalla de búsqueda
                        },
                        iconResId = R.drawable.ic_search
                    )
                    Text(text = "Buscar", fontSize = 12.sp, color = Color.Black)
                }

                // Botón de Configuración
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(
                        onClick = {
                            if (navController.currentDestination?.route != "settings") {
                                navController.navigate("settings") // Navega a la configuración si no está ya en ella
                            }
                        },
                        iconResId = R.drawable.ic_settings
                    )
                    Text(text = "Configuración", fontSize = 12.sp, color = Color.Black)
                }
            }
        }
    }
}

// Vista previa de la pantalla de configuración para propósitos de diseño
@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    val navController = rememberNavController()
    Settings(navController = navController)
}
