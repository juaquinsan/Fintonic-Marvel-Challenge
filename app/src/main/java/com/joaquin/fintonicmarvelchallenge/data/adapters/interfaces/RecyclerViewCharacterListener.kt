package com.joaquin.fintonicmarvelchallenge.data.adapters.interfaces

import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.Character

/*
    Interface recyclerview character list
 */
interface RecyclerViewCharacterListener {
    fun onClick(character: Character, position: Int)
}