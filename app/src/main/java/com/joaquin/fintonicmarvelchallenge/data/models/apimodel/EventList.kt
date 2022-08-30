package com.joaquin.fintonicmarvelchallenge.data.models.apimodel

data class EventList (
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<EventSummary>
){}