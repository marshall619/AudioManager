package com.example.alarmapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmapp.data.model.Time
import com.example.alarmapp.repository.TimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeDbViewModel @Inject constructor(
    private val repository: TimeRepository
) : ViewModel() {

    val allTimeInDayList: StateFlow<List<Time>>
        get() = _allTimeInDayList

    private var _allTimeInDayList : MutableStateFlow<List<Time>> = MutableStateFlow(emptyList<Time>())

    fun allTimesInDay(day : String) {
        viewModelScope.launch {
            _allTimeInDayList.emit(repository.allTimesInDay(day))
        }
    }

    fun insertNewTimeAction(time: Time){
        viewModelScope.launch {
            repository.insertNewTimeAction(time)
        }
    }

    fun deleteTime(id : Int) {
        viewModelScope.launch {
            repository.deleteTime(id)
        }
    }

    fun deleteAllTimes() {
        viewModelScope.launch {
            repository.deleteAllTimes()
        }
    }

}