package com.example.avance.tiposformularios

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
fun FormSelect6(navController: NavController) {
    var codigo by remember { mutableStateOf("") }
    var selectedZone by remember { mutableStateOf("Bosque") }
    var cameraName by remember { mutableStateOf("") }
    var cameraPlate by remember { mutableStateOf("") }
    var guayaPlate by remember { mutableStateOf("") }
    var roadWidth by remember { mutableStateOf("") }
    var installationDate by remember { mutableStateOf("") }
    var distanceToObjective by remember { mutableStateOf("") }
    var lensHeight by remember { mutableStateOf("") }
    var observationNotes by remember { mutableStateOf("") }

    // Estados para la lista de chequeo
    var programmed by remember { mutableStateOf(false) }
    var memory by remember { mutableStateOf(false) }
    var crawlTest by remember { mutableStateOf(false) }
    var installed by remember { mutableStateOf(false) }
    var cameraSign by remember { mutableStateOf(false) }
    var onStatus by remember { mutableStateOf(false) }

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
            // Campo de Código
            FormTextField("Código", codigo) { codigo = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Zona
            Text("Zona", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ZoneRadioButtonS6("Bosque", selectedZone) { selectedZone = it }
                ZoneRadioButtonS6("Arreglo Agroforestal", selectedZone) { selectedZone = it }
                ZoneRadioButtonS6("Cultivos Transitorios", selectedZone) { selectedZone = it }
                ZoneRadioButtonS6("Cultivos Permanentes", selectedZone) { selectedZone = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextFieldS6("Nombre Cámara", cameraName) { cameraName = it }
            FormTextFieldS6("Placa Cámara", cameraPlate) { cameraPlate = it }
            FormTextFieldS6("Placa Guaya", guayaPlate) { guayaPlate = it }
            FormTextFieldS6("Ancho Camino mt", roadWidth) { roadWidth = it }
            FormTextFieldS6("Fecha de Instalación", installationDate) { installationDate = it }
            FormTextFieldS6("Distancia al objetivo mt", distanceToObjective) { distanceToObjective = it }
            FormTextFieldS6("Altura del lente mt", lensHeight) { lensHeight = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Chequeo
            Text("Lista de chequeo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                CheckboxWithLabelS6(label = "Programada", checked = programmed, onCheckedChange = { programmed = it })
                CheckboxWithLabelS6(label = "Memoria", checked = memory, onCheckedChange = { memory = it })
                CheckboxWithLabelS6(label = "Prueba de gateo", checked = crawlTest, onCheckedChange = { crawlTest = it })
                CheckboxWithLabelS6(label = "Instalada", checked = installed, onCheckedChange = { installed = it })
                CheckboxWithLabelS6(label = "Letrero de cámara", checked = cameraSign, onCheckedChange = { cameraSign = it })
                CheckboxWithLabelS6(label = "Prendida", checked = onStatus, onCheckedChange = { onStatus = it })
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
fun FormTextFieldS6(label: String, value: String, onValueChange: (String) -> Unit) {
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
fun ZoneRadioButtonS6(label: String, selectedOption: String, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        RadioButton(
            selected = (label == selectedOption),
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = 14.sp)
    }
}

@Composable
fun CheckboxWithLabelS6(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(label, fontSize = 14.sp)
    }
}
