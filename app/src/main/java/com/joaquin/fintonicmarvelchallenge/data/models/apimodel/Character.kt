package com.joaquin.fintonicmarvelchallenge.data.models.apimodel

import java.util.*
import kotlin.collections.ArrayList

data class Character (
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: ArrayList<Url>,
    val thumbnail: Image,
    val comics: ComicList,
    val stories: StoryList,
    val events: EventList,
    val series: SeriesList
) { }