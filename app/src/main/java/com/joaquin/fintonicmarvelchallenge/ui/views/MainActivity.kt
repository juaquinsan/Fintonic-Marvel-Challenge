package com.joaquin.fintonicmarvelchallenge.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaquin.fintonicmarvelchallenge.data.adapters.CharactersAdapter
import com.joaquin.fintonicmarvelchallenge.data.adapters.interfaces.RecyclerViewCharacterListener
import com.joaquin.fintonicmarvelchallenge.databinding.ActivityMainBinding
import com.joaquin.fintonicmarvelchallenge.ui.viewmodels.CharactersViewModel
import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val charactersViewModel : CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charactersViewModel.onCreate()

        charactersViewModel.allCharacterModel.observe(this) {

            if (it.data != null) {
                if (it.code == 200) {
                    // There are items to show
                    binding.recyclerViewCharacters.visibility = View.VISIBLE
                    binding.layoutNoCharacters.visibility = View.GONE

                    binding.recyclerViewCharacters.setHasFixedSize(true)
                    binding.recyclerViewCharacters.itemAnimator = DefaultItemAnimator()
                    binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(this)

                    val charactersAdapter : CharactersAdapter = CharactersAdapter(it.data.results,
                        object: RecyclerViewCharacterListener {
                            override fun onClick(character: Character, position: Int) {
                                goDetailActivity(character.id)
                            }
                        })

                    binding.recyclerViewCharacters.adapter = charactersAdapter
                } else {
                    // There aren't items to show
                    binding.recyclerViewCharacters.visibility = View.GONE
                    binding.layoutNoCharacters.visibility = View.VISIBLE
                }
            } else {
                // There aren't items to show
                binding.recyclerViewCharacters.visibility = View.GONE
                binding.layoutNoCharacters.visibility = View.VISIBLE
            }
        }

        charactersViewModel.dataIsLoading.observe(this) {
            binding.progressBarLoadingContent.isVisible = it
        }
    }

    /*
        Go to DetailActivity passing character information selected
     */
    private fun goDetailActivity(characterId: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("characterid", characterId)
        startActivity(intent)
        finish()
    }
}