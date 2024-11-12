package com.example.avance.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avance.ui.theme.AvanceTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avance.R
import com.example.avance.view.tiposformularios.*
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel

class MainActivity : ComponentActivity() {
    private lateinit var fontSizeViewModel: FontSizeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fontSizeViewModel = FontSizeViewModel()

        setContent {
            AvanceTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navigation(fontSizeViewModel)
                }
            }
        }
    }
}

@Composable
fun Navigation(fontSizeViewModel: FontSizeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") { FirstScreen(navController) }
        composable("second_screen") { AvanceTheme { SecondScreen(navController) } }
        composable("third_screen") { ThirdScreen(navController) }
        composable("olvidaste_cont_screen") { OlvidasteContScreen(navController) }
        composable("verificar_screen") { VerificarScreen(navController) }
        composable("hola_samantha") { HolaSamantha(navController) }
        composable("perfil") { Perfil() }
        composable("formulario_activity") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormularioScreen(navController, formularioViewModel, fontSizeViewModel)
        }

        composable("form_1") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormSelect1(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_2") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormSelect2(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_3") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormSelect3(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_4") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormSelect4(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_5") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormSelect5(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_6") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormSelect6(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_7") {
            val formularioViewModel: FormularioViewModel = viewModel()
            FormSelect7(navController, formularioViewModel, fontSizeViewModel)
        }

        composable("settings") { Settings(navController, fontSizeViewModel) }
        composable("search_todos") { SearchTodos(navController, fontSizeViewModel) }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.captura_de_pantalla_2024_10_16_165923),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = { navController.navigate("second_screen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A5E23),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .offset(y = (-70).dp)
            ) {
                Text("Iniciar Sesión", fontSize = 20.sp)
            }

            Button(
                onClick = { navController.navigate("third_screen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .offset(y = (-70).dp)
            ) {
                Text("Crear Cuenta", fontSize = 20.sp)
            }
        }
    }
}
