package com.joaquin.fintonicmarvelchallenge.data.models.apimodel

data class SeriesList (
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<SeriesSummary>
) {}