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
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect3(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel()
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fauna Búsqueda Libre", color = Color.White, fontSize = fontSize.sp) },
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
            FormTextField("Código", formData.commonName, fontSize) { viewModel.updateCommonName(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Zona
            Text("Zona", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Bosque", formData.zone, fontSize) { viewModel.updateZone(it) }
                ObservationRadioButton("Arreglo Agroforestal", formData.zone, fontSize) { viewModel.updateZone(it) }
                ObservationRadioButton("Cultivos Transitorios", formData.zone, fontSize) { viewModel.updateZone(it) }
                ObservationRadioButton("Cultivos Permanentes", formData.zone, fontSize) { viewModel.updateZone(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre Común
            FormTextField("Nombre Común", formData.commonName, fontSize) { viewModel.updateCommonName(it) }

            // Nombre Científico
            FormTextField("Nombre Científico", formData.scientificName, fontSize) { viewModel.updateScientificName(it) }

            // Número de Individuos
            FormTextField("Número de Individuos", formData.individualCount, fontSize, isNumeric = true) { viewModel.updateIndividualCount(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Observación
            Text("Tipo de Observación", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                ObservationRadioButton("La Vió", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Huella", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Rastro", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Cacería", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Le Dijeron", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Acción para elegir archivos */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5E23)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Elige archivo", color = Color.White, fontSize = fontSize.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            Text("Observaciones", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = formData.observationNotes,
                onValueChange = { viewModel.updateObservationNotes(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = { Text("Observaciones", fontSize = fontSize.sp) },
                textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp)
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
