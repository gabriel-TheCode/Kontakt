package com.gabrielthecode.kontakt.presentation.contactdetails

import androidx.lifecycle.ViewModel
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserContactDetailsViewModel @Inject constructor() : ViewModel() {

	private var defaultState = UserContactDetailsState.InitialState

	private val _state = MutableStateFlow<UserContactDetailsState>(defaultState)
	val state: StateFlow<UserContactDetailsState> get() = _state

	private val _event = MutableStateFlow<UserContactDetailsEvent>(UserContactDetailsEvent.None)
	val event: StateFlow<UserContactDetailsEvent> = _event

	fun loadContactDetails(uiModel: UserContactUIModel?) {
		uiModel?.let {
			_state.value = UserContactDetailsState.LoadedState(it)
		}
	}

	fun onCallActionClick(phone: String) {
		_event.value = UserContactDetailsEvent.OnCallActionClickEvent(phone)
	}

	fun onSmsActionClick(phone: String) {
		_event.value = UserContactDetailsEvent.OnSmsActionClickEvent(phone)
	}

	fun clearEvent() {
		_event.value = UserContactDetailsEvent.None
	}
}

sealed class UserContactDetailsState {
	data object InitialState : UserContactDetailsState()
	data class LoadedState(val uiModel: UserContactUIModel) : UserContactDetailsState()
}

sealed class UserContactDetailsEvent {
	data object None : UserContactDetailsEvent()
	class OnCallActionClickEvent(val phone: String) : UserContactDetailsEvent()
	class OnSmsActionClickEvent(val phone: String) : UserContactDetailsEvent()
}
