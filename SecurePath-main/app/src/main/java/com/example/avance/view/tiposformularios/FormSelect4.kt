package com.example.avance.view.tiposformularios

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect4(navController: NavController, viewModel: FormularioViewModel = viewModel()) {
    val formData = viewModel.formData.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Validación de Cobertura", color = Color.White) },
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
            // Campo de Código
            FormTextField("Código", formData.commonName) { viewModel.updateCommonName(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Seguimiento
            Text("Seguimiento", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Si", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("No", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Cambios
            Text("Cambió", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Si", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("No", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Cobertura
            Text("Cobertura", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("BD", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("RA", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("RB", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("PA", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("PL", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("CP", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("CT", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("VH", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("TD", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("IF", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Disturbio
            Text("Disturbio", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Inundación", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Quema", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Tala", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Erosión", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Minería", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Carretera", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Más plantas acuáticas", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Otro", formData.selectedObservation) { viewModel.updateSelectedObservation(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Acción para elegir archivos */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5E23)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Elige archivo", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            Text("Observaciones", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = formData.observationNotes,
                onValueChange = { viewModel.updateObservationNotes(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = { Text("Observaciones") }
            )

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
                    Text("ATRAS", color = Color.White)
                }
                Button(
                    onClick = { /* Acción para enviar el formulario */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("ENVIAR", color = Color.White)
                }
            }
        }
    }
}
