package com.gabrielthecode.kontakt.presentation.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gabrielthecode.kontakt.domain.usecases.GetUserContacts
import com.gabrielthecode.kontakt.presentation.contact.mapper.UserContactEntityToUIModelMapper
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserContactsViewModel @Inject constructor(
	getUserContactsUseCase: GetUserContacts,
	private val userContactEntityToUIModelMapper: UserContactEntityToUIModelMapper,
) : ViewModel() {

	private val _event = MutableStateFlow<UserContactEvent>(UserContactEvent.None)
	val event: StateFlow<UserContactEvent?> = _event

	val userContactStateFlow: StateFlow<PagingData<UserContactUIModel>> =
		getUserContactsUseCase().map { pagingData ->
			pagingData.map { userEntity ->
				userContactEntityToUIModelMapper.map(userEntity)
			}
		}
			.cachedIn(viewModelScope) // Caches paging data in the ViewModel scope to handle configuation changes
			.stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty())

	fun onUserContactClick(uiModel: UserContactUIModel) {
		_event.value = UserContactEvent.OnContactClickEvent(uiModel)
	}

	fun clearEvent() {
		_event.value = UserContactEvent.None
	}
}

sealed class UserContactEvent {
	class OnContactClickEvent(val uiModel: UserContactUIModel) : UserContactEvent()
	data object None : UserContactEvent()
}
