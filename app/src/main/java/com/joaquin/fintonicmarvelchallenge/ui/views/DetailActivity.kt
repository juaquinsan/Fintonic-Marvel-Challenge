package com.joaquin.fintonicmarvelchallenge.ui.views

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.joaquin.fintonicmarvelchallenge.databinding.ActivityDetailBinding
import com.joaquin.fintonicmarvelchallenge.ui.viewmodels.CharactersViewModel
import com.joaquin.fintonicmarvelchallenge.utils.goToActivity
import com.joaquin.fintonicmarvelchallenge.utils.loadByUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    private val charactersViewModel : CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            Get product selected from intent
         */
        val characterId = intent.getIntExtra("characterid", 0)

        charactersViewModel.characterInfo(characterId)

        charactersViewModel.characterModel.observe(this) {
            // Parse character
            val charactersData = it

            if (charactersData.data != null) {
                val character = charactersData.data.results[0]

                if (charactersData.code == 200) {
                    // There are items to show, fill the information
                    binding.layoutCharacterInfo.visibility = View.VISIBLE
                    binding.layoutNoCharactersInfo.visibility = View.GONE

                    binding.imageViewCharacter.loadByUrl(
                        character.thumbnail.path.replace(
                            "http",
                            "https"
                        ) + '.' + character.thumbnail.extension
                    )

                    binding.textViewCharacterName.text = character.name
                    if (character.description != "") {
                        binding.textViewCharacterDescription.visibility = View.VISIBLE
                        binding.textViewCharacterDescription.text = character.description
                    }

                    binding.textViewComicsCount.text = "${character.comics.available}"
                    binding.textViewSeriesCount.text = "${character.series.available}"
                    binding.textViewStoriesCount.text = "${character.stories.available}"
                } else {
                    // An error occurs to show info
                    binding.layoutCharacterInfo.visibility = View.GONE
                    binding.layoutNoCharactersInfo.visibility = View.VISIBLE
                }
            } else {
                // An error occurs to show info
                binding.layoutCharacterInfo.visibility = View.GONE
                binding.layoutNoCharactersInfo.visibility = View.VISIBLE
            }
        }

        charactersViewModel.dataIsLoading.observe(this) {
            binding.progressBarLoadingContent.isVisible = it
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        goToActivity<MainActivity>()
    }
}