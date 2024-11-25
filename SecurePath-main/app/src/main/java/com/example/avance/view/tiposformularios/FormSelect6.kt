package com.example.avance.view.tiposformularios

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect6(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel() // Obtenemos fontSize desde FontSizeViewModel
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState() // Recogemos el valor de fontSize
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.updateImageUri(uri.toString()) // Guarda el URI de la imagen en el ViewModel
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cámaras Trampa", color = Color.White, fontSize = fontSize.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryColor
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
            // Zona
            Text("Zona", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                SelectableOptionS1(
                    label = "Bosque",
                    selectedOption = formData.selectedZone,
                    onSelected = { viewModel.updateZone("Bosque") },
                    fontSize = fontSize
                )
                SelectableOptionS1(
                    label = "Arreglo Agroforestal",
                    selectedOption = formData.selectedZone,
                    onSelected = { viewModel.updateZone("Arreglo Agroforestal") },
                    fontSize = fontSize
                )
                SelectableOptionS1(
                    label = "Cultivos Transitorios",
                    selectedOption = formData.selectedZone,
                    onSelected = { viewModel.updateZone("Cultivos Transitorios") },
                    fontSize = fontSize
                )
                SelectableOptionS1(
                    label = "Cultivos Permanentes",
                    selectedOption = formData.selectedZone,
                    onSelected = { viewModel.updateZone("Cultivos Permanentes") },
                    fontSize = fontSize
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextField("Nombre Cámara", formData.cameraName, fontSize) { viewModel.updateCameraName(it) }
            FormTextField("Placa Cámara", formData.cameraPlate, fontSize) { viewModel.updateCameraPlate(it) }
            FormTextField("Placa Guaya", formData.guayaPlate, fontSize) { viewModel.updateGuayaPlate(it) }
            FormTextField("Ancho Camino mt", formData.pathWidth, fontSize) { viewModel.updatePathWidth(it) }
            FormTextField("Fecha de Instalación", formData.installationDate, fontSize) { viewModel.updateInstallationDate(it) }
            FormTextField("Distancia al objetivo mt", formData.targetDistance, fontSize) { viewModel.updateTargetDistance(it) }
            FormTextField("Altura del lente mt", formData.lensHeight, fontSize) { viewModel.updateLensHeight(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de chequeo
            Text("Lista de chequeo", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                CheckboxWithLabel(
                    label = "Programada",
                    checked = formData.isProgrammed,
                    onCheckedChange = { viewModel.updateIsProgrammed(it) },
                    fontSize = fontSize
                )
                CheckboxWithLabel(
                    label = "Memoria",
                    checked = formData.hasMemory,
                    onCheckedChange = { viewModel.updateHasMemory(it) },
                    fontSize = fontSize
                )
                CheckboxWithLabel(
                    label = "Prueba de gateo",
                    checked = formData.hasGateTest,
                    onCheckedChange = { viewModel.updateHasGateTest(it) },
                    fontSize = fontSize
                )
                CheckboxWithLabel(
                    label = "Instalada",
                    checked = formData.isInstalled,
                    onCheckedChange = { viewModel.updateIsInstalled(it) },
                    fontSize = fontSize
                )
                CheckboxWithLabel(
                    label = "Letrero de cámara",
                    checked = formData.hasCameraSign,
                    onCheckedChange = { viewModel.updateHasCameraSign(it) },
                    fontSize = fontSize
                )
                CheckboxWithLabel(
                    label = "Prendida",
                    checked = formData.isOn,
                    onCheckedChange = { viewModel.updateIsOn(it) },
                    fontSize = fontSize
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { imagePickerLauncher.launch("image/*") }, // Abre la galería de imágenes
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Elige archivo", color = Color.White, fontSize = fontSize.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            Text("Observaciones", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            FormTextField0(
                label = "Observaciones",
                value = formData.observationNotes,
                onValueChange = { viewModel.updateObservationNotes(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                fontSize = fontSize
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de Acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("ATRAS", color = Color.White, fontSize = fontSize.sp)
                }
                Button(
                    onClick = {
                        viewModel.saveCamarasTrampa() //Boton para guardar datos de formulario y camaras trampa
                        navController.navigate("hola_samantha")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("ENVIAR", color = Color.White, fontSize = fontSize.sp)
                }
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, fontSize: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onCheckedChange(!checked) }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, fontSize = fontSize.sp)
    }
}

@Composable
fun FormTextField0(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: Float // Agregamos fontSize como parámetro
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = fontSize.sp) },
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp)
    )
}

@Composable
fun SelectableOptionS1(label: String, selectedOption: String, onSelected: () -> Unit, fontSize: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onSelected() }
    ) {
        RadioButton(
            selected = selectedOption == label,
            onClick = onSelected
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, fontSize = fontSize.sp)
    }
}
