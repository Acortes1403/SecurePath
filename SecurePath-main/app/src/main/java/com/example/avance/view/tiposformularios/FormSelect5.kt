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
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect5(navController: NavController, viewModel: FormularioViewModel = viewModel()) {
    val formData = viewModel.formData.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parcela de Vegetación", color = Color.White) },
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

            // Cuadrante
            Text("Cuadrante", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // Primera fila de cuadrantes: A y B
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuadrantButton(label = "A", isSelected = formData.selectedObservation == "A") {
                    viewModel.updateSelectedObservation("A")
                }
                QuadrantButton(label = "B", isSelected = formData.selectedObservation == "B") {
                    viewModel.updateSelectedObservation("B")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Segunda fila de cuadrantes: C, D, E, F, G, H
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuadrantButton(label = "C", isSelected = formData.selectedObservation == "C") {
                    viewModel.updateSelectedObservation("C")
                }
                QuadrantButton(label = "D", isSelected = formData.selectedObservation == "D") {
                    viewModel.updateSelectedObservation("D")
                }
                QuadrantButton(label = "E", isSelected = formData.selectedObservation == "E") {
                    viewModel.updateSelectedObservation("E")
                }
                QuadrantButton(label = "F", isSelected = formData.selectedObservation == "F") {
                    viewModel.updateSelectedObservation("F")
                }
                QuadrantButton(label = "G", isSelected = formData.selectedObservation == "G") {
                    viewModel.updateSelectedObservation("G")
                }
                QuadrantButton(label = "H", isSelected = formData.selectedObservation == "H") {
                    viewModel.updateSelectedObservation("H")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

// Sub-Cuadrante
            Text(text = "Sub-Cuadrante", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                QuadrantButton(label = "1", isSelected = formData.selectedSubQuadrant == "1") {
                    viewModel.updateSelectedSubQuadrant("1")
                }
                QuadrantButton(label = "2", isSelected = formData.selectedSubQuadrant == "2") {
                    viewModel.updateSelectedSubQuadrant("2")
                }
                QuadrantButton(label = "3", isSelected = formData.selectedSubQuadrant == "3") {
                    viewModel.updateSelectedSubQuadrant("3")
                }
                QuadrantButton(label = "4", isSelected = formData.selectedSubQuadrant == "4") {
                    viewModel.updateSelectedSubQuadrant("4")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hábito de crecimiento
            Text("Hábito de crecimiento", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                GrowthHabitButton(
                    iconId = R.drawable.ic_arbusto,
                    label = "Arbusto < 1mt",
                    isSelected = formData.selectedGrowthHabit == "Arbusto",
                    onClick = { viewModel.updateSelectedGrowthHabit("Arbusto") }
                )
                GrowthHabitButton(
                    iconId = R.drawable.ic_arbolito,
                    label = "Arbolito 1-3 mt",
                    isSelected = formData.selectedGrowthHabit == "Arbolito",
                    onClick = { viewModel.updateSelectedGrowthHabit("Arbolito") }
                )
                GrowthHabitButton(
                    iconId = R.drawable.ic_arbol,
                    label = "Árbol > 3mt",
                    isSelected = formData.selectedGrowthHabit == "Árbol",
                    onClick = { viewModel.updateSelectedGrowthHabit("Árbol") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextField("Nombre Común Especie", formData.commonName) { viewModel.updateCommonName(it) }
            FormTextField("Nombre Científico", formData.scientificName) { viewModel.updateScientificName(it) }
            FormTextField("Placa", formData.placa) { viewModel.updatePlaca(it) }
            FormTextField("Circunferencia en cm (CL)", formData.circunference) { viewModel.updateCircunference(it) }
            FormTextField("Distancia en mt", formData.distance) { viewModel.updateDistance(it) }
            FormTextField("Estatura Biomonitor en mt", formData.biomonHeight) { viewModel.updateBiomonHeight(it) }
            FormTextField("Altura en mt", formData.height) { viewModel.updateHeight(it) }

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

@Composable
fun QuadrantButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
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
        Text(label, fontSize = 14.sp, color = if (isSelected) Color.Black else Color.Gray)
    }
}

@Composable
fun GrowthHabitButton(iconId: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
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
        Text(label, fontSize = 14.sp, color = Color.Black)
    }
}
