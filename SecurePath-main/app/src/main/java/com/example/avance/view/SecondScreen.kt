package com.example.avance.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import com.example.avance.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.avance.ui.theme.AvanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, mainActivity: MainActivity) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var authError by remember { mutableStateOf<String?>(null) }

    /*
    @Composable
    fun CustomBackground() {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Top Circle Shape
            drawCircle(
                color = Color(0xFF6A994E), // Dark green color for the top circle
                center = Offset(size.width / 5, -size.height / 2), // Positioning slightly higher
                radius = size.width * 1.9f // Adjust radius for your design
            )

            // Middle Wave Shape (between top and bottom for smoother transition)
            drawPath(
                path = Path().apply {
                    moveTo(-size.width * 0.2f, size.height * 0.85f)
                    cubicTo(
                        size.width * 0.3f, size.height * 0.65f,    // Control point 1 (smooth rise)
                        size.width * 0.75f, size.height * 0.85f,   // Control point 2 (broader curve)
                        size.width * 2.2f, size.height * 0.7f      // End point, slightly off-screen to the right
                    )
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                },
                color = Color(0xFF4E7029) // Slightly lighter green
            )

            // Bottom Wave Shape
            drawPath(
                path = Path().apply {
                    moveTo(0f, size.height * 0.8f)
                    cubicTo(
                        size.width * 0.25f, size.height * 0.85f,
                        size.width * 0.75f, size.height * 1.0f,
                        size.width, size.height * 0.9f
                    )
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                },
                color = Color(0xFFA5BE00) // Light green for contrast
            )
        }
    }*/

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background // Use theme background color to adapt to dark mode
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            /* Image(
                painter = painterResource(id = R.drawable.vista_1_light),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize() // Make it cover the entire screen
            ) */
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
                    Image(
                        painter = painterResource(id = R.drawable.awaq_logo_letras),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(160.dp) // Adjust size of the logo
                            .padding(top = 130.dp)
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
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(6.dp))

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
                    shape = RoundedCornerShape(8.dp),
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
                    Text("¿Olvidaste tu contraseña?",
                        fontSize = 18.sp,
                        color = Color(0xFFFFFFFF),
                        fontWeight = FontWeight.Black)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Login Button
                Button(
                    onClick = { navController.navigate("hola_samantha") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3A5F0B), // Green color
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(8.dp), // Add elevation for a subtle shadow
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Adjust width to match the mockup
                        .height(60.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp) // Slightly more rounded corners
                ) {
                    Text(
                        text = "ENTRAR",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
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
                        containerColor = Color(0xFF00589C),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(8.dp), // Add elevation for a shadow effect
                    modifier = Modifier
                        .fillMaxWidth(0.56f)
                        .height(60.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp) // Rounded corners
                ) {
                    Text(
                        text = "AUTENTICAR",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewSecondScreenLight() {
    AvanceTheme(darkTheme = false) {
        SecondScreen(navController = NavController(LocalContext.current), mainActivity = MainActivity())
    }
}
