package jp.speakbuddy.edisonandroidexercise.ui

import io.mockk.every
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.fact.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.fact.ui.FactUiState
import jp.speakbuddy.edisonandroidexercise.fact.ui.viewmodel.FactViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FactViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test update fact success`() {
        // Given
        val expected =
            "Some Fact about cats that it is inhabiting large island in Japan where you can visit and sees them. They also have more population than human inhabitant"
        val fact = mockk<Fact> {
            every { fact } returns expected
        }
        val factRepository = mockk<FactRepository> {
            every { getFact() } returns flowOf(fact)
        }
        val viewModel = FactViewModel(factRepository)

        // When
        viewModel.updateFact()

        // Then
        val factUiState = viewModel.factStateFlow.value
        assertTrue(factUiState is FactUiState.Success && factUiState.fact.fact == expected)
    }

    @Test
    fun `test update fact error`() {
        // Given
        val error = RuntimeException()
        val factRepository = mockk<FactRepository> {
            every { getFact() } returns flow { throw error }
        }
        val viewModel = FactViewModel(factRepository)

        // When
        viewModel.updateFact()

        // Then
        val factUiState = viewModel.factStateFlow.value
        assertTrue(factUiState is FactUiState.Error && factUiState.throwable == error)
    }

    @Test
    fun `test update fact loading`() {
        // Given
        val factRepository = mockk<FactRepository> {
            every { getFact() } returns flow {  }
        }
        val viewModel = FactViewModel(factRepository)

        // When
        viewModel.updateFact()

        // Then
        val factUiState = viewModel.factStateFlow.value
        assertTrue(factUiState is FactUiState.Loading)
    }

    @Test
    fun `test update fact initial`() {
        // Given
        val factRepository = mockk<FactRepository>()
        val viewModel = FactViewModel(factRepository)

        // When

        // Then
        val factUiState = viewModel.factStateFlow.value
        assertTrue(factUiState is FactUiState.Initial)
    }
}
