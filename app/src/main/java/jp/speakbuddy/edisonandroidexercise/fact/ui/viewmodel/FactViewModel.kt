package jp.speakbuddy.edisonandroidexercise.fact.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.fact.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.fact.ui.FactUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val factRepository: FactRepository
) : ViewModel() {

    private val _factStateFlow: MutableStateFlow<FactUiState> =
        MutableStateFlow(FactUiState.Initial)
    val factStateFlow: StateFlow<FactUiState> = _factStateFlow.asStateFlow()

    fun updateFact() {
        _factStateFlow.update { FactUiState.Loading }
        factRepository.getFact().onEach { fact ->
            _factStateFlow.update { FactUiState.Success(fact) }
        }.catch { throwable ->
            _factStateFlow.update { FactUiState.Error(throwable) }
        }.launchIn(viewModelScope)
    }
}
