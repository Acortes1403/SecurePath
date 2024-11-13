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
fun FormSelect4(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel() // Obtenemos fontSize desde FontSizeViewModel
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState() // Recogemos el valor de fontSize

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Validación de Cobertura", color = Color.White, fontSize = fontSize.sp) },
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
            FormTextField("Código", formData.codigof4, fontSize) { viewModel.updateCodigof4(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Seguimiento
            Text("Seguimiento", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Si", formData.yesandno1, fontSize) { viewModel.updateYesNo1(it) }
                ObservationRadioButton("No", formData.yesandno1, fontSize) { viewModel.updateYesNo1(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Cambios
            Text("Cambió", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Si", formData.yesandno2, fontSize) { viewModel.updateYesNo2(it) }
                ObservationRadioButton("No", formData.yesandno2, fontSize) { viewModel.updateYesNo2(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Cobertura
            Text("Cobertura", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("BD", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("RA", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("RB", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("PA", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("PL", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("CP", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("CT", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("VH", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("TD", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
                ObservationRadioButton("IF", formData.observationType, fontSize) { viewModel.updateObservationType(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Disturbio
            Text("Disturbio", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Inundación", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
                ObservationRadioButton("Quema", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
                ObservationRadioButton("Tala", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
                ObservationRadioButton("Erosión", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
                ObservationRadioButton("Minería", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
                ObservationRadioButton("Carretera", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
                ObservationRadioButton("Más plantas acuáticas", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
                ObservationRadioButton("Otro", formData.disturbance, fontSize) { viewModel.updateDisturbance(it) }
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
                    onClick = {
                        viewModel.saveValidacionCobertura() //Boton para guardar datos de formulario y ValidacionCobertura
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("ENVIAR", color = Color.White, fontSize = fontSize.sp)
                }
            }
        }
    }
}