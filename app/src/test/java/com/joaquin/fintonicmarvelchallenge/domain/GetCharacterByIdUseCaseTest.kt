package com.joaquin.fintonicmarvelchallenge.domain

import com.joaquin.fintonicmarvelchallenge.data.MarvelRepository
import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*


class GetCharacterByIdUseCaseTest {

    @RelaxedMockK
    private lateinit var marvelRepository: MarvelRepository

    lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @Before
    fun onBefore() {
        /*
            Intialize Mock library
         */
        MockKAnnotations.init(this)

        getCharacterByIdUseCase = GetCharacterByIdUseCase(marvelRepository)
    }

    @Test
    fun `when Marvel API return something then get values by ID from API` () = runBlocking {
        // Given
        // Create dummy response API model
        val url = Url(
            "",
            ""
        )

        val thumbnail = Image(
            "",
            ""
        )

        val comicSummary = ComicSummary(
            "",
            ""
        )

        val comicList = ComicList(
            0,
            0,
            "",
            arrayListOf(comicSummary)
        )

        val storySummary = StorySummary(
            "",
            "",
            ""
        )

        val storyList = StoryList(
            0,
            0,
            "",
            arrayListOf(storySummary)
        )

        val eventSummary = EventSummary(
            "",
            ""
        )

        val eventList = EventList(
            0,
            0,
            "",
            arrayListOf(eventSummary)
        )

        val seriesSummary = SeriesSummary(
            "",
            ""
        )

        val seriesList = SeriesList(
            0,
            0,
            "",
            arrayListOf(seriesSummary)
        )

        val character = Character(
            1011334,
            "3-D Man",
            "",
            Date(),
            "",
            arrayListOf(url),
            thumbnail,
            comicList,
            storyList,
            eventList,
            seriesList
        )

        val characterDataContainer = CharacterDataContainer(
            0,
            20,
            1562,
            1,
            arrayListOf(character)
        )

        val characterDataWrapper = CharacterDataWrapper(
            200,
            "© 2022 MARVEL",
            "Data provided by Marvel. © 2022 MARVEL",
            "<a href=\"http://marvel.com\">Data provided by Marvel. © 2022 MARVEL</a>",
            "b9e8dbb4be479fba5daa7499f4faa52889a788bb",
            characterDataContainer,
            "b9e8dbb4be479fba5daa7499f4faa52889a788bb"
        )

        coEvery { marvelRepository.getCharacterByID(1011334) } returns characterDataWrapper

        // When
        val response = getCharacterByIdUseCase(1011334)

        // Then
        coVerify (exactly = 1) { marvelRepository.getCharacterByID(1011334) }
        assert(response == characterDataWrapper)
    }
}