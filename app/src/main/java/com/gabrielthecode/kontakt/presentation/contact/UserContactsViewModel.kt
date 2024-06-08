package com.gabrielthecode.kontakt.presentation.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gabrielthecode.kontakt.core.usecases.GetUserContacts
import com.gabrielthecode.kontakt.presentation.contact.mapper.UserContactEntityToUIModelMapper
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserContactsViewModel @Inject constructor(
	private val getUserContactsUseCase: GetUserContacts,
	private val userContactEntityToUIModelMapper: UserContactEntityToUIModelMapper,
) : ViewModel() {

	private val _userContactStateFlow: MutableStateFlow<PagingData<UserContactUIModel>> =
		MutableStateFlow(PagingData.empty())
	var userContactStateFlow = _userContactStateFlow
		private set

	private val _event = MutableLiveData<UserContactEvent>()
	val event: LiveData<UserContactEvent> = _event

	init {
		viewModelScope.launch {
			getUserContactsUseCase.getUserContactsWithPaging().map { pagingData ->
				pagingData.map { userEntity ->
					userContactEntityToUIModelMapper.map(userEntity)
				}
			}
				.cachedIn(viewModelScope) // Caches the paging data in the ViewModel scope
				.collect {
					_userContactStateFlow.value = it
				}
		}
	}

	fun onUserContactClick(uiModel: UserContactUIModel) {
		_event.value = UserContactEvent.OnContactClickEvent(uiModel)
	}
}

sealed class UserContactEvent {
	class OnContactClickEvent(val uiModel: UserContactUIModel) : UserContactEvent()
}
