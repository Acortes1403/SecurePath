package com.example.avance.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.avance.R
import com.example.avance.ui.theme.AvanceTheme
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.ui.theme.SecondaryColor
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person

@Composable
fun EditarPerfil(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.vista_perfil_awaq),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Profile Picture
            Image(
                painter = painterResource(id = R.drawable.test_profile_picture),
                contentDescription = stringResource(id = R.string.profile_picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Name TextField
            var name by remember { mutableStateOf("Elit Acosta") }
            ProfileTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nombre",
                icon = Icons.Filled.Person,
            )

            // Email TextField
            var email by remember { mutableStateOf("elitacosta@gmail.com") }
            ProfileTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )

            // Password TextField
            var rol by remember { mutableStateOf("Biomonitor") }
            ProfileTextField(
                value = rol,
                onValueChange = { rol = it },
                label = "Rol",
                icon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            )

            // Phone TextField
            var grupo by remember { mutableStateOf("Grupo 1") }
            ProfileTextField(
                value = grupo,
                onValueChange = { grupo = it },
                label = "Grupo",
                icon = Icons.Default.Groups,
                keyboardType = KeyboardType.Phone
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Save Button
            Button(
                onClick = { navController.navigate("perfil") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryColor,
                    contentColor = PrimaryColor
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Guardar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    containerColor: Color = Color.Transparent,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    labelColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = labelColor
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = containerColor,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
            cursorColor = SecondaryColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun PreviewEditarPerfil() {
    AvanceTheme {
        EditarPerfil(navController = rememberNavController())
    }
}
