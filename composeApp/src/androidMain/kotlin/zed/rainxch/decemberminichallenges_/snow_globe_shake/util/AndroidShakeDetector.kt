package zed.rainxch.decemberminichallenges_.snow_globe_shake.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.abs

class AndroidShakeDetector(
    private val context: Context
) : ShakeDetector, SensorEventListener {
    private var onShakeCallback: (() -> Unit)? = null
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private var lastShakeTime = 0L
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f
    private var isListening = false

    companion object {
        private const val SHAKE_THRESHOLD = 12f
        private const val SHAKE_INTERVAL = 3000L
    }

    override fun invoke(onShake: () -> Unit) {
        onShakeCallback = onShake
        startListening()
    }

    private fun startListening() {
        if (!isListening && accelerometer != null) {
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_UI
            )
            isListening = true
        }
    }

    fun stopListening() {
        if (isListening) {
            sensorManager.unregisterListener(this)
            isListening = false
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val currentTime = System.currentTimeMillis()

            if (currentTime - lastShakeTime > SHAKE_INTERVAL) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val deltaX = abs(x - lastX)
                val deltaY = abs(y - lastY)
                val deltaZ = abs(z - lastZ)

                if (deltaX > SHAKE_THRESHOLD ||
                    deltaY > SHAKE_THRESHOLD ||
                    deltaZ > SHAKE_THRESHOLD
                ) {
                    lastShakeTime = currentTime
                    onShakeCallback?.invoke()
                }

                lastX = x
                lastY = y
                lastZ = z
            }
        }
    }
}