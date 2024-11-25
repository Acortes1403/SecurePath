// Paquete de la aplicación
package com.example.avance.view

// Importación de componentes y funciones necesarios para la interfaz de usuario
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.avance.R
import com.example.avance.model.FormularioBase
import com.example.avance.ui.theme.AvanceTheme
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.ui.theme.SecondaryColor
import com.example.avance.viewmodel.FormularioViewModel
import com.example.avance.viewmodel.FontSizeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTodos(
    navController: NavController,
    fontSizeViewModel: FontSizeViewModel,
    id: Int,
    viewModel: FormularioViewModel
) {
    val fontSize by fontSizeViewModel.fontSize.collectAsState()
    val formularios by viewModel.formularios.collectAsState(initial = emptyList())
    var selectedFilter by remember { mutableStateOf("Todos") }
    val filteredFormularios = if (selectedFilter == "Todos") formularios else formularios.filter {
        it.tipoDeRegistro == selectedFilter
    }

    LaunchedEffect(Unit) {
        viewModel.fetchFormularios()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.vista_perfil_awaq),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize()) {
            // Top AppBar
            TopAppBar(
                title = {
                    Text(
                        text = "Búsqueda",
                        fontSize = fontSize.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryColor
                )
            )

            // Horizontal LazyRow for Filters
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val filters = listOf(
                    "Todos",
                    "Fauna en Transectos",
                    "Fauna en Punto de Conteo",
                    "Fauna Búsqueda Libre",
                    "Validación de Cobertura",
                    "Parcela de Vegetación",
                    "Cámaras Trampa",
                    "Variables Climáticas"
                )

                items(filters) { filter ->
                    TextFilterButton(
                        label = filter,
                        isSelected = selectedFilter == filter,
                        onClick = { selectedFilter = filter }
                    )
                }
            }

            // Display filtered list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .weight(1f), // Use weight to ensure it occupies available space
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredFormularios) { formulario ->
                    FormularioItem(
                        formulario = formulario,
                        onEdit = { selectedFormulario ->
                            navController.navigate("formSelect4?isEditing=true&formId=${selectedFormulario.id}")
                        },
                        onDelete = { selectedFormulario ->
                            viewModel.deleteFormulario(selectedFormulario)
                        }
                    )
                }
            }

            // Bottom Navigation Bar with semi-transparent background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SecondaryColor.copy(alpha = 0.2f)) // Semi-transparent background
            ) {
                BottomNavigationBar(
                    navController = navController,
                    modifier = Modifier.padding(vertical = 8.dp) // Add padding if needed
                )
            }
        }
    }
}


@Composable
fun TextFilterButton(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = label,
        fontSize = 14.sp,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        color = if (isSelected) PrimaryColor else Color.White   ,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .background(
                color = if (isSelected) SecondaryColor else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun FormularioItem(
    formulario: FormularioBase,
    onEdit: (FormularioBase) -> Unit,
    onDelete: (FormularioBase) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .shadow(6.dp, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = SecondaryColor)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Form Icon
            Image(
                painter = painterResource(id = R.drawable.ic_formicon),
                contentDescription = "Form Icon",
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Form Information
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "#FM${formulario.id}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                formulario.tipoDeRegistro?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
                Text(
                    text = "Date: ${formulario.date} @ ${formulario.hora}",
                    fontSize = 12.sp,
                    color = Color.White
                )
            }

            // More Options Menu
            MoreOptionsMenu(
                formulario = formulario,
                onEditClick = { onEdit(formulario) },
                onDeleteClick = { onDelete(formulario) }
            )
        }
    }
}


@Composable
fun MoreOptionsMenu(
    formulario: FormularioBase,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Default.MoreVert, contentDescription = "More Options")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = {
                expanded = false
                onEditClick()
            }
        )
        DropdownMenuItem(
            text = { Text("Delete") },
            onClick = {
                expanded = false
                onDeleteClick()
            }
        )
    }
}