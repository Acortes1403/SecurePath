package com.example.avance.view.tiposformularios

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
import com.example.avance.view.SelectableOption
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect6(navController: NavController, viewModel: FormularioViewModel= viewModel()) {
    val formData = viewModel.formData.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cámaras Trampa", color = Color.White) },
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
            // Zona
            Text("Zona", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                SelectableOption(
                    label = "Bosque",
                    selectedOption = formData.zone,
                    onSelected = { viewModel.updateZone("Bosque") }
                )
                SelectableOption(
                    label = "Arreglo Agroforestal",
                    selectedOption = formData.zone,
                    onSelected = { viewModel.updateZone("Arreglo Agroforestal") }
                )
                SelectableOption(
                    label = "Cultivos Transitorios",
                    selectedOption = formData.zone,
                    onSelected = { viewModel.updateZone("Cultivos Transitorios") }
                )
                SelectableOption(
                    label = "Cultivos Permanentes",
                    selectedOption = formData.zone,
                    onSelected = { viewModel.updateZone("Cultivos Permanentes") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextField("Nombre Cámara", formData.cameraName) { viewModel.updateCameraName(it) }
            FormTextField("Placa Cámara", formData.cameraPlate) { viewModel.updateCameraPlate(it) }
            FormTextField("Placa Guaya", formData.guayaPlate) { viewModel.updateGuayaPlate(it) }
            FormTextField("Ancho Camino mt", formData.pathWidth) { viewModel.updatePathWidth(it) }
            FormTextField("Fecha de Instalación", formData.installationDate) { viewModel.updateInstallationDate(it) }
            FormTextField("Distancia al objetivo mt", formData.targetDistance) { viewModel.updateTargetDistance(it) }
            FormTextField("Altura del lente mt", formData.lensHeight) { viewModel.updateLensHeight(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de chequeo
            Text("Lista de chequeo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                CheckboxWithLabel(
                    label = "Programada",
                    checked = formData.isProgrammed,
                    onCheckedChange = { viewModel.updateIsProgrammed(it) }
                )
                CheckboxWithLabel(
                    label = "Memoria",
                    checked = formData.hasMemory,
                    onCheckedChange = { viewModel.updateHasMemory(it) }
                )
                CheckboxWithLabel(
                    label = "Prueba de gateo",
                    checked = formData.hasGateTest,
                    onCheckedChange = { viewModel.updateHasGateTest(it) }
                )
                CheckboxWithLabel(
                    label = "Instalada",
                    checked = formData.isInstalled,
                    onCheckedChange = { viewModel.updateIsInstalled(it) }
                )
                CheckboxWithLabel(
                    label = "Letrero de cámara",
                    checked = formData.hasCameraSign,
                    onCheckedChange = { viewModel.updateHasCameraSign(it) }
                )
                CheckboxWithLabel(
                    label = "Prendida",
                    checked = formData.isOn,
                    onCheckedChange = { viewModel.updateIsOn(it) }
                )
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
            FormTextField0(
                label = "Observaciones",
                value = formData.observations,
                onValueChange = { viewModel.updateObservations(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
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
fun CheckboxWithLabel(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
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
        Text(text = label, fontSize = 14.sp)
    }
}
@Composable
fun FormTextField0(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier  // Agrega el parámetro modifier con un valor predeterminado
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier   // Ahora puedes usar el modificador aquí
    )
}
