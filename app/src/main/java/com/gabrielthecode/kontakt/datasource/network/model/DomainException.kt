package com.gabrielthecode.kontakt.datasource.network.model

sealed class DomainException : Exception() {
	data object NetworkError : DomainException()
	data object TimeoutError : DomainException()
	data class UnknowError(override val message: String) : DomainException()
}