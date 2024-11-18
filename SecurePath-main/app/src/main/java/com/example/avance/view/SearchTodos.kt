// Paquete de la aplicación
package com.example.avance.view

// Importación de componentes y funciones necesarios para la interfaz de usuario
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.model.FormularioBase
import com.example.avance.viewmodel.FormularioViewModel
import com.example.avance.viewmodel.FontSizeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTodos(
    navController: NavController,
    fontSizeViewModel: FontSizeViewModel,
    viewModel: FormularioViewModel
) {
    val fontSize by fontSizeViewModel.fontSize.collectAsState()
    val formularios by viewModel.formularios.collectAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewModel.fetchFormularios()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "Búsqueda", fontSize = fontSize.sp, color = Color.Black) },
            navigationIcon = {
                Text(
                    text = "<--",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { navController.popBackStack() }
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFA8D8A2))
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(formularios) { formulario ->
                FormularioItem(
                    formulario = formulario,
                    onView = { selectedFormulario ->
                        navController.navigate("form_activity/${selectedFormulario.id}/view")
                    },
                    onDelete = { selectedFormulario ->
                        viewModel.deleteFormulario(selectedFormulario)
                    }
                )
            }
        }
    }
}

@Composable
fun FormularioItem(
    formulario: FormularioBase,
    onView: (FormularioBase) -> Unit, // Callback para la acción "View"
    onDelete: (FormularioBase) -> Unit // Callback para la acción "Edit"
) {
    var expanded by remember { mutableStateOf(false) } // Control del estado del menú desplegable

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp) // Actualizado por la advertencia
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono a la izquierda
            Image(
                painter = painterResource(id = R.drawable.ic_formicon),
                contentDescription = "Icono",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Contenido del formulario
            Column(
                modifier = Modifier.weight(1f) // Esto hace que ocupe el espacio restante
            ) {
                Text(
                    text = "#FM${formulario.id}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                formulario.tipoDeRegistro?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = "Date: ${formulario.date} @ ${formulario.hora}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // Botón de opciones (tres puntos)
            Box {
                Text(
                    text = "⋮", // Tres puntos verticales
                    fontSize = 24.sp,
                    modifier = Modifier
                        .clickable { expanded = true } // Abre el menú al hacer clic
                        .padding(8.dp)
                )

                // Menú desplegable
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false } // Cierra el menú
                ) {
                    DropdownMenuItem(
                        text = { Text("View") },
                        onClick = {
                            expanded = false
                            onView(formulario) // Llama al callback de "View"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete") },
                        onClick = {
                            expanded = false
                            onDelete(formulario) // Llama al callback de "Edit"
                        }
                    )
                }
            }
        }
    }
}



