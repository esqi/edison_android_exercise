package jp.speakbuddy.edisonandroidexercise.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import jp.speakbuddy.edisonandroidexercise.data.proto.FactLocalData

val Context.factLocalDataDataStore: DataStore<FactLocalData> by dataStore(
    fileName = "FactLocalData.pb",
    serializer = FactLocalDataSerializer
)