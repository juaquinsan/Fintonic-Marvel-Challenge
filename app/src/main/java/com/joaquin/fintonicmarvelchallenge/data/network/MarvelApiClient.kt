package com.joaquin.fintonicmarvelchallenge.data.network

import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiClient {

    /*
        Return all characters from Marvel API limited to 20 results
     */
    @GET("characters")
    suspend fun getAllCharactersInfo(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Response<CharacterDataWrapper>

    /*
        Return all character information by character ID
     */
    @GET("characters/{id}")
    suspend fun getCharacterInfoById(@Path("id") id: Int,
                             @Query("apikey") apikey: String,
                             @Query("ts") ts: String,
                             @Query("hash") hash: String
    ): Response<CharacterDataWrapper>
}