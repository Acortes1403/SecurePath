package com.example.avance.tiposformularios

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect3(navController: NavController) {
    var selectedZone by remember { mutableStateOf("Bosque") }
    var selectedAnimal by remember { mutableStateOf<String?>(null) }
    var commonName by remember { mutableStateOf("") }
    var scientificName by remember { mutableStateOf("") }
    var individualCount by remember { mutableStateOf("") }
    var selectedObservation by remember { mutableStateOf("La Vió") }
    var selectedAltitude by remember { mutableStateOf("Baja <1mt") }
    var observationNotes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFA4C639)
                ),
                navigationIcon = {
                    Text(
                        text = "<",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { navController.popBackStack() }
                    )
                }
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
            Text("Zona", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ZoneRadioButtonS3("Bosque", selectedZone) { selectedZone = it }
                ZoneRadioButtonS3("Arreglo Agroforestal", selectedZone) { selectedZone = it }
                ZoneRadioButtonS3("Cultivos Transitorios", selectedZone) { selectedZone = it }
                ZoneRadioButtonS3("Cultivos Permanentes", selectedZone) { selectedZone = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Animal
            Text("Tipo de Animal", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AnimalButtonS3(iconId = R.drawable.ic_mammal, label = "Mamífero", isSelected = selectedAnimal == "Mamífero") { selectedAnimal = "Mamífero" }
                AnimalButtonS3(iconId = R.drawable.ic_bird, label = "Ave", isSelected = selectedAnimal == "Ave") { selectedAnimal = "Ave" }
                AnimalButtonS3(iconId = R.drawable.ic_reptile, label = "Reptil", isSelected = selectedAnimal == "Reptil") { selectedAnimal = "Reptil" }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AnimalButtonS3(iconId = R.drawable.ic_amphibian, label = "Anfibio", isSelected = selectedAnimal == "Anfibio") { selectedAnimal = "Anfibio" }
                AnimalButtonS3(iconId = R.drawable.ic_insect, label = "Insecto", isSelected = selectedAnimal == "Insecto") { selectedAnimal = "Insecto" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextFieldS3("Nombre Común", commonName) { commonName = it }
            FormTextFieldS3("Nombre Científico", scientificName) { scientificName = it }
            FormTextFieldS3("Número de Individuos", individualCount, isNumeric = true) { individualCount = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Observación
            Text("Tipo de Observación", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButtonS3("La Vió", selectedObservation) { selectedObservation = it }
                ObservationRadioButtonS3("Huella", selectedObservation) { selectedObservation = it }
                ObservationRadioButtonS3("Rastro", selectedObservation) { selectedObservation = it }
                ObservationRadioButtonS3("Cacería", selectedObservation) { selectedObservation = it }
                ObservationRadioButtonS3("Le Dijeron", selectedObservation) { selectedObservation = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Altura de Observación
            Text("Altura de Observación", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                AltitudeRadioButtonS3("Baja <1mt", selectedAltitude) { selectedAltitude = it }
                AltitudeRadioButtonS3("Media 1-3mt", selectedAltitude) { selectedAltitude = it }
                AltitudeRadioButtonS3("Alta >3mt", selectedAltitude) { selectedAltitude = it }
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
fun ZoneRadioButtonS3(label: String, selectedZone: String, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = (label == selectedZone),
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = 14.sp)
    }
}

@Composable
fun AnimalButtonS3(iconId: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
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
fun FormTextFieldS3(label: String, value: String, isNumeric: Boolean = false, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        singleLine = true,
        keyboardOptions = if (isNumeric) {
            KeyboardOptions(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions.Default
        }
    )
}

@Composable
fun ObservationRadioButtonS3(label: String, selectedObservation: String, onSelected: (String) -> Unit) {
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

@Composable
fun AltitudeRadioButtonS3(label: String, selectedAltitude: String, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        RadioButton(
            selected = (label == selectedAltitude),
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = 14.sp)
    }
}