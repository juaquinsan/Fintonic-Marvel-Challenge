package com.joaquin.fintonicmarvelchallenge.data.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joaquin.fintonicmarvelchallenge.utils.inflate
import com.joaquin.fintonicmarvelchallenge.R
import com.joaquin.fintonicmarvelchallenge.data.adapters.interfaces.RecyclerViewCharacterListener
import com.joaquin.fintonicmarvelchallenge.utils.loadByUrl
import com.joaquin.fintonicmarvelchallenge.data.models.apimodel.Character

class CharactersAdapter (private val characters: ArrayList<Character>,
                         private val listener: RecyclerViewCharacterListener
    ) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.card_characters))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position], listener)
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind (character: Character,
                  listener: RecyclerViewCharacterListener
        ) = with(itemView) {
            val imageViewCharacter = findViewById<ImageView>(R.id.imageViewCharacter)
            val textViewName = findViewById<TextView>(R.id.textViewCharacterName)
            val textViewDescription = findViewById<TextView>(R.id.textViewCharacterDescription)

            // Replace incorrect url provide by API to secure connection
            imageViewCharacter.loadByUrl(character.thumbnail.path.replace("http",
                "https") + '.' + character.thumbnail.extension)
            textViewName.text = character.name

            textViewDescription.text = character.description

            setOnClickListener { listener.onClick(character, adapterPosition) }
        }
    }
}