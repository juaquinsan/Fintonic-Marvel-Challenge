package com.joaquin.fintonicmarvelchallenge.domain

import com.joaquin.fintonicmarvelchallenge.data.MarvelRepository
import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.CharacterDataWrapper
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor (
    private val repository: MarvelRepository
) {
    suspend operator fun invoke(id: Int): CharacterDataWrapper = repository.getCharacterByID(id)
}