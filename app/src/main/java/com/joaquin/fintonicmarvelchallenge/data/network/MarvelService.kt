package com.joaquin.fintonicmarvelchallenge.data.network

import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.CharacterDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MarvelService @Inject constructor(
    private val marvelAPI : MarvelApiClient
){
    /*
        API INFORMATION CONNECTOR
     */
    private val api_key = "fb780f8e14243645546f5b1e01ca63df"
    private val ts = "1661439481"
    private val hash = "eb80d64791c5ebecb863c53593a4a58a"
    // ********************************************************

    /*
        Retrofit API call all characters
     */
    suspend fun getAllCharacters() : CharacterDataWrapper {
        return withContext(Dispatchers.IO) {
            val response : Response<CharacterDataWrapper> =
                marvelAPI.getAllCharactersInfo(api_key, ts, hash)
            response.body() ?: CharacterDataWrapper(999, "null",
                "null", "null", "null", null, "null")
        }
    }

    /*
        Retrofit API call character by ID
     */
    suspend fun getCharacterInfoById(id: Int) : CharacterDataWrapper {
        return withContext(Dispatchers.IO) {
            val response : Response<CharacterDataWrapper> =
                marvelAPI.getCharacterInfoById(id, api_key, ts, hash)
            response.body() ?: CharacterDataWrapper(999, "null",
                "null", "null", "null", null, "null")
        }
    }
}