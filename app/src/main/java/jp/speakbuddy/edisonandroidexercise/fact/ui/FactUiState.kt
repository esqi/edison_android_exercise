package jp.speakbuddy.edisonandroidexercise.fact.ui

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact

sealed interface FactUiState {
    data class Success(val fact: Fact) : FactUiState
    data class Error(val throwable: Throwable) : FactUiState
    data object Loading : FactUiState
    data object Initial : FactUiState
}