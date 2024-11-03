package com.example.avance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avance.ui.theme.AvanceTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
<<<<<<< HEAD
import com.example.avance.tiposformularios.FormSelect1
import com.example.avance.tiposformularios.FormSelect2
import com.example.avance.tiposformularios.FormSelect3
import com.example.avance.tiposformularios.FormSelect4
import com.example.avance.tiposformularios.FormSelect5
import com.example.avance.tiposformularios.FormSelect6
import com.example.avance.tiposformularios.FormSelect7
=======
>>>>>>> 8beac99b33b40d2a5048ab9b4da9ec1c7cebea6b

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvanceTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") { FirstScreen(navController) }
        composable("second_screen") { SecondScreen(navController) }
        composable("third_screen") { ThirdScreen(navController) }
        composable("olvidaste_cont_screen") { OlvidasteContScreen(navController) }
        composable("verificar_screen") { VerificarScreen(navController) }
        composable("hola_samantha") { HolaSamantha(navController) } // Asegúrate de pasar el navController
        composable("perfil") { Perfil() } // Nueva pantalla de perfil
<<<<<<< HEAD
        composable("formulario_activity") { FormularioScreen(navController) }
        composable("form_1") { FormSelect1(navController) }
        composable("form_2") { FormSelect2(navController) }
        composable("form_3") { FormSelect3(navController) }
        composable("form_4") { FormSelect4(navController) }
        composable("form_5") { FormSelect5(navController) }
        composable("form_6") { FormSelect6(navController) }
        composable("form_7") { FormSelect7(navController) }
=======
>>>>>>> 8beac99b33b40d2a5048ab9b4da9ec1c7cebea6b
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
            // Botón de Iniciar Sesión
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

            // Botón de Crear Cuenta
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
