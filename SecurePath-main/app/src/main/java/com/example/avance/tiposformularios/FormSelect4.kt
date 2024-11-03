package com.example.avance.tiposformularios

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
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect4(navController: NavController) {
    // Estados para las selecciones
    var codigo by remember { mutableStateOf("") }
    var seguimiento by remember { mutableStateOf("Si") }
    var cambio by remember { mutableStateOf("Si") }
    var cobertura by remember { mutableStateOf("BD") }
    var tipoCultivo by remember { mutableStateOf("") }
    var disturbio by remember { mutableStateOf("Inundación") }
    var observaciones by remember { mutableStateOf("") }

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

            // Seguimiento
            Text("Seguimiento", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                RadioButtonOptionS4("Si", seguimiento) { seguimiento = it }
                RadioButtonOptionS4("No", seguimiento) { seguimiento = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cambió
            Text("Cambió", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                RadioButtonOptionS4("Si", cambio) { cambio = it }
                RadioButtonOptionS4("No", cambio) { cambio = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cobertura
            Text("Cobertura", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                RadioButtonOptionS4("BD", cobertura) { cobertura = it }
                RadioButtonOptionS4("RA", cobertura) { cobertura = it }
                RadioButtonOptionS4("RB", cobertura) { cobertura = it }
                RadioButtonOptionS4("PA", cobertura) { cobertura = it }
                RadioButtonOptionS4("PL", cobertura) { cobertura = it }
                RadioButtonOptionS4("CP", cobertura) { cobertura = it }
                RadioButtonOptionS4("CT", cobertura) { cobertura = it }
                RadioButtonOptionS4("VH", cobertura) { cobertura = it }
                RadioButtonOptionS4("TD", cobertura) { cobertura = it }
                RadioButtonOptionS4("IF", cobertura) { cobertura = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipos de Cultivo
            FormTextFieldS4("Tipos de Cultivo", tipoCultivo) { tipoCultivo = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Disturbio
            Text("Disturbio", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                RadioButtonOptionS4("Inundación", disturbio) { disturbio = it }
                RadioButtonOptionS4("Quema", disturbio) { disturbio = it }
                RadioButtonOptionS4("Tala", disturbio) { disturbio = it }
                RadioButtonOptionS4("Erosión", disturbio) { disturbio = it }
                RadioButtonOptionS4("Minería", disturbio) { disturbio = it }
                RadioButtonOptionS4("Carretera", disturbio) { disturbio = it }
                RadioButtonOptionS4("Más plantas acuáticas", disturbio) { disturbio = it }
                RadioButtonOptionS4("Otro", disturbio) { disturbio = it }
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
                value = observaciones,
                onValueChange = { observaciones = it },
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
fun FormTextFieldS4(label: String, value: String, onValueChange: (String) -> Unit) {
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
fun RadioButtonOptionS4(label: String, selectedOption: String, onSelected: (String) -> Unit) {
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
