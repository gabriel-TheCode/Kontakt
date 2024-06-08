package com.gabrielthecode.kontakt.datasource.network.mapper.error

import com.gabrielthecode.kontakt.datasource.network.model.DomainException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class DomainExceptionMapper {
	fun map(throwable: Throwable): DomainException {
		return when (throwable) {
			is UnknownHostException -> DomainException.NetworkError
			is SocketTimeoutException -> DomainException.TimeoutError
			else -> DomainException.UnknowError(throwable.message ?: "An error occurred")
		}
	}
}
