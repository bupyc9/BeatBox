package ru.bupyc9.beatbox

data class Sound(
        var assetPath: String,
        var soundId: Int = 0
) {
    val name: String

    init {
        val components = assetPath.split("/")
        val fileName = components[components.size - 1]
        name = fileName.replace(".wav", "")
    }
}