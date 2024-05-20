package com.heaven.fcm_24

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()

        val intent_o = intent
        val title = intent_o.getStringExtra("title")
        val message = intent_o.getStringExtra("body")
        val imagesUrl=intent_o.getStringExtra("image")

        findViewById<MaterialTextView>(R.id.tv_title).text=title
        findViewById<MaterialTextView>(R.id.tv_description).text = message
//            HtmlCompat.fromHtml(message.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        Glide.with(this).load("$imagesUrl")
                .error(R.mipmap.ic_launcher).into(findViewById(R.id.iv_image))
    }


}