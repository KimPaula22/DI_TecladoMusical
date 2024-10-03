package com.example.tecladomusical

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tecladomusical.ui.theme.TecladoMusicalTheme

class MainActivity : ComponentActivity() {

    private lateinit var soundPool: SoundPool
    private lateinit var mapaDeNotas: Map<String, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar los atributos de audio para SoundPool
        val atributosDeAudio = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        // Inicializar SoundPool con un máximo de 1 stream para monofonía
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(atributosDeAudio)
            .build()

        // Cargar los sonidos en SoundPool y mapearlos
        mapaDeNotas = mapOf(
            "Do" to soundPool.load(this, R.raw.doo, 1),
            "Re" to soundPool.load(this, R.raw.re, 1),
            "Mi" to soundPool.load(this, R.raw.mi, 1),
            "Fa" to soundPool.load(this, R.raw.fa, 1),
            "Sol" to soundPool.load(this, R.raw.sol, 1),
            "La" to soundPool.load(this, R.raw.la, 1),
            "Si" to soundPool.load(this, R.raw.si, 1)
        )

        setContent {
            TecladoMusicalTheme {
                TecladoMusical(soundPool, mapaDeNotas)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release() // Liberar recursos de SoundPool
    }
}

