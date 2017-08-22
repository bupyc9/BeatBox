package ru.bupyc9.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log
import java.io.IOException

class BeatBox(context: Context) {
    companion object {
        @JvmStatic private val TAG = BeatBox::class.java.simpleName
        @JvmStatic private val SOUNDS_FOLDER = "sample_sounds"
        @JvmStatic private val MAX_SOUNDS = 5
    }

    private var mAssets: AssetManager = context.assets
    private var mSoundPool: SoundPool
    var sounds: ArrayList<Sound> = arrayListOf()
        private set

    init {
        val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        mSoundPool = SoundPool.Builder()
                .setMaxStreams(MAX_SOUNDS)
                .setAudioAttributes(attributes)
                .build()

        loadSounds()
    }

    private fun loadSounds() {
        var soundNames = arrayOf<String>()

        try {
            soundNames = mAssets.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found ${soundNames.size} sounds")
        } catch (e: IOException) {
            Log.e(TAG, "Could not list assets", e)
            return
        }


        soundNames.forEach {
            try {
                val assetPath = SOUNDS_FOLDER + "/" + it
                val sound = Sound(assetPath)
                load(sound)
                sounds.add(sound)
            } catch (e: IOException) {
                Log.e(TAG, "Could not load sound ${it}")
            }
        }
    }

    private fun load(sound: Sound) {
        val afd = mAssets.openFd(sound.assetPath)
        sound.soundId = mSoundPool.load(afd, 1)
    }

    fun play(sound: Sound) {
        if (sound.soundId <= 0) {
            return
        }

        mSoundPool.play(sound.soundId, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    public fun release() {
        mSoundPool.release()
    }
}