package jp.speakbuddy.edisonandroidexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import dagger.hilt.android.AndroidEntryPoint
import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.fact.ui.FactUiState
import jp.speakbuddy.edisonandroidexercise.fact.ui.screen.FactContent
import jp.speakbuddy.edisonandroidexercise.fact.ui.screen.FactScreen
import jp.speakbuddy.edisonandroidexercise.theme.EdisonAndroidExerciseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdisonAndroidExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App {
                        FactScreen(modifier = Modifier.padding(it))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Fact")
                }
            )
        },
        content = content
    )
}

@Preview(showSystemUi = true)
@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App {
                FactContent(
                    factUiState = FactUiState.Success(object : Fact {
                        override val fact: String =
                            "Success Fact about cats it is very long that the length will be shown under this fact. Many cat is normal, "
                    }),
                    onClick = { },
                    modifier = Modifier.padding(it),
                )
            }
        }
    }
}