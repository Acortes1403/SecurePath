// Paquete de la aplicación
package com.example.avance.view

// Importación de componentes y funciones necesarios para la interfaz de usuario
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.R

// Función que representa la pantalla de búsqueda
@Composable
fun SearchTodos(navController: NavController, fontSizeViewModel: FontSizeViewModel) {
    // Caja que ocupa toda la pantalla
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo que ocupa toda la pantalla
        Image(
            painter = painterResource(id = R.drawable.vista6), // Fondo de pantalla
            contentDescription = "Fondo de pantalla",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Columna principal que contiene los elementos de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Espacio para ajustar la posición de los elementos
            Spacer(modifier = Modifier.height(15.dp))

            // Fila para el botón de "Regresar" y el título "Búsqueda"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = {
                        navController.popBackStack() // Navega a la pantalla anterior
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent, // Fondo transparente
                        contentColor = Color.Black // Color del texto
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp), // Sin elevación
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = "<", color = Color.Black, fontSize = 28.sp) // Texto del botón de regreso
                }

                // Título "Búsqueda"
                Text(
                    text = "Búsqueda",
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        // Menú inferior con opciones de navegación
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f)) // Espacio para empujar el menú hacia abajo

            // Fila de botones de navegación en el menú inferior
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón de "Inicio"
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(
                        onClick = {
                            if (navController.currentDestination?.route != "hola_samantha") {
                                navController.navigate("hola_samantha?refresh=true") // Navega a la pantalla de inicio si no está ya en ella
                            }
                        },
                        iconResId = R.drawable.ic_home // Ícono de inicio
                    )
                    Text(text = "Inicio", fontSize = 12.sp, color = Color.Black) // Texto debajo del ícono
                }

                // Botón de "Buscar" (pantalla actual)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(
                        onClick = {
                            navController.navigate("search_todos") // Mantiene la navegación en la pantalla actual
                        },
                        iconResId = R.drawable.ic_search // Ícono de búsqueda
                    )
                    Text(text = "Buscar", fontSize = 12.sp, color = Color.Black)
                }

                // Botón de "Configuración"
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomIconButton(
                        onClick = {
                            if (navController.currentDestination?.route != "settings") {
                                navController.navigate("settings") // Navega a la pantalla de configuración si no está ya en ella
                            }
                        },
                        iconResId = R.drawable.ic_settings // Ícono de configuración
                    )
                    Text(text = "Configuración", fontSize = 12.sp, color = Color.Black)
                }
            }
        }
    }
}

// Vista previa de la pantalla de búsqueda para propósitos de diseño
@Preview(showBackground = true)
@Composable
fun SearchTodosPreview() {
    val navController = rememberNavController()
    SearchTodos(navController = navController, fontSizeViewModel = FontSizeViewModel())
}
