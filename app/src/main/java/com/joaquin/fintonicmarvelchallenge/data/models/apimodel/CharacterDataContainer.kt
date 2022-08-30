package com.joaquin.fintonicmarvelchallenge.data.models.apimodel

data class CharacterDataContainer (
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<Character>
){}