package jp.speakbuddy.edisonandroidexercise.fact.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
    modifier: Modifier = Modifier,
    factViewModel: FactViewModel = viewModel()
) {
    val state by factViewModel.factStateFlow.collectAsState(FactUiState.Initial)
    FactContent(factUiState = state, onClick = factViewModel::updateFact, modifier = modifier)
}

@Composable
fun ErrorBody(
    throwable: Throwable
) {
    Text(
        text = "something went wrong. error = ${throwable.message}",
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Red,
    )
}

@Composable
fun SuccessBody(
    fact: Fact
) {
    if (fact.containCats) {
        Text(
            text = "Multiple cats!!",
            style = MaterialTheme.typography.titleMedium,
        )
    }
    Text(
        text = fact.fact,
        style = MaterialTheme.typography.bodyLarge,
    )
    if (fact.isShowLength) {
        Text(
            text = "Length: ${fact.fact.length}",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LoadingBody() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun FactContent(
    factUiState: FactUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
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
            is FactUiState.Error -> ErrorBody(throwable = factUiState.throwable)
            FactUiState.Initial -> {}
            FactUiState.Loading -> LoadingBody()
            is FactUiState.Success -> SuccessBody(fact = factUiState.fact)
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
private fun FactContentPreview() {
    EdisonAndroidExerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FactContent(
                factUiState = FactUiState.Success(object : Fact {
                    override val fact: String =
                        "Success Fact about cats it is very long that the length will be shown under this fact. Many cat is normal, "
                }),
                onClick = { },
            )
        }
    }
}

@Preview(showSystemUi = true)
@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun FactLoadingPreview() {
    EdisonAndroidExerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FactContent(
                factUiState = FactUiState.Loading,
                onClick = { },
            )
        }
    }
}
