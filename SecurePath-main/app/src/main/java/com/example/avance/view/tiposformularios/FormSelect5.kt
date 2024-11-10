package com.example.avance.view.tiposformularios

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect5(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel() // Obtenemos fontSize desde FontSizeViewModel
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState() // Recogemos el valor de fontSize

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parcela de Vegetación", color = Color.White, fontSize = fontSize.sp) },
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

            // Cuadrante
            Text("Cuadrante", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // Primera fila de cuadrantes: A y B
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuadrantButton(label = "A", isSelected = formData.selectedObservation == "A", fontSize) {
                    viewModel.updateSelectedObservation("A")
                }
                QuadrantButton(label = "B", isSelected = formData.selectedObservation == "B", fontSize) {
                    viewModel.updateSelectedObservation("B")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Segunda fila de cuadrantes: C, D, E, F, G, H
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("C", "D", "E", "F", "G", "H").forEach { label ->
                    QuadrantButton(label = label, isSelected = formData.selectedObservation == label, fontSize) {
                        viewModel.updateSelectedObservation(label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sub-Cuadrante
            Text(text = "Sub-Cuadrante", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                listOf("1", "2", "3", "4").forEach { label ->
                    QuadrantButton(label = label, isSelected = formData.selectedSubQuadrant == label, fontSize) {
                        viewModel.updateSelectedSubQuadrant(label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hábito de crecimiento
            Text("Hábito de crecimiento", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                listOf(
                    R.drawable.ic_arbusto to "Arbusto < 1mt",
                    R.drawable.ic_arbolito to "Arbolito 1-3 mt",
                    R.drawable.ic_arbol to "Árbol > 3mt"
                ).forEach { (iconId, label) ->
                    GrowthHabitButton(
                        iconId = iconId,
                        label = label,
                        isSelected = formData.selectedGrowthHabit == label,
                        fontSize = fontSize,
                        onClick = { viewModel.updateSelectedGrowthHabit(label) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextField("Nombre Común Especie", formData.commonName, fontSize) { viewModel.updateCommonName(it) }
            FormTextField("Nombre Científico", formData.scientificName, fontSize) { viewModel.updateScientificName(it) }
            FormTextField("Placa", formData.placa, fontSize) { viewModel.updatePlaca(it) }
            FormTextField("Circunferencia en cm (CL)", formData.circunference, fontSize) { viewModel.updateCircunference(it) }
            FormTextField("Distancia en mt", formData.distance, fontSize) { viewModel.updateDistance(it) }
            FormTextField("Estatura Biomonitor en mt", formData.biomonHeight, fontSize) { viewModel.updateBiomonHeight(it) }
            FormTextField("Altura en mt", formData.height, fontSize) { viewModel.updateHeight(it) }

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

@Composable
fun QuadrantButton(label: String, isSelected: Boolean, fontSize: Float, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFFA4C639) else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = fontSize.sp, color = if (isSelected) Color.Black else Color.Gray)
    }
}

@Composable
fun GrowthHabitButton(iconId: Int, label: String, isSelected: Boolean, fontSize: Float, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(width = 100.dp, height = 120.dp)
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFFA4C639) else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = label,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = fontSize.sp, color = Color.Black)
    }
}
