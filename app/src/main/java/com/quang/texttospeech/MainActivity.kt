package com.quang.texttospeech

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private val mediaPlayer = MediaPlayer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSpeech.setOnClickListener {
            val text = edtText.text.toString().trim()
            if (text.isNotEmpty()) playMp3(text)
        }
    }

    private fun playMp3(text: String) {
        val headers = HashMap<String, String>()
        headers["Referer"] = "https://translate.google.com"
        headers["User-Agent"] = "stagefright/1.2 (Linux;Android 5.0)"
        val url = "https://translate.google.com/translate_tts?ie=UTF-8&tl=vi&client=tw-ob&q=${URLEncoder.encode(
            text,
            "UTF-8"
        )}"
        mediaPlayer.reset()
        mediaPlayer.setDataSource(this, Uri.parse(url), headers)
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }
}
