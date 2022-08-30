package com.joaquin.fintonicmarvelchallenge.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.CharacterDataWrapper
import com.joaquin.fintonicmarvelchallenge.domain.GetAllCharactersUseCase
import com.joaquin.fintonicmarvelchallenge.domain.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    /*
        Encapsulate models from LiveData
     */
    val allCharacterModel = MutableLiveData<CharacterDataWrapper>()
    val characterModel = MutableLiveData<CharacterDataWrapper>()
    val dataIsLoading = MutableLiveData<Boolean>()

    /*
        Call to All Characters Use Case
     */
    fun onCreate() {
        viewModelScope.launch {
            dataIsLoading.postValue(true)
            val result : CharacterDataWrapper = getAllCharactersUseCase()

            allCharacterModel.postValue(result)
            dataIsLoading.postValue(false)
        }
    }

    /*
        Call to one Characters by ID Use Case
     */
    fun characterInfo(id : Int) {
        viewModelScope.launch {
            dataIsLoading.postValue(true)
            //getCharacterByIdUseCase.id = id
            val result : CharacterDataWrapper = getCharacterByIdUseCase(id)

            characterModel.postValue(result)
            dataIsLoading.postValue(false)
        }
    }
}