package com.gabrielthecode.kontakt.presentation.contactdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielthecode.kontakt.presentation.contact.UserContactEvent
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserContactDetailsViewModel @Inject constructor() : ViewModel() {

	private var defaultState = UserContactDetailsState.InitialState

	private val _state = mutableStateOf<UserContactDetailsState>(defaultState)
	val state: State<UserContactDetailsState> get() = _state

	private val _event = MutableLiveData<UserContactDetailsEvent>()
	val event: LiveData<UserContactDetailsEvent> = _event

	fun loadContactDetails(uiModel: UserContactUIModel?){
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

}

sealed class UserContactDetailsState {
	data object InitialState : UserContactDetailsState()
	data class LoadedState(val uiModel: UserContactUIModel) : UserContactDetailsState()
}

sealed class UserContactDetailsEvent {
	class OnCallActionClickEvent(val phone: String) : UserContactDetailsEvent()
	class OnSmsActionClickEvent(val phone: String) : UserContactDetailsEvent()
}
