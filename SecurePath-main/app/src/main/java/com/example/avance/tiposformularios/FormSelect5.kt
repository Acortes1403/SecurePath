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
fun FormSelect5(navController: NavController) {
    var codigo by remember { mutableStateOf("") }
    var selectedCuadrante by remember { mutableStateOf("A") }
    var selectedSubCuadrante by remember { mutableStateOf("1") }
    var selectedHabit by remember { mutableStateOf("Arbusto") }
    var commonName by remember { mutableStateOf("") }
    var scientificName by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var circumference by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var biomonitorHeight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
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
            // Campo de Código
            FormTextFieldS5("Código", codigo) { codigo = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Cuadrante
            Text("Cuadrante", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CuadranteButton("A", selectedCuadrante) { selectedCuadrante = it }
                CuadranteButton("B", selectedCuadrante) { selectedCuadrante = it }
                CuadranteButton("C", selectedCuadrante) { selectedCuadrante = it }
                CuadranteButton("D", selectedCuadrante) { selectedCuadrante = it }
                CuadranteButton("E", selectedCuadrante) { selectedCuadrante = it }
                CuadranteButton("F", selectedCuadrante) { selectedCuadrante = it }
                CuadranteButton("G", selectedCuadrante) { selectedCuadrante = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sub-Cuadrante
            Text("Sub-Cuadrante", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SubCuadranteButton("1", selectedSubCuadrante) { selectedSubCuadrante = it }
                SubCuadranteButton("2", selectedSubCuadrante) { selectedSubCuadrante = it }
                SubCuadranteButton("3", selectedSubCuadrante) { selectedSubCuadrante = it }
                SubCuadranteButton("4", selectedSubCuadrante) { selectedSubCuadrante = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hábito de Crecimiento
            Text("Hábito de crecimiento", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                HabitButton(iconId = R.drawable.ic_arbusto, label = "Arbusto <1mt", isSelected = selectedHabit == "Arbusto") { selectedHabit = "Arbusto" }
                HabitButton(iconId = R.drawable.ic_arbolito, label = "Arbolito 1-3mt", isSelected = selectedHabit == "Arbolito") { selectedHabit = "Arbolito" }
                HabitButton(iconId = R.drawable.ic_arbol, label = "Arbol >3mt", isSelected = selectedHabit == "Arbol") { selectedHabit = "Arbol" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextFieldS5("Nombre Común Especie", commonName) { commonName = it }
            FormTextFieldS5("Nombre Científico", scientificName) { scientificName = it }
            FormTextFieldS5("Placa", placa) { placa = it }
            FormTextFieldS5("Circunferencia en cm (CL)", circumference) { circumference = it }
            FormTextFieldS5("Distancia en mt", distance) { distance = it }
            FormTextFieldS5("Estatura Biomonitor en mt", biomonitorHeight) { biomonitorHeight = it }
            FormTextFieldS5("Altura en mt", height) { height = it }

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
fun FormTextFieldS5(label: String, value: String, onValueChange: (String) -> Unit) {
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
fun CuadranteButton(label: String, selectedOption: String, onSelected: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .border(
                width = 2.dp,
                color = if (label == selectedOption) Color(0xFFA4C639) else Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onSelected(label) },
        contentAlignment = Alignment.Center
    ) {
        Text(label, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun SubCuadranteButton(label: String, selectedOption: String, onSelected: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .border(
                width = 2.dp,
                color = if (label == selectedOption) Color(0xFFA4C639) else Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onSelected(label) },
        contentAlignment = Alignment.Center
    ) {
        Text(label, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun HabitButton(iconId: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
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
