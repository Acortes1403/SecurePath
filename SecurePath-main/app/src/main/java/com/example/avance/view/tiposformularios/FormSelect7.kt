package com.example.avance.view.tiposformularios

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect7(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel() // Obtenemos fontSize desde FontSizeViewModel
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState() // Recogemos el valor de fontSize

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Variables Climáticas", color = Color.White, fontSize = fontSize.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFA4C639)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Zona
            Text("Zona", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                RadioButtonWithLabel(
                    label = "Bosque",
                    selected = formData.zone == "Bosque",
                    fontSize = fontSize,
                    onClick = { viewModel.updateZone("Bosque") }
                )
                RadioButtonWithLabel(
                    label = "Arreglo Agroforestal",
                    selected = formData.zone == "Arreglo Agroforestal",
                    fontSize = fontSize,
                    onClick = { viewModel.updateZone("Arreglo Agroforestal") }
                )
                RadioButtonWithLabel(
                    label = "Cultivos Transitorios",
                    selected = formData.zone == "Cultivos Transitorios",
                    fontSize = fontSize,
                    onClick = { viewModel.updateZone("Cultivos Transitorios") }
                )
                RadioButtonWithLabel(
                    label = "Cultivos Permanentes",
                    selected = formData.zone == "Cultivos Permanentes",
                    fontSize = fontSize,
                    onClick = { viewModel.updateZone("Cultivos Permanentes") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextField(label = "Pluviosidad (mm)", value = formData.rainfall, fontSize = fontSize) { viewModel.updateRainfall(it) }
            FormTextField(label = "Temperatura máxima", value = formData.maxTemperature, fontSize = fontSize) { viewModel.updateMaxTemperature(it) }
            FormTextField(label = "Humedad máxima", value = formData.maxHumidity, fontSize = fontSize) { viewModel.updateMaxHumidity(it) }
            FormTextField(label = "Temperatura mínima", value = formData.minTemperature, fontSize = fontSize) { viewModel.updateMinTemperature(it) }
            FormTextField(label = "Humedad mínima", value = formData.minHumidity, fontSize = fontSize) { viewModel.updateMinHumidity(it) }
            FormTextField(label = "Nivel Quebrada (mt)", value = formData.creekLevel, fontSize = fontSize) { viewModel.updateCreekLevel(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de Acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA4C639))
                ) {
                    Text("ATRAS", color = Color.White, fontSize = fontSize.sp)
                }
                Button(
                    onClick = { /* Acción para enviar el formulario */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("ENVIAR", color = Color.White, fontSize = fontSize.sp)
                }
            }
        }
    }
}

@Composable
fun RadioButtonWithLabel(label: String, selected: Boolean, fontSize: Float, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable(onClick = onClick)) {
        RadioButton(selected = selected, onClick = onClick)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, fontSize = fontSize.sp)
    }
}