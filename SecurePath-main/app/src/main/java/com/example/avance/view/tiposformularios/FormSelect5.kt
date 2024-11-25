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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.viewmodel.FontSizeViewModel
import com.example.avance.viewmodel.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect5(
    navController: NavController,
    viewModel: FormularioViewModel = viewModel(),
    fontSizeViewModel: FontSizeViewModel = viewModel() // Obtain fontSize from FontSizeViewModel
) {
    val formData = viewModel.formData.value
    val fontSize by fontSizeViewModel.fontSize.collectAsState()
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.updateImageUri(it.toString()) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parcela de Vegetación", fontSize = 20.sp, color = Color.White) },
                navigationIcon = {
                    Text(
                        text = "<",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .clickable { navController.popBackStack() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor)
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

            // Quadrant Section
            Text(text = "Cuadrante", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            QuadrantSection(
                selectedABQuadrant = formData.selectedABQuadrant,
                selectedCGQuadrant = formData.selectedCGQuadrant,
                onABClick = { abQuadrant ->
                    viewModel.updateSelectedQuadrant("AB") // Ensure this updates the AB quadrant
                    viewModel.updateSelectedABQuadrant(abQuadrant) // Update selectedABQuadrant directly
                },
                onCGClick = { cgQuadrant ->
                    viewModel.updateSelectedQuadrant("CG") // Ensure this updates the CG quadrant
                    viewModel.updateSelectedCGQuadrant(cgQuadrant) // Update selectedCGQuadrant directly
                },
                fontSize = fontSize
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sub-Cuadrante
            Text(text = "Sub-Cuadrante", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                listOf("1", "2", "3", "4").forEach { label ->
                    QuadrantButton(
                        label = label,
                        isSelected = formData.selectedSubQuadrant == label,
                        fontSize = fontSize,
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .size(fontSize.dp * 5) // Larger size for better visibility
                    ) {
                        viewModel.updateSelectedSubQuadrant(label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hábito de crecimiento
            Text("Hábito de crecimiento", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                listOf(
                    R.drawable.ic_arbusto to "Arbusto < 1mt",
                    R.drawable.ic_arbolito to "Arbolito 1-3 mt",
                    R.drawable.ic_arbol to "Árbol > 3mt"
                ).forEach { (iconId, label) ->
                    GrowthHabitButton(
                        iconId = iconId,
                        label = label,
                        isSelected = formData.selectedGrowthHabit == label,
                        fontSize = fontSize,
                        onClick = { viewModel.updateSelectedGrowthHabit(label) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Texto
            FormTextField("Nombre Común Especie", formData.commonName, fontSize) { viewModel.updateCommonName(it) }
            FormTextField("Nombre Científico", formData.scientificName, fontSize) { viewModel.updateScientificName(it) }
            FormTextField("Placa", formData.placa, fontSize) { viewModel.updatePlaca(it) }
            FormTextField("Circunferencia en cm (CL)", formData.circunference, fontSize) { viewModel.updateCircunference(it) }
            FormTextField("Distancia en mt", formData.distance, fontSize) { viewModel.updateDistance(it) }
            FormTextField("Estatura Biomonitor en mt", formData.biomonHeight, fontSize) { viewModel.updateBiomonHeight(it) }
            FormTextField("Altura en mt", formData.height, fontSize) { viewModel.updateHeight(it) }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
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
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("ATRAS", color = Color.White, fontSize = fontSize.sp)
                }
                Button(
                    onClick = {
                        viewModel.saveParcelaVegetacion()
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
fun QuadrantButton(
    label: String,
    isSelected: Boolean,
    fontSize: Float,
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFFA8D8A2) else Color.Gray,
                shape = shape
            )
            .background(
                color = if (isSelected) Color(0xFFA8D8A2) else Color.Transparent,
                shape = shape
            )
    ) {
        Text(
            text = label,
            fontSize = fontSize.sp,
            color = if (isSelected) Color.Black else Color.Gray
        )
    }
}


@Composable
fun GrowthHabitButton(
    iconId: Int,
    label: String,
    isSelected: Boolean,
    fontSize: Float,
    onClick: () -> Unit
) {
    val iconSize = fontSize.dp * 8
    Column(
        modifier = Modifier
            .padding(8.dp)
            .size(iconSize) // Adjust size for button
            .background(
                color = if (isSelected) Color(0xFFDCE775) else Color.Transparent, // Highlight color when selected
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon
        Image(
            painter = painterResource(id = iconId),
            contentDescription = label,
            modifier = Modifier
                .weight(1f) // Use weight to push the icon to the top
                .fillMaxWidth()
                .padding(8.dp)
        )
        // Label
        Text(
            text = label,
            fontSize = fontSize.sp,
            color = if (isSelected) Color.Black else Color.Gray, // Highlight text if selected
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(bottom = 8.dp) // Ensure spacing at the bottom
        )
    }
}


@Composable
fun QuadrantSection(
    selectedABQuadrant: String?,
    selectedCGQuadrant: String?,
    onABClick: (String) -> Unit,
    onCGClick: (String) -> Unit,
    fontSize: Float
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // First Row: Quadrants A and B
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuadrantButton(
                label = "A",
                isSelected = selectedABQuadrant == "A",
                fontSize = fontSize,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(fontSize.dp * 8)
            ) {
                onABClick("A")
            }
            Spacer(modifier = Modifier.width(16.dp))
            QuadrantButton(
                label = "B",
                isSelected = selectedABQuadrant == "B",
                fontSize = fontSize,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(fontSize.dp * 8)
            ) {
                onABClick("B")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second Column: Quadrants C-G
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            listOf("C", "D", "E", "F", "G").forEach { label ->
                QuadrantButton(
                    label = label,
                    isSelected = selectedCGQuadrant == label,
                    fontSize = fontSize,
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .height(50.dp)
                ) {
                    onCGClick(label)
                }
            }
        }
    }
}
