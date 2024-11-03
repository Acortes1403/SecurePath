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
fun FormSelect7(navController: NavController) {
    var selectedZone by remember { mutableStateOf("Bosque") }
    var pluviosidad by remember { mutableStateOf("") }
    var temperaturaMaxima by remember { mutableStateOf("") }
    var humedadMaxima by remember { mutableStateOf("") }
    var temperaturaMinima by remember { mutableStateOf("") }
    var temperaturaMinimaDos by remember { mutableStateOf("") }
    var nivelQuebrada by remember { mutableStateOf("") }

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
                ZoneRadioButtonS7("Bosque", selectedZone) { selectedZone = it }
                ZoneRadioButtonS7("Arreglo Agroforestal", selectedZone) { selectedZone = it }
                ZoneRadioButtonS7("Cultivos Transitorios", selectedZone) { selectedZone = it }
                ZoneRadioButtonS7("Cultivos Permanentes", selectedZone) { selectedZone = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextFieldS7("Pluviosidad (mm)", pluviosidad) { pluviosidad = it }
            FormTextFieldS7("Temperatura máxima", temperaturaMaxima) { temperaturaMaxima = it }
            FormTextFieldS7("Humedad máxima", humedadMaxima) { humedadMaxima = it }
            FormTextFieldS7("Temperatura mínima", temperaturaMinima) { temperaturaMinima = it }
            FormTextFieldS7("Temperatura mínima???", temperaturaMinimaDos) { temperaturaMinimaDos = it }
            FormTextFieldS7("Nivel Quebrada (mt)", nivelQuebrada) { nivelQuebrada = it }

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
fun FormTextFieldS7(label: String, value: String, onValueChange: (String) -> Unit) {
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
fun ZoneRadioButtonS7(label: String, selectedOption: String, onSelected: (String) -> Unit) {
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
