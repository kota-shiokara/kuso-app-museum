package com.ikanoshiokara.emergency_stop.view.pages

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.ikanoshiokara.emergency_stop.LocalNavController
import com.ikanoshiokara.emergency_stop.NavItem
import com.ikanoshiokara.emergency_stop.R

@Composable
fun MainPage() {
    Scaffold(
        topBar = {
            MainAppBar()
        }
    ) {
        MainContent()
    }
}

@Composable
fun MainAppBar() {
    val navController = LocalNavController.current
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(NavItem.SettingsPage.name)
                }
            ) {
                Icon(Icons.Filled.Edit, contentDescription = "settings_page")
            }
        }
    )
}

@Composable
fun MainContent() {
    val context = LocalContext.current
    val packageName = context.packageName
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(350.dp)
                .clip(CircleShape)
                .background(Color.Yellow)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmergencyText(text = "EMERGENCY")
                Spacer(modifier = Modifier.size(12.dp))
                Box(
                    modifier = Modifier
                        .size(175.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .clickable {
                            // val packageInstaller = context.packageManager.packageInstaller
                            // packageInstaller.uninstall(packageName, intentSender)

                            val packageUri = Uri.parse("package:$packageName")
                            val uninstallIntent = Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri)
                            startActivity(context, uninstallIntent, null)
                        }
                )
                Spacer(modifier = Modifier.size(12.dp))
                EmergencyText(text = "STOP")
            }
        }
    }
}

@Composable
fun EmergencyText(text: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}