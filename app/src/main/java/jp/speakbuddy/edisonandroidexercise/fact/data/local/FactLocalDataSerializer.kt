package jp.speakbuddy.edisonandroidexercise.fact.data.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import jp.speakbuddy.edisonandroidexercise.fact.data.proto.FactLocalData
import java.io.InputStream
import java.io.OutputStream

object FactLocalDataSerializer : Serializer<FactLocalData> {
    override val defaultValue: FactLocalData = FactLocalData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FactLocalData = try {
        FactLocalData.parseFrom(input)
    } catch (exception: Throwable) {
        throw CorruptionException("Cannot read proto.", exception)
    }

    override suspend fun writeTo(t: FactLocalData, output: OutputStream) = t.writeTo(output)
}