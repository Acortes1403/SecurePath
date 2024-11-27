package com.example.avance.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResultLauncher
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import androidx.activity.compose.rememberLauncherForActivityResult



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    navController: NavController,
    viewModel: FormularioViewModel,
    fontSizeViewModel: FontSizeViewModel
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Lanzador de permiso para ubicación
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                // Permiso concedido, obtener ubicación
                obtenerUbicacion(fusedLocationClient, viewModel)
            } else {
                // Permiso denegado
            }
        }
    )

    // Comprobar si ya tenemos permiso
    LaunchedEffect(context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacion(fusedLocationClient, viewModel)
        } else {
            requestPermissionLauncher?.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario", color = Color.White, fontSize = fontSize.sp) },
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
            FormTextField("Nombre", formData.name, fontSize = fontSize, onValueChange = viewModel::updateName)
            FormTextField("Fecha", formData.date, fontSize = fontSize, onValueChange = viewModel::updateDate)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                FormTextField(
                    "Localidad",
                    formData.location,
                    fontSize = fontSize,
                    onValueChange = viewModel::updateLocation,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { obtenerUbicacion(fusedLocationClient, viewModel) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_map),
                        contentDescription = "Buscar ubicación"
                    )
                }
            }

            FormTextField("Hora", formData.hora, fontSize = fontSize, onValueChange = viewModel::updateTime)
            FormTextField("Número de Transecto", formData.transectNumber, fontSize = fontSize, onValueChange = viewModel::updateTransectNumber)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Estado del Tiempo", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherIcon(iconId = R.drawable.ic_soleado, description = "Soleado")
                WeatherIcon(iconId = R.drawable.ic_nublado, description = "Nublado")
                WeatherIcon(iconId = R.drawable.ic_lluvioso, description = "Lluvioso")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tipo de Registro", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                SelectableOption("Fauna en Transectos", formData.tipoDeRegistro, fontSize) { viewModel.updateRegistro("Fauna en Transectos") }
                SelectableOption("Fauna en Punto de Conteo", formData.tipoDeRegistro, fontSize) { viewModel.updateRegistro("Fauna en Punto de Conteo") }
                SelectableOption("Fauna Búsqueda Libre", formData.tipoDeRegistro, fontSize) { viewModel.updateRegistro("Fauna Búsqueda Libre") }
                SelectableOption("Validación de Cobertura", formData.tipoDeRegistro, fontSize) { viewModel.updateRegistro("Validación de Cobertura") }
                SelectableOption("Parcela de Vegetación", formData.tipoDeRegistro, fontSize) { viewModel.updateRegistro("Parcela de Vegetación") }
                SelectableOption("Cámaras Trampa", formData.tipoDeRegistro, fontSize) { viewModel.updateRegistro("Cámaras Trampa") }
                SelectableOption("Variables Climáticas", formData.tipoDeRegistro, fontSize) { viewModel.updateRegistro("Variables Climáticas") }
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
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("SIGUIENTE", color = Color.White, fontSize = fontSize.sp)
            }
        }
    }
}

@Composable
fun FormTextField(label: String, value: String, fontSize: Float, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = fontSize.sp) },
        modifier = modifier.fillMaxWidth().padding(4.dp),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp)
    )
}

@Composable
fun WeatherIcon(iconId: Int, description: String, onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = description,
            modifier = Modifier.size(48.dp)
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
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = fontSize.sp)
    }
}

// Función para obtener la ubicación
private fun obtenerUbicacion(fusedLocationClient: FusedLocationProviderClient, viewModel: FormularioViewModel) {
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val coordenadas = "${it.latitude}, ${it.longitude}"
                viewModel.updateLocation(coordenadas)
            }
        }
    } catch (e: SecurityException) {
        // Manejo de excepción si no se tiene permiso
        e.printStackTrace()
    }
}