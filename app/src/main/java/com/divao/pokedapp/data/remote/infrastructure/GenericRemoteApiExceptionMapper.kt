package com.divao.pokedapp.data.remote.infrastructure

import com.divao.domain.exception.NoInternetException
import com.divao.domain.exception.UnexpectedException
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketException
import java.net.UnknownHostException

fun Throwable.toGenericRemoteApiException(): Throwable {
    if ((this is HttpException && code() == HttpURLConnection.HTTP_INTERNAL_ERROR) || this is SocketException) {
        return UnexpectedException()
    } else if (this is UnknownHostException) {
        return NoInternetException()
    }

    return this
}