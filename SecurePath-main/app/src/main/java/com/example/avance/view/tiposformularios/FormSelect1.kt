package com.example.avance.view.tiposformularios

import androidx.compose.foundation.Image
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
import com.example.avance.viewmodel.FormularioViewModel
import com.example.avance.viewmodel.FontSizeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect1(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel()
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()  // Observa el tamaño de letra global

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Faunas en Transecto", color = Color.White, fontSize = fontSize.sp) },
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
            Text("Tipo de Animal", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
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

            FormTextField("Nombre Común", formData.commonName, fontSize) { viewModel.updateCommonName(it) }
            FormTextField("Nombre Científico", formData.scientificName, fontSize) { viewModel.updateScientificName(it) }
            FormTextField("Número de Individuos", formData.individualCount, fontSize, isNumeric = true) { viewModel.updateIndividualCount(it) }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tipo de Observación", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                ObservationRadioButton("La Vió", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Huella", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Rastro", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Cacería", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
                ObservationRadioButton("Le Dijeron", formData.selectedObservation, fontSize) { viewModel.updateSelectedObservation(it) }
            }

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
                    onClick = { /* Acción para enviar el formulario */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("ENVIAR", color = Color.White, fontSize = fontSize.sp)
                }
            }
        }
    }
}

@Composable
fun AnimalButton(iconId: Int, label: String, isSelected: Boolean, onClick: () -> Unit, fontSize: Float) {
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
        Text(label, fontSize = fontSize.sp, color = Color.Black)
    }
}

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
        textStyle = LocalTextStyle.current.copy(fontSize = fontSize.sp)
    )
}

@Composable
fun ObservationRadioButton(label: String, selectedObservation: String, fontSize: Float, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelected(label) }
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        RadioButton(
            selected = (label == selectedObservation),
            onClick = { onSelected(label) }
        )
        Text(label, fontSize = fontSize.sp)
    }
}
