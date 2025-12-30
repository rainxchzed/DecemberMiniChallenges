package zed.rainxch.decemberminichallenges_.core.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentLinkedQueue
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.LineEvent

class JvmAudioPlayer : AudioPlayer {
    private val activeClips = ConcurrentLinkedQueue<Clip>()
    private val maxClips = 10

    override suspend fun play(resourcePath: String) = withContext(Dispatchers.IO) {
        try {
            activeClips.removeAll { !it.isActive && !it.isRunning }

            while (activeClips.size >= maxClips) {
                activeClips.poll()?.close()
            }

            var audioInputStream = AudioSystem.getAudioInputStream(
                this::class.java.classLoader.getResource("sounds/$resourcePath")
            )

            val baseFormat = audioInputStream.format

            val decodedFormat = AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.sampleRate,
                16,
                baseFormat.channels,
                baseFormat.channels * 2,
                baseFormat.sampleRate,
                false
            )

            if (baseFormat != decodedFormat) {
                audioInputStream = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream)
            }

            val clip = AudioSystem.getClip()
            clip.open(audioInputStream)

            clip.addLineListener { event ->
                if (event.type == LineEvent.Type.STOP) {
                    activeClips.remove(clip)
                    clip.close()
                }
            }

            activeClips.add(clip)
            clip.start()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun cleanup() {
        activeClips.forEach { it.close() }
        activeClips.clear()
    }
}