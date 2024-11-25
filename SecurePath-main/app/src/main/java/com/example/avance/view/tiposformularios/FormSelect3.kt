package com.example.avance.view.tiposformularios

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel
import com.example.avance.R
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.ui.theme.SecondaryColor
import com.example.avance.view.SelectableOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect3(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel()
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.updateImageUri(uri.toString())
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Fauna Búsqueda Libre",
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
            // Código Field
            FormTextField(
                label = "Código",
                value = formData.codigof4,
                fontSize = fontSize,
                onValueChange = viewModel::updateCodigof4
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Zona Section
            Text(
                text = "Zona",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                val zones = listOf("Bosque", "Arreglo Agroforestal", "Cultivos Transitorios", "Cultivos Permanentes")
                zones.forEach { zone ->
                    SelectableOption(
                        label = zone,
                        selectedOption = formData.selectedZone,
                        fontSize = fontSize,
                        onSelected = viewModel::updateZone
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre Común and Científico Fields
            FormTextField(
                label = "Nombre Común",
                value = formData.commonName,
                fontSize = fontSize,
                onValueChange = viewModel::updateCommonName
            )

            // Nombre Científico Field
            FormTextField(
                label = "Nombre Científico",
                value = formData.scientificName,
                fontSize = fontSize,
                onValueChange = viewModel::updateScientificName
            )

            // Número de Individuos Field
            FormTextField(
                label = "Número de Individuos",
                value = formData.individualCount,
                fontSize = fontSize,
                onValueChange = viewModel::updateIndividualCount
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Observación Section
            Text(
                text = "Tipo de Observación",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                val observations = listOf("La Vió", "Huella", "Rastro", "Cacería", "Le Dijeron")
                observations.forEach { observation ->
                    SelectableOption(
                        label = observation,
                        selectedOption = formData.selectedObservation,
                        fontSize = fontSize,
                        onSelected = viewModel::updateSelectedObservation
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias Section
            Text(
                text = "Evidencias",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
            ) {
                Text("Elige archivo", color = Color.White, fontSize = fontSize.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones Field
            Text(
                text = "Observaciones",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = formData.observationNotes,
                onValueChange = { viewModel.updateObservationNotes(it) },
                placeholder = { Text("Observaciones", fontSize = fontSize.sp, color = SecondaryColor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = PrimaryColor,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("ATRÁS", color = Color.White, fontSize = fontSize.sp)
                }
                Button(
                    onClick = {
                        viewModel.saveFaunaBusquedalibre()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(
    label: String,
    value: String,
    fontSize: Float,
    modifier: Modifier = Modifier,
    isNumeric: Boolean = false, // New parameter for numeric input handling
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { input ->
            // Validate input for numeric fields
            if (!isNumeric || input.all { it.isDigit() }) {
                onValueChange(input)
            }
        },
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
        ),
        keyboardOptions = if (isNumeric) KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) else KeyboardOptions.Default
    )
}
