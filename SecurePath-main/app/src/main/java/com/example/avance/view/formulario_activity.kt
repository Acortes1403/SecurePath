package com.example.avance.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel
import androidx.navigation.compose.rememberNavController
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.ui.theme.SecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    navController: NavController,
    viewModel: FormularioViewModel,
    fontSizeViewModel: FontSizeViewModel
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()
    var selectedWeather by remember { mutableStateOf("Soleado") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Formulario",
                        color = Color.White,
                        fontSize = fontSize.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
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
                    containerColor = PrimaryColor // Use app's primary color
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
            FormTextField(
                label = "Nombre",
                value = formData.name,
                fontSize = fontSize,
                onValueChange = viewModel::updateName
            )
            FormTextField(
                label = "Fecha",
                value = formData.date,
                fontSize = fontSize,
                onValueChange = viewModel::updateDate
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                FormTextField(
                    label = "Localidad",
                    value = formData.location,
                    fontSize = fontSize,
                    onValueChange = viewModel::updateLocation,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { /* Acción para abrir el mapa o buscar localización */ },
                    modifier = Modifier.size(48.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_map),
                        contentDescription = "Buscar ubicación"
                    )
                }
            }

            FormTextField(
                label = "Hora",
                value = formData.hora,
                fontSize = fontSize,
                onValueChange = viewModel::updateHora
            )
            FormTextField(
                label = "Número de Transecto",
                value = formData.transectNumber,
                fontSize = fontSize,
                onValueChange = viewModel::updateTransectNumber
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Weather Condition Section
            Text(
                text = "Estado del Tiempo",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                WeatherIcon(
                    iconId = R.drawable.ic_soleado,
                    description = "Soleado",
                    isSelected = selectedWeather == "Soleado",
                    fontSize = fontSize,
                    onClick = { selectedWeather = "Soleado" }
                )
                WeatherIcon(
                    iconId = R.drawable.ic_nublado,
                    description = "Nublado",
                    isSelected = selectedWeather == "Nublado",
                    fontSize = fontSize,
                    onClick = { selectedWeather = "Nublado" }
                )
                WeatherIcon(
                    iconId = R.drawable.ic_lluvioso,
                    description = "Lluvioso",
                    isSelected = selectedWeather == "Lluvioso",
                    fontSize = fontSize,
                    onClick = { selectedWeather = "Lluvioso" }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tipo de Registro",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                val options = listOf(
                    "Fauna en Transectos",
                    "Fauna en Punto de Conteo",
                    "Fauna Búsqueda Libre",
                    "Validación de Cobertura",
                    "Parcela de Vegetación",
                    "Cámaras Trampa",
                    "Variables Climáticas"
                )
                options.forEach { option ->
                    SelectableOption(
                        label = option,
                        selectedOption = formData.tipoDeRegistro,
                        fontSize = fontSize,
                        onSelected = { viewModel.updateRegistro(it) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    when (formData.tipoDeRegistro) {
                        "Fauna en Transectos" -> navController.navigate("form_1")
                        "Fauna en Punto de Conteo" -> navController.navigate("form_2")
                        "Fauna Búsqueda Libre" -> navController.navigate("form_3")
                        "Validación de Cobertura" -> navController.navigate("form_4")
                        "Parcela de Vegetación" -> navController.navigate("form_5")
                        "Cámaras Trampa" -> navController.navigate("form_6")
                        "Variables Climáticas" -> navController.navigate("form_7")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
            ) {
                Text("SIGUIENTE", color = Color.White, fontSize = fontSize.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(label: String, value: String, fontSize: Float, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = fontSize.sp, color = PrimaryColor) },
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = PrimaryColor,
            unfocusedIndicatorColor = Color.Gray
        )
    )
}

@Composable
fun WeatherIcon(
    iconId: Int,
    description: String,
    isSelected: Boolean,
    fontSize: Float,
    onClick: () -> Unit
) {
    val iconSize = fontSize.dp * 8 // Adjust size based on fontSize

    Box(
        modifier = Modifier
            .size(iconSize)
            .padding(4.dp)
            .background(
                color = if (isSelected) Color(0xFFDCE775) else Color.Transparent, // Highlight color
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = description,
            modifier = Modifier.size(iconSize * 0.8f) // Slightly smaller to fit inside the background
        )
    }
}

@Composable
fun SelectableOption(label: String, selectedOption: String?, fontSize: Float, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = (label == selectedOption),
            onClick = { onSelected(label) },
            colors = RadioButtonDefaults.colors(
                selectedColor = PrimaryColor,
                unselectedColor = SecondaryColor
            )
        )
        Text(label, fontSize = fontSize.sp, color = if (label == selectedOption) PrimaryColor else Color.Gray)
    }
}



