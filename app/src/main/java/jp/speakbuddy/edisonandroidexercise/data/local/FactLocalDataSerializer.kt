package jp.speakbuddy.edisonandroidexercise.data.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import jp.speakbuddy.edisonandroidexercise.data.proto.FactLocalData
import java.io.InputStream
import java.io.OutputStream

object FactLocalDataSerializer : Serializer<FactLocalData> {
    override val defaultValue: FactLocalData = FactLocalData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FactLocalData = try {
        FactLocalData.parseFrom(input)
    } catch (exception: InvalidProtocolBufferException) {
        throw CorruptionException("Cannot read proto.", exception)
    }

    override suspend fun writeTo(t: FactLocalData, output: OutputStream) = t.writeTo(output)
}