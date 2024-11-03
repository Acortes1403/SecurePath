package com.example.avance

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(navController: NavController) {
    // Estado para almacenar el tipo de registro seleccionado
    var selectedRegistro by remember { mutableStateOf<String?>(null) }

    // Estados para los campos de texto editables
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var localidad by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var numeroTransecto by remember { mutableStateOf("") }

    // Estado del tiempo y época
    var selectedWeather by remember { mutableStateOf<String?>(null) }
    var selectedEpoch by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario", color = Color.White) },
                navigationIcon = {
                    Text(
                        text = "<",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { navController.popBackStack() }
                    )
                },
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Campos del formulario con los parámetros correctos
            FormTextField(label = "Nombre", value = nombre, onValueChange = { nombre = it })
            FormTextField(label = "Fecha", value = fecha, onValueChange = { fecha = it })

            // Campo de Localidad con botón de ícono
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                FormTextField(
                    label = "Localidad",
                    value = localidad,
                    onValueChange = { localidad = it },
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { /* Acción para abrir el mapa o buscar localización */ },
                    modifier = Modifier.size(40.dp).padding(start = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_map),
                        contentDescription = "Buscar ubicación"
                    )
                }
            }

            FormTextField(label = "Hora", value = hora, onValueChange = { hora = it })
            FormTextField(label = "Número de Transecto", value = numeroTransecto, onValueChange = { numeroTransecto = it })

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Estado del Tiempo (con iconos)
            Text("Estado del Tiempo:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherOption(
                    iconId = R.drawable.ic_soleado,
                    label = "Soleado",
                    selectedOption = selectedWeather,
                    onSelected = { selectedWeather = it }
                )
                WeatherOption(
                    iconId = R.drawable.ic_nublado,
                    label = "Nublado",
                    selectedOption = selectedWeather,
                    onSelected = { selectedWeather = it }
                )
                WeatherOption(
                    iconId = R.drawable.ic_lluvioso,
                    label = "Lluvioso",
                    selectedOption = selectedWeather,
                    onSelected = { selectedWeather = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Época
            Text("Época", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                EpochOption(
                    label = "Verano/Seca",
                    selectedOption = selectedEpoch,
                    onSelected = { selectedEpoch = it }
                )
                EpochOption(
                    label = "Invierno/Lluviosa",
                    selectedOption = selectedEpoch,
                    onSelected = { selectedEpoch = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Tipo de Registro
            Text("Tipo de Registro", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                SelectableOption(
                    label = "Fauna en Transectos",
                    selectedOption = selectedRegistro,
                    onSelected = { selectedRegistro = it }
                )
                SelectableOption(
                    label = "Fauna en Punto de Conteo",
                    selectedOption = selectedRegistro,
                    onSelected = { selectedRegistro = it }
                )
                SelectableOption(
                    label = "Fauna Búsqueda Libre",
                    selectedOption = selectedRegistro,
                    onSelected = { selectedRegistro = it }
                )
                SelectableOption(
                    label = "Validación de Cobertura",
                    selectedOption = selectedRegistro,
                    onSelected = { selectedRegistro = it }
                )
                SelectableOption(
                    label = "Parcela de Vegetación",
                    selectedOption = selectedRegistro,
                    onSelected = { selectedRegistro = it }
                )
                SelectableOption(
                    label = "Cámaras Trampa",
                    selectedOption = selectedRegistro,
                    onSelected = { selectedRegistro = it }
                )
                SelectableOption(
                    label = "Variables Climáticas",
                    selectedOption = selectedRegistro,
                    onSelected = { selectedRegistro = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Siguiente" en la parte inferior de la pantalla
            Button(
                onClick = {
                    when (selectedRegistro) {
                        "Fauna en Transectos" -> navController.navigate("form_1")
                        "Fauna en Punto de Conteo" -> navController.navigate("form_2")
                        "Fauna Búsqueda Libre" -> navController.navigate("form_3")
                        "Validación de Cobertura" -> navController.navigate("form_4")
                        "Parcela de Vegetación" -> navController.navigate("form_5")
                        "Cámaras Trampa" -> navController.navigate("form_6")
                        "Variables Climáticas" -> navController.navigate("form_7")
                        else -> { /* Muestra un mensaje de error o realiza otra acción */ }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("SIGUIENTE", color = Color.White)
            }
        }
    }
}


@Composable
fun FormTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        singleLine = true
    )
}

@Composable
fun WeatherOption(iconId: Int, label: String, selectedOption: String?, onSelected: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onSelected(label) }
    ) {
        IconButton(
            onClick = { onSelected(label) },
            modifier = Modifier.size(64.dp)
        ) {
            Image(painter = painterResource(id = iconId), contentDescription = label)
        }
        Text(label, fontSize = 12.sp, color = if (selectedOption == label) Color.Green else Color.Black)
    }
}

@Composable
fun EpochOption(label: String, selectedOption: String?, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = (label == selectedOption),
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = 14.sp)
    }
}

@Composable
fun SelectableOption(label: String, selectedOption: String?, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = (label == selectedOption),
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormularioScreen() {
    FormularioScreen(navController = rememberNavController())
}