package jp.speakbuddy.edisonandroidexercise.fact.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.fact.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.fact.ui.FactUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO) {
            factRepository.getFact().fold(
                onSuccess = { fact -> FactUiState.Success(fact) }
            ) { e -> FactUiState.Error(e) }
                .also { factUiState -> _factStateFlow.update { factUiState } }
        }
    }
}
