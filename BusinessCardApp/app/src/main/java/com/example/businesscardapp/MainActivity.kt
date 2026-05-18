package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BrandGreen
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCardScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCardScreen(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFDAE4DB))
    ) {
        MainWidget(Modifier.weight(0.8f))
        ContactInformation(Modifier.weight(0.2f))
    }
}

@Composable
fun MainWidget(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val image = painterResource(R.drawable.android_logo)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .background(color = Color(0xFF172C35))
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Jennifer Doe",
            fontSize = 36.sp,
            fontWeight = FontWeight.W300
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Android Developer Extraordinaire",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = BrandGreen,
        )
    }
}

@Composable
fun ContactInformation(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        ContactListTile(text = "+611 (123) 444 555 666", icon = Icons.Default.Phone)
        ContactListTile(text = "@AndroidDev", icon = Icons.Default.Share)
        ContactListTile(text = "jen.doe@android.com", icon = Icons.Default.Email)
    }
}

@Composable
fun ContactListTile(text: String, icon: ImageVector) {
    Row() {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BrandGreen,
        )
        Spacer(Modifier.width(12.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardScreenPreview() {
    BusinessCardAppTheme {
        BusinessCardScreen()
    }
}