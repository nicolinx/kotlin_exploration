package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFDFBFD)
    ) { innerPadding ->
        ArtSpaceView(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

data class ArtworkResource(
    @DrawableRes val imageId: Int,
    @StringRes val descriptionId: Int,
    @StringRes val authorId: Int,
    @StringRes val yearId: Int,
)

@Composable
fun ArtSpaceView(modifier: Modifier = Modifier) {
    var currentArt by remember { mutableIntStateOf(0) }

    val artwork = when (currentArt) {
        0 -> ArtworkResource(
            R.drawable.art_1,
            R.string.art_1_description,
            R.string.art_1_author,
            R.string.art_1_year
        )

        1 -> ArtworkResource(
            R.drawable.art_2,
            R.string.art_2_description,
            R.string.art_2_author,
            R.string.art_2_year
        )

        2 -> ArtworkResource(
            R.drawable.art_3,
            R.string.art_3_description,
            R.string.art_3_author,
            R.string.art_3_year
        )

        else -> ArtworkResource(
            R.drawable.art_1,
            R.string.art_1_description,
            R.string.art_1_author,
            R.string.art_1_year
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ArtDisplay(
                image = artwork.imageId,
            )
        }

        Box(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ArtInfo(
                description = artwork.descriptionId,
                author = artwork.authorId,
                year = artwork.yearId,
            )
        }

        Box(
            Modifier
                .weight(0.1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ActionButtons(
                onPrev = {
                    if (currentArt > 0) currentArt--
                },
                onNext = {
                    if (currentArt < 2) currentArt++
                },
            )
        }
    }
}

@Composable
fun ArtDisplay(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = modifier
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Composable
fun ArtInfo(
    @StringRes description: Int,
    @StringRes author: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .wrapContentSize(),
        color = Color(0xFFECEBF0)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(stringResource(description))
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(author))
                }
                append(" (${stringResource(year)})")
            })
        }
    }
}

@Composable
fun ActionButtons(
    onPrev: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Button(onClick = onPrev, modifier = Modifier.weight(1f)) {
            Text("Previous")
        }
        Spacer(Modifier.width(32.dp))
        Button(onClick = onNext, modifier = Modifier.weight(1f)) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}