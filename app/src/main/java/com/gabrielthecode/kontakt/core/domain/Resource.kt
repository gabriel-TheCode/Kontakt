package com.gabrielthecode.kontakt.core.domain

sealed class Resource<T> {
	data class Progress<T>(val data: T?) : Resource<T>()
	data class Success<T>(val data: T?) : Resource<T>()
	data class Failure<T>(val throwable: Throwable, val data: T? = null) : Resource<T>()
	data class Idle<T>(val data: T? = null) : Resource<T>()

	fun toData(): T? {
		return when (this) {
			is Success -> this.data
			is Progress -> this.data
			is Failure -> this.data
			is Idle -> this.data
		}
	}

	fun hasData(): Boolean {
		return this.toData() != null
	}

	fun <R> map(transform: (T?) -> R?): Resource<R> {
		return when (this) {
			is Success -> success(transform(this.data))
			is Progress -> loading(transform(this.data))
			is Failure -> failure(this.throwable, transform(this.data))
			is Idle -> idle(transform(this.data))
		}
	}

	companion object {
		fun <T> loading(data: T? = null): Resource<T> =
			Progress(data)

		fun <T> success(data: T? = null): Resource<T> = Success(data)
		fun <T> failure(throwable: Throwable, data: T? = null): Resource<T> =
			Failure(throwable, data)

		fun <T> idle(data: T? = null): Resource<T> = Idle(data)
	}
}