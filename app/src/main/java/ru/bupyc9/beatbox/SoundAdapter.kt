package ru.bupyc9.beatbox

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SoundAdapter(private val mSounds: MutableList<Sound>): RecyclerView.Adapter<SoundAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_sound, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(getItem(position))
    }

    override fun getItemCount(): Int = mSounds.size

    fun getItem(position: Int): Sound = mSounds.get(position)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val mButton: Button = itemView.findViewById(R.id.list_item_sound_button)
        private lateinit var mSound: Sound

        fun bind(sound: Sound) {
            mSound = sound
            mButton.text = mSound.name
        }
    }
}