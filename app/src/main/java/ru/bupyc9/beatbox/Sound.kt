package ru.bupyc9.beatbox

class Sound(val assetPath: String) {
    val name: String

    init {
        val components = assetPath.split("/")
        val fileName = components[components.size - 1]
        name = fileName.replace(".wav", "")
    }
}