package jp.speakbuddy.edisonandroidexercise.ui

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.fact.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.fact.ui.FactUiState
import jp.speakbuddy.edisonandroidexercise.fact.ui.viewmodel.FactViewModel
import org.junit.Before
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FactViewModelTest {

    @MockK
    private lateinit var factRepository: FactRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun updateFact() = runTest {
        val viewModel = FactViewModel(factRepository)
        // Given
        val fact : Fact = mockk()
        val expected = "Some Fact about cats that it is inhabiting large island in Japan where you can visit and sees them. They also have more population than human inhabitant"
        every { fact.fact } returns expected
        coEvery { factRepository.getFact() } returns Result.success(fact)

        // When
        viewModel.updateFact().join()

        // Then
        val factUiState = viewModel.factStateFlow.value
        assert(factUiState is FactUiState.Success && factUiState.fact.fact == expected)
    }
}
