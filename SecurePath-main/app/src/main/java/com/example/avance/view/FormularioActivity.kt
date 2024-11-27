package com.example.avance.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.model.FormularioBase
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioActivity(
    viewModel: FormularioViewModel,
    id: Int,
    navController: NavController
) {
    // Reset the form data when entering the screen
    LaunchedEffect(Unit) {
        viewModel.resetFormData()
    }
        // Obtenemos el formulario por ID desde el ViewModel
    val formulario = viewModel.getFormularioById(id).collectAsState(initial = null).value

    Column(modifier = Modifier.fillMaxSize()) {
        // AppBar superior
        TopAppBar(
            title = { Text("Detalles del Formulario", fontSize = 20.sp) },
            navigationIcon = {
                Text(
                    text = "<",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { navController.popBackStack() }
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFA8D8A2))
        )

        // Mostrar detalles o mensaje de carga/error
        formulario?.let { form ->
            FormularioDetails(formulario = form)
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Formulario no encontrado o todavía cargando...")
        }
    }
}

@Composable
fun FormularioDetails(formulario: FormularioBase) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ID del formulario
        Text(
            text = "ID: #FM${formulario.id}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fecha y hora
        Text(
            text = "Fecha: ${formulario.date}",
            fontSize = 14.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Hora: ${formulario.hora}",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tipo de registro
        formulario.tipoDeRegistro?.let {
            Text(
                text = "Tipo de Registro: $it",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}
