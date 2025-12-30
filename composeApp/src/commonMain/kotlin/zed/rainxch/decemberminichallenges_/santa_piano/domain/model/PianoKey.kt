package zed.rainxch.decemberminichallenges_.santa_piano.domain.model

enum class PianoKey(
    val musicPath: String,
) {
    Chime(
        musicPath = "chime_sound.wav"
    ),
    JingleBells(
        musicPath = "jingle_bells.wav"
    ),
    MessToy(
        musicPath = "mess_toy_piano.wav"
    ),
    SleighBells(
        musicPath = "sleigh_bells.wav"
    ),
    SnowStep(
        musicPath = "snow_step.wav"
    ),
    TinyMetallicBell(
        musicPath = "tiny_metallic_bell.mp3"
    ),
    ToyPiano(
        musicPath = "toy_piano_key.wav"
    ),
}