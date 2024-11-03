package com.example.avance.tiposformularios

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
import androidx.navigation.NavController
import com.example.avance.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect1(navController: NavController) {
    var selectedAnimal by remember { mutableStateOf<String?>(null) }
    var selectedObservation by remember { mutableStateOf("La Vió") }
    var commonName by remember { mutableStateOf("") }
    var scientificName by remember { mutableStateOf("") }
    var individualCount by remember { mutableStateOf("") }
    var observationNotes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Faunas en Transecto", color = Color.White) },
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
            // Tipo de Animal
            Text("Tipo de Animal", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AnimalButton(
                    iconId = R.drawable.ic_mammal,
                    label = "Mamífero",
                    isSelected = selectedAnimal == "Mamífero",
                    onClick = { selectedAnimal = "Mamífero" }
                )
                AnimalButton(
                    iconId = R.drawable.ic_bird,
                    label = "Ave",
                    isSelected = selectedAnimal == "Ave",
                    onClick = { selectedAnimal = "Ave" }
                )
                AnimalButton(
                    iconId = R.drawable.ic_reptile,
                    label = "Reptil",
                    isSelected = selectedAnimal == "Reptil",
                    onClick = { selectedAnimal = "Reptil" }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AnimalButton(
                    iconId = R.drawable.ic_amphibian,
                    label = "Anfibio",
                    isSelected = selectedAnimal == "Anfibio",
                    onClick = { selectedAnimal = "Anfibio" }
                )
                AnimalButton(
                    iconId = R.drawable.ic_insect,
                    label = "Insecto",
                    isSelected = selectedAnimal == "Insecto",
                    onClick = { selectedAnimal = "Insecto" }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextField("Nombre Común", commonName) { commonName = it }
            FormTextField("Nombre Científico", scientificName) { scientificName = it }
            FormTextField("Número de Individuos", individualCount, isNumeric = true) { individualCount = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Observación
            Text("Tipo de Observación", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                ObservationRadioButton("La Vió", selectedObservation) { selectedObservation = it }
                ObservationRadioButton("Huella", selectedObservation) { selectedObservation = it }
                ObservationRadioButton("Rastro", selectedObservation) { selectedObservation = it }
                ObservationRadioButton("Cacería", selectedObservation) { selectedObservation = it }
                ObservationRadioButton("Le Dijeron", selectedObservation) { selectedObservation = it }
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
                value = observationNotes,
                onValueChange = { observationNotes = it },
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
fun AnimalButton(iconId: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
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

@Composable
fun FormTextField(label: String, value: String, isNumeric: Boolean = false, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        singleLine = true
    )
}

@Composable
fun ObservationRadioButton(label: String, selectedObservation: String, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        RadioButton(
            selected = (label == selectedObservation),
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = 14.sp)
    }
}


