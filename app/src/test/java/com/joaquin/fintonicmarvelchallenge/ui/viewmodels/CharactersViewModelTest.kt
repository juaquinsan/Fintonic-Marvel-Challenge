package com.joaquin.fintonicmarvelchallenge.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.*
import com.joaquin.fintonicmarvelchallenge.domain.GetAllCharactersUseCase
import com.joaquin.fintonicmarvelchallenge.domain.GetCharacterByIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @RelaxedMockK
    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase
    @RelaxedMockK
    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    private lateinit var charactersViewModel: CharactersViewModel

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        /*
            Intialize Mock library
         */
        MockKAnnotations.init(this)

        charactersViewModel = CharactersViewModel(getAllCharactersUseCase, getCharacterByIdUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when CharactersViewModel is created at first time, get all characters and set the variable`() = runTest {
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

        coEvery { getAllCharactersUseCase() } returns characterDataWrapper

        // When
        charactersViewModel.onCreate()

        // Then
        assert(charactersViewModel.allCharacterModel.value == characterDataWrapper)
    }

    @Test
    fun `when CharactersViewmodel characterInfo return a character by ID on the livedata` () = runTest {
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

        coEvery { getCharacterByIdUseCase(1011334) } returns characterDataWrapper

        // When
        charactersViewModel.characterInfo(1011334)

        // Then
        assert(charactersViewModel.characterModel.value == characterDataWrapper)
    }
}