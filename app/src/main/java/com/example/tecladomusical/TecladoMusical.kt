package com.example.tecladomusical

import android.media.SoundPool
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TecladoMusical(soundPool: SoundPool, mapaDeNotas: Map<String, Int>) {
    val etiquetasDeNotas = listOf("Do", "Re", "Mi", "Fa", "Sol", "La", "Si")
    var notaActual by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)), // Fondo de la pantalla
        verticalArrangement = Arrangement.SpaceEvenly, // Notas en vertical
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for ((index, nota) in etiquetasDeNotas.withIndex()) {
            // Fondito rosa y negro
            val colorDeFondo = if (index % 2 == 0) Color(0xFFFFC0CB) else Color.Black

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // Ajustar la altura de cada tecla
                    .background(
                        color = if (notaActual == nota) colorDeFondo.copy(alpha = 0.7f) else colorDeFondo,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        notaActual = nota
                        reproducirNota(soundPool, mapaDeNotas[nota] ?: 0)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = nota, fontSize = 24.sp, color = Color.White)
            }
        }
    }
}

fun reproducirNota(soundPool: SoundPool, idSonido: Int) {
    if (idSonido != 0) {
        soundPool.play(idSonido, 1f, 1f, 1, 0, 1f)
    }
}
