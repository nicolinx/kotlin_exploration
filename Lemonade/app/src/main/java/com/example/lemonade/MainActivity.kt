package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LemonadeView(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LemonadeView(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(0) }

    val image = when (currentStep) {
        0 -> painterResource(R.drawable.lemon_tree)
        1 -> painterResource(R.drawable.lemon_squeeze)
        2 -> painterResource(R.drawable.lemon_drink)
        3 -> painterResource(R.drawable.lemon_restart)
        else -> throw IllegalStateException("Unknown step: $currentStep")
    }

    val imageDescription = when (currentStep) {
        0 -> stringResource(R.string.lemon_tree_description)
        1 -> stringResource(R.string.lemon_description)
        2 -> stringResource(R.string.lemon_drink_description)
        3 -> stringResource(R.string.lemon_empty_description)
        else -> throw IllegalStateException("Unknown step: $currentStep")
    }

    val description = when (currentStep) {
        0 -> stringResource(R.string.lemon_tree)
        1 -> stringResource(R.string.lemon)
        2 -> stringResource(R.string.lemon_drink)
        3 -> stringResource(R.string.lemon_empty)
        else -> throw IllegalStateException("Unknown step: $currentStep")
    }

    var targetSqueeze = (2..4).random()
    var squeeze = 0;

    fun reset() {
        squeeze = 0;
        targetSqueeze = (2..4).random()
        currentStep = 0;
    }

    fun nextStep() {
        if (currentStep == 3) {
            reset();
        } else if (currentStep != 1) {
            currentStep++;
        } else {
            squeeze++;
            if (squeeze == targetSqueeze) {
                currentStep++;
            }
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { nextStep() },
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2E7DA))
        ) {
            Image(
                painter = image,
                contentDescription = imageDescription,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}