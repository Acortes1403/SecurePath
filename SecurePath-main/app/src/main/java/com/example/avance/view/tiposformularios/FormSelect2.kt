package com.example.avance.view.tiposformularios

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect2(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel()
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()


    // Estado para mostrar el mensaje
    var selectedImageMessage by remember { mutableStateOf<String?>(null) }

    // Configura el ActivityResultLauncher para abrir la galería de imágenes
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.updateImageUri(uri.toString()) // Guarda el URI de la imagen en el ViewModel
            selectedImageMessage = "Imagen seleccionada con éxito" // Actualiza el mensaje
        } else {
            selectedImageMessage = "No se seleccionó ninguna imagen" // Mensaje opcional
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fauna en Punto de Conteo", color = Color.White, fontSize = fontSize.sp) },
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
            FormTextField("Código", formData.codigof4, fontSize) { viewModel.updateCodigof4(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Zona
            Text("Zona", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                ObservationRadioButton("Bosque", formData.selectedZone, fontSize) { viewModel.updateZone(it) }
                ObservationRadioButton("Arreglo Agroforestal", formData.selectedZone, fontSize) { viewModel.updateZone(it) }
                ObservationRadioButton("Cultivos Transitorios", formData.selectedZone, fontSize) { viewModel.updateZone(it) }
                ObservationRadioButton("Cultivos Permanentes", formData.selectedZone, fontSize) { viewModel.updateZone(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre Común
            FormTextField("Nombre Común", formData.commonName, fontSize) { viewModel.updateCommonName(it) }

            // Nombre Científico
            FormTextField("Nombre Científico", formData.scientificName, fontSize) { viewModel.updateScientificName(it) }

            // Número de Individuos
            FormTextField("Número de Individuos", formData.individualCount, fontSize, isNumeric = true) { viewModel.updateIndividualCount(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Observación
            Text("Tipo de Observación", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                ObservationRadioButton("La Vió", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Huella", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Rastro", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Cacería", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Le Dijeron", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { imagePickerLauncher.launch("image/*") }, // Abre la galería de imágenes
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5E23)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Elige archivo", color = Color.White, fontSize = fontSize.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            Text("Observaciones", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = formData.observationNotes,
                onValueChange = { viewModel.updateObservationNotes(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = { Text("Observaciones", fontSize = fontSize.sp) },
                textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp)
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
                    Text("ATRAS", color = Color.White, fontSize = fontSize.sp)
                }
                Button(
                    onClick = {
                        viewModel.saveFaunaConteo()
                        navController.navigate("hola_samantha")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("ENVIAR", color = Color.White, fontSize = fontSize.sp)
                }
            }
        }
    }
}
