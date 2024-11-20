package com.example.avance.view


import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.AuthenticationCallback
import com.example.avance.R
import com.example.avance.model.MyApp
import com.example.avance.view.tiposformularios.*
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel
import com.example.avance.viewmodel.FormularioViewModelFactory
import com.auth0.android.result.Credentials

class MainActivity : ComponentActivity() {
    private lateinit var fontSizeViewModel: FontSizeViewModel
    private lateinit var formularioViewModel: FormularioViewModel
    private lateinit var auth0: Auth0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fontSizeViewModel = FontSizeViewModel()


        // Inicializar Auth0 con clientId y dominio desde strings.xml
        //ayuda
        auth0 = Auth0(
            getString(R.string.com_auth0_client_id),
            getString(R.string.com_auth0_domain)
        )


        // Obtener instancia de AppDatabase desde MyApp
        val appDatabase = (application as MyApp).database

        // Crear instancia de FormularioViewModelFactory
        val viewModelFactory = FormularioViewModelFactory(
            faunaTransectoDao = appDatabase.faunaTransectoDao(),
            faunaConteoDao = appDatabase.faunaConteoDao(),
            faunaBusquedalibreDao = appDatabase.faunaBusquedalibreDao(),
            parcelaVegetacionDao = appDatabase.parcelaVegetacionDao(),
            validacionCoberturaDao = appDatabase.validacionCoberturaDao(),
            camarasTrampaDao = appDatabase.camarasTrampaDao(),
            variablesClimaticasDao = appDatabase.variablesClimaticasDao(),
            formularioBaseDao = appDatabase.formularioBaseDao(),
            dao = appDatabase.formularioBaseDao()
        )

        // Crear el ViewModel utilizando ViewModelProvider y el factory
        formularioViewModel = ViewModelProvider(this, viewModelFactory)[FormularioViewModel::class.java]
        fontSizeViewModel = FontSizeViewModel() // O crea este ViewModel según cómo lo uses

        // Pasar `formularioViewModel` a setContent para que esté disponible en los Composables
        setContent {
            AvanceTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navigation(
                        fontSizeViewModel = fontSizeViewModel,
                        formularioViewModel = formularioViewModel,
                        mainActivity = this
                    )
                }
            }
        }
    }

    // Función para manejar la autenticación de Auth0
    fun loginWithAuth0(
        username: String,
        password: String,
        onSuccess: (Credentials) -> Unit,
        onError: (String) -> Unit
    ) {
        val authentication = AuthenticationAPIClient(auth0)
        authentication
            .login(username, password, "Username-Password-Authentication")
            .setScope("openid profile email")
            .start(object : AuthenticationCallback<Credentials> {
                override fun onFailure(error: AuthenticationException) {
                    Log.e("AuthError", "Error de autenticación: ${error.localizedMessage}")
                    onError(error.localizedMessage ?: "Error desconocido")
                }

                override fun onSuccess(result: Credentials) {
                    onSuccess(result)
                }
            })
    }
}



// Navigation.kt
@Composable
fun Navigation(
    fontSizeViewModel: FontSizeViewModel,
    formularioViewModel: FormularioViewModel,
    mainActivity: MainActivity
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") { FirstScreen(navController) }
        composable("second_screen") { AvanceTheme { SecondScreen(navController, mainActivity) } }
        composable("hola_samantha") { HolaSamantha(navController) }
        composable("perfil") { Perfil(navController) }

        // Pasar el mismo formularioViewModel a todos los formularios
        composable("FormularioActivity/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            FormularioActivity(viewModel = formularioViewModel, id = id, navController = navController)
        }

        composable("formulario_activity") {
            FormularioScreen(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_1") {
            FormSelect1(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_2") {
            FormSelect2(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_3") {
            FormSelect3(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_4") {
            FormSelect4(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_5") {
            FormSelect5(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_6") {
            FormSelect6(navController, formularioViewModel, fontSizeViewModel)
        }
        composable("form_7") {
            FormSelect7(navController, formularioViewModel, fontSizeViewModel)
        }

        composable("settings") { Settings(navController, fontSizeViewModel) }
        composable("search_todos") { SearchTodos(navController, fontSizeViewModel, id, formularioViewModel) }
        composable("editar_perfil") { EditarPerfil(navController)}
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
                Text("Continuar", fontSize = 20.sp)
            }
        }
    }
}
