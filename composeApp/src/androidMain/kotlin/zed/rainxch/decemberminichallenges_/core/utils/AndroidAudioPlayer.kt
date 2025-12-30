package zed.rainxch.decemberminichallenges_.core.utils

import android.content.Context
import android.media.SoundPool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AndroidAudioPlayer(private val context: Context) : AudioPlayer {
    private val soundPool: SoundPool = SoundPool(
        /* maxStreams = */ 10,
        /* streamType = */ android.media.AudioManager.STREAM_MUSIC,
        /* srcQuality = */ 0
    )

    private val soundMap = mutableMapOf<String, Int>()

    init {
        preloadSounds()
    }

    private fun preloadSounds() {
        val sounds = listOf(
            "chime_sound.wav",
            "jingle_bells.wav",
            "mess_toy_piano.wav",
            "sleigh_bells.wav",
            "snow_step.wav",
            "tiny_metallic_bell.mp3",
            "toy_piano_key.wav"
        )

        sounds.forEach { soundFile ->
            try {
                val assetFileDescriptor = context.assets.openFd("sounds/$soundFile")
                val soundId = soundPool.load(assetFileDescriptor, 1)
                soundMap[soundFile] = soundId
                assetFileDescriptor.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun play(resourcePath: String) {
        withContext(Dispatchers.Main) {
            soundMap[resourcePath]?.let { soundId ->
                soundPool.play(
                    soundId,
                    1.0f,
                    1.0f,
                    1,
                    0,
                    1.0f
                )
            }
        }
    }

    fun release() {
        soundPool.release()
    }
}
