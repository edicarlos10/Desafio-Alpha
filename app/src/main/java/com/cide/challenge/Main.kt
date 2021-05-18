package com.cide.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cide.challenge.view.HotelView

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameCont, HotelView.newInstance())
                .commitNow()//hotel view
        }
    }
}