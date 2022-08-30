package com.joaquin.fintonicmarvelchallenge.data

import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.CharacterDataWrapper
import com.joaquin.fintonicmarvelchallenge.data.network.MarvelService
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val api : MarvelService
) {

    suspend fun getAllCharacters(): CharacterDataWrapper {
        return api.getAllCharacters()
    }

    suspend fun getCharacterByID(id : Int) : CharacterDataWrapper {
        return api.getCharacterInfoById(id)
    }
}