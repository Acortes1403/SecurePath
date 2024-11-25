package com.example.avance.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.avance.R
import com.example.avance.ui.theme.AvanceTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import com.example.avance.ui.theme.PrimaryColor
import com.example.avance.ui.theme.SecondaryColor

@Composable
fun ReportStatusButton(
    label: String,
    count: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = count.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

@Composable
fun ReportStatusButtons(
    totalCount: Int,
    uploadedCount: Int,
    savedCount: Int,
    markedCount: Int,
    onTotalClick: () -> Unit,
    onUploadedClick: () -> Unit,
    onSavedClick: () -> Unit,
    onMarkedClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ReportStatusButton(label = stringResource(id = R.string.total), count = totalCount, onClick = onTotalClick)
        ReportStatusButton(label = stringResource(id = R.string.uploaded), count = uploadedCount, onClick = onUploadedClick)
        ReportStatusButton(label = stringResource(id = R.string.saved), count = savedCount, onClick = onSavedClick)
        ReportStatusButton(label = stringResource(id = R.string.marked), count = markedCount, onClick = onMarkedClick)
    }
}

@Composable
fun GreetingSection(userName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.greeting),
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = userName,
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EmergencyAlert(unsubmittedFormsCount: Int) {
    if (unsubmittedFormsCount > 0) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(horizontal = 16.dp)
                .background(
                    Color(0xFFFDE7E7),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_warning),
                        contentDescription = stringResource(id = R.string.warning_icon),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.emergency),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFB71C1C),
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.emergency_subtext, unsubmittedFormsCount),
                    color = Color(0xFFB71C1C),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ProgressIndicator(progress: Float) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(248.dp)
            .shadow(elevation = 2.dp, shape = CircleShape)
    ) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(248.dp),
            color = PrimaryColor,
            strokeWidth = 22.dp,
            trackColor = SecondaryColor,
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            color = SecondaryColor,
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 8.dp), // Adjust padding as needed
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Home Button
        BottomNavItem(
            label = stringResource(id = R.string.home),
            iconResId = R.drawable.ic_home,
            onClick = { navController.navigate("hola_samantha") }
        )

        // Search Button
        BottomNavItem(
            label = stringResource(id = R.string.search),
            iconResId = R.drawable.ic_search,
            onClick = { navController.navigate("search_todos") }
        )

        // Settings Button
        BottomNavItem(
            label = stringResource(id = R.string.settings),
            iconResId = R.drawable.ic_settings,
            onClick = {
                if (navController.currentDestination?.route != "settings") {
                    navController.navigate("settings")
                }
            }
        )
    }
}


@Composable
fun BottomNavItem(label: String, iconResId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}


@Composable
fun HolaSamantha(navController: NavController) {
    var progress by remember { mutableFloatStateOf(0.36f) } // Placeholder progress
    val totalCount = 7
    val uploadedCount = 3
    val savedCount = 2
    val markedCount = 2
    val unsubmittedFormsCount = 2

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.vista_perfil_awaq),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Left Icon Button with Logo
        IconButton(
            onClick = { /* Action for left button */ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp)
                .size(60.dp)
                .background(Color(0x504E7029), shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.awaq_logo),
                contentDescription = stringResource(id = R.string.logo),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Right Button (Profile)
        IconButton(
            onClick = { navController.navigate("perfil") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
                .size(52.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.test_profile_picture),
                contentDescription = stringResource(id = R.string.profile_picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Main Content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 42.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Greeting Section
            GreetingSection(userName = stringResource(id = R.string.user_name))

            Spacer(modifier = Modifier.height(24.dp))

            // Floating Action Button
            LargeFloatingActionButton(
                onClick = { navController.navigate("formulario_activity") },
                shape = CircleShape,
                containerColor = SecondaryColor,
                contentColor = PrimaryColor,
                elevation = FloatingActionButtonDefaults.elevation(12.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.new_register),
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = stringResource(id = R.string.new_register),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Emergency Alert
            EmergencyAlert(unsubmittedFormsCount = unsubmittedFormsCount)

            Spacer(modifier = Modifier.height(16.dp))

            // Report Status Buttons
            ReportStatusButtons(
                totalCount = totalCount,
                uploadedCount = uploadedCount,
                savedCount = savedCount,
                markedCount = markedCount,
                onTotalClick = { navController.navigate("allReports") },
                onUploadedClick = { navController.navigate("uploadedReports") },
                onSavedClick = { navController.navigate("savedReports") },
                onMarkedClick = { navController.navigate("markedReports") }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Progress Indicator
            ProgressIndicator(progress = progress)
        }


        // Bottom Navigation Bar with semi-transparent background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SecondaryColor.copy(alpha = 0.2f)) // Semi-transparent background
                .align(Alignment.BottomEnd)
        ) {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier.padding(vertical = 8.dp) // Add padding if needed
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun PreviewHolaSamantha() {
    AvanceTheme {
        HolaSamantha(navController = rememberNavController())
    }
}