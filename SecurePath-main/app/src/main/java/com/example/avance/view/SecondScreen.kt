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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import com.example.avance.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.avance.ui.theme.AvanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top // Center the form vertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(200.dp) // Adjust the size of the circle as needed
                ) {
                    // White circle background
                    Box(
                        modifier = Modifier
                            .size(160.dp) // Adjust the size of the circle
                            .background(Color(0x504E7029), shape = CircleShape) // 50% transparent white
                    )

                    // Logo Image on top of the white circle
                    Image(
                        painter = painterResource(id = R.drawable.awaq_logo),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(240.dp) // Adjust the size of the logo if needed
                    )
                }

                // "Inicia Sesión" Text
                Text(
                    text = "Inicia Sesión",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(top = 60.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

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
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(vertical = 4.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFFFFFFF), // Light background color
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Label and TextField
                Text(
                    text = "Contraseña",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(vertical = 2.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFFFFFFF), // Light background color
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // Forgot Password Button
                TextButton(
                    onClick = { navController.navigate("olvidaste_cont_screen") },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 4.dp)
                ) {
                    Text("Olvidaste tu contraseña?",
                        fontSize = 18.sp,
                        color = Color(0xFFFFFFFF),
                        fontWeight = FontWeight.Black)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login Button
                Button(
                    onClick = { navController.navigate("hola_samantha") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3A5F0B) // Green color
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Adjust width to match the mockup
                        .height(60.dp)
                        .padding(horizontal = 16.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp) // Slightly more rounded corners
                ) {
                    Text(
                        text = "ENTRAR",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewSecondScreenLight() {
    AvanceTheme(darkTheme = false) {
        SecondScreen(navController = NavController(LocalContext.current))
    }
}
