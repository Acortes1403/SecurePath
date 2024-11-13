package com.example.avance.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.avance.R
import com.example.avance.ui.theme.AvanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(navController: NavController, mainActivity: MainActivity) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var authError by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background // Use theme background color to adapt to dark mode
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.vista_1_light),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize() // Make it cover the entire screen
            )

            // "Back" Button at the Top
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.offset(x = (-10).dp)
                ) {
                    Text("<", fontSize = 40.sp)
                }
            }

            // Main Column for Text and Inputs
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                // Logo with a white circle background
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(200.dp) // Adjust the size of the circle as needed
                ) {
                    Box(
                        modifier = Modifier
                            .size(160.dp) // Size of the white circle
                            .background(Color(0x504E7029), shape = CircleShape)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.awaq_logo_letras),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(160.dp) // Adjust size of the logo
                            .padding(top = 130.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.awaq_logo),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(240.dp) // Adjust size of the logo
                    )
                }

                // "Crea una cuenta" Title
                Text(
                    text = "Crea tu cuenta",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(top = 20.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Name Label and TextField
                Text(
                    text = "Name",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Ensures it's aligned as you desire in the center
                )
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("") },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    textStyle = TextStyle(color = Color.Black)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Email Label and TextField
                Text(
                    text = "Email",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Ensures it's aligned as you desire in the center
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("") },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    textStyle = TextStyle(color = Color.Black)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Password Label and TextField
                Text(
                    text = "Contraseña",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Ensures it's aligned as you desire in the center
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("") },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    textStyle = TextStyle(color = Color.Black)
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Create Account Button
                Button(
                    onClick = { navController.navigate("hola_samantha") }, // Add logic for creating the account
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3A5F0B) // Green color
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(60.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "CREAR CUENTA",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Black
                    )
                }

                authError?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón de Autenticar
                Button(
                    onClick = {
                        mainActivity.loginWithAuth0(
                            username = email,
                            password = password,
                            onSuccess = { navController.navigate("hola_samantha") },
                            onError = { error -> authError = "Error de autenticación: $error" }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 88, 156),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(60.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "AUTENTICAR",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


                // Forgot Password Button
                TextButton(
                    onClick = { navController.navigate("second_screen") },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 4.dp)
                ) {
                    Text("¿Ya estas registrado?",
                        fontSize = 18.sp,
                        color = Color(0xFFFFFFFF),
                        fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewThirdScreenLight() {
    AvanceTheme(darkTheme = false) {
        ThirdScreen(navController = NavController(LocalContext.current), mainActivity =  MainActivity())
    }
}
