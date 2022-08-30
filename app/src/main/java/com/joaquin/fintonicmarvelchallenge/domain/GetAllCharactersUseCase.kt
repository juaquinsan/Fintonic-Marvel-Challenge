package com.joaquin.fintonicmarvelchallenge.domain

import com.joaquin.fintonicmarvelchallenge.data.MarvelRepository
import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.CharacterDataWrapper
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository : MarvelRepository
) {

    suspend operator fun invoke() : CharacterDataWrapper = repository.getAllCharacters()
}