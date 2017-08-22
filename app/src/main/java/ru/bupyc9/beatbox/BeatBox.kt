package ru.bupyc9.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.IOException

class BeatBox(context: Context) {
    companion object {
        @JvmStatic private val TAG = BeatBox::class.java.simpleName
        @JvmStatic private val SOUNDS_FOLDER = "sample_sounds"
    }

    private var mAssets: AssetManager = context.assets
    var sounds: ArrayList<Sound> = arrayListOf()
        private set

    init {
        loadSounds()
    }

    private fun loadSounds() {
        try {
            val soundNames = mAssets.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found ${soundNames.size} sounds")

            soundNames.forEach {
                val assetPath = SOUNDS_FOLDER + "/" + it
                val sound = Sound(assetPath)
                sounds.add(sound)
            }
        } catch (e: IOException) {
            Log.e(TAG, "Could not list assets", e)
            return
        }
    }
}