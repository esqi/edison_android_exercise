package jp.speakbuddy.edisonandroidexercise.fact.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.fact.data.proto.copy
import jp.speakbuddy.edisonandroidexercise.fact.data.repository.FactLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class FactLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : FactLocalDataSource {

    private val dataStore = context.factLocalDataDataStore

    override suspend fun saveFact(fact: Fact) {
        runCatching {
            dataStore.updateData {
                it.copy {
                    this.fact = fact.fact
                }
            }
        }
    }

    override suspend fun getCachedFact(): Fact? = runCatching {
        dataStore.data
            .firstOrNull()
            ?.takeUnless { factLocalData -> factLocalData.fact.isNullOrBlank() }
            ?.let { factLocalData -> FactLocal(factLocalData) }
    }.getOrDefault(null)
}