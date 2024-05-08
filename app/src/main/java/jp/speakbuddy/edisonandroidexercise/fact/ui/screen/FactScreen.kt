package jp.speakbuddy.edisonandroidexercise.fact.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun FactScreen(
    factUiState: FactUiState,
    onClick : () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
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
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }

        Button(onClick = onClick) {
            Text(text = "Update fact")
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
                override val fact: String = "Success Fact about cats"
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
