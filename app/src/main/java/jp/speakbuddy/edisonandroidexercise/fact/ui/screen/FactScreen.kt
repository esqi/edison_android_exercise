package jp.speakbuddy.edisonandroidexercise.fact.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.fact.data.model.containCats
import jp.speakbuddy.edisonandroidexercise.fact.data.model.isShowLength
import jp.speakbuddy.edisonandroidexercise.fact.ui.FactUiState
import jp.speakbuddy.edisonandroidexercise.fact.ui.viewmodel.FactViewModel
import jp.speakbuddy.edisonandroidexercise.theme.EdisonAndroidExerciseTheme

@Composable
fun FactScreen(
    factViewModel: FactViewModel = viewModel()
) {
    val state by factViewModel.factStateFlow.collectAsState(FactUiState.Initial)
    FactScreen(factUiState = state, onClick = factViewModel::updateFact)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactScreen(
    factUiState: FactUiState,
    onClick : () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title =  {
                    Text("Fact")
                }
            )
        }
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding(),
                    bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = "Fact",
                style = MaterialTheme.typography.titleLarge,
            )

            when (factUiState) {
                is FactUiState.Error -> Text(
                    text = "something went wrong. error = ${factUiState.throwable.message}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red,
                )

                FactUiState.Initial -> {

                }
                FactUiState.Loading -> CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

                is FactUiState.Success -> {
                    if (factUiState.fact.containCats) {
                        Text(
                            text = "Multiple cats!!",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    Text(
                        text = factUiState.fact.fact,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    if (factUiState.fact.isShowLength) {
                        Text(
                            text = "Length: ${factUiState.fact.fact.length}",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Button(onClick = onClick) {
                Text(text = "Update fact")
            }
        }
    }
}

@Preview(showSystemUi = true)
@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
        FactScreen(
            factUiState = FactUiState.Success(object : Fact {
                override val fact: String = "Success Fact about cats it is very long that the length will be shown under this fact. Many cat is normal, "
            })
        ) {
            // do nothing
        }
    }
}

@Preview(showSystemUi = true)
@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun FactScreenLoadingPreview() {
    EdisonAndroidExerciseTheme {
        FactScreen(
            factUiState = FactUiState.Loading
        ) {
            // do nothing
        }
    }
}
