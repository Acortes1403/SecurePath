package com.example.avance.view.tiposformularios

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.model.FormularioBase
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.ui.theme.SecondaryColor
import com.example.avance.viewmodel.FormularioViewModel
import com.example.avance.viewmodel.FontSizeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect1(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel() ,
    isEditing: Boolean = false, // Pass editing state
    formularioToEdit: FormularioBase? = null // Pass the form to edit, if applicable
) {
    LaunchedEffect(Unit) {
        viewModel.fetchFormularios()
        }

    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()  // Observa el tamaño de letra global
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
                title = {
                    Text(
                        "Faunas en Transecto",
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Animal Type Section
            Text(
                text = "Tipo de Animal",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AnimalButton(
                    iconId = R.drawable.ic_mammal,
                    label = "Mamífero",
                    isSelected = formData.selectedAnimal == "Mamífero",
                    onClick = { viewModel.updateSelectedAnimal("Mamífero") },
                    fontSize = fontSize
                )
                AnimalButton(
                    iconId = R.drawable.ic_bird,
                    label = "Ave",
                    isSelected = formData.selectedAnimal == "Ave",
                    onClick = { viewModel.updateSelectedAnimal("Ave") },
                    fontSize = fontSize
                )
                AnimalButton(
                    iconId = R.drawable.ic_reptile,
                    label = "Reptil",
                    isSelected = formData.selectedAnimal == "Reptil",
                    onClick = { viewModel.updateSelectedAnimal("Reptil") },
                    fontSize = fontSize
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Text Fields
            FormTextField("Nombre Común", formData.commonName, fontSize) { viewModel.updateCommonName(it) }
            FormTextField("Nombre Científico", formData.scientificName, fontSize) { viewModel.updateScientificName(it) }
            FormTextField(
                "Número de Individuos",
                formData.individualCount,
                fontSize,
                isNumeric = true
            ) { viewModel.updateIndividualCount(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Observation Section
            Text(
                text = "Tipo de Observación",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                val observations = listOf("La Vió", "Huella", "Rastro", "Cacería", "Le Dijeron")
                observations.forEach { observation ->
                    ObservationRadioButton(
                        label = observation,
                        selectedObservation = formData.selectedObservation,
                        fontSize = fontSize
                    ) { viewModel.updateSelectedObservation(it) }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidence Section
            Text(
                text = "Evidencias",
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Elige archivo", color = Color.White, fontSize = fontSize.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation Buttons
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
                        viewModel.saveFaunaTransecto()
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
fun AnimalButton(iconId: Int, label: String, isSelected: Boolean, onClick: () -> Unit, fontSize: Float) {
    Box(
        modifier = Modifier
            .size(fontSize.dp * 8)
            .background(
                color = if (isSelected) Color(0xFFDCE775) else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = label,
                modifier = Modifier.size(fontSize.dp * 6)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, fontSize = fontSize.sp, color = if (isSelected) PrimaryColor else Color.Gray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(label: String, value: String, fontSize: Float, isNumeric: Boolean = false, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = fontSize.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        singleLine = true,
        keyboardOptions = if (isNumeric) {
            KeyboardOptions(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions.Default
        },
        textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = PrimaryColor,
            unfocusedIndicatorColor = Color.Gray
        )
    )
}

@Composable
fun ObservationRadioButton(label: String, selectedObservation: String, fontSize: Float, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(label) }
            .padding(8.dp)
    ) {
        RadioButton(
            selected = label == selectedObservation,
            onClick = { onSelected(label) },
            colors = RadioButtonDefaults.colors(
                selectedColor = PrimaryColor,
                unselectedColor = SecondaryColor
            )
        )
        Text(label, fontSize = fontSize.sp, color = if (label == selectedObservation) PrimaryColor else Color.Gray)
    }
}
