package com.cide.challenge.helper

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.ImageView
import android.widget.TextView
import com.cide.challenge.Class.hotel.AmenityX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object Helper {

    /**
     * Function that reads a json file and returns the json string
     */
    fun readJson(context: Context, jsonName: String): String {
        val json: String
        try {
            json = context.assets.open(jsonName).bufferedReader().use { it.readText() }
        } catch (error: IOException) {
            error.printStackTrace()
            return ""
        }
        return json
    }

    /**
     * Loads the images asynchronously and set in the imageView
     */
    fun loadImages(url: String, imageView: ImageView) = CoroutineScope(Dispatchers.Main).launch {
        val task = async(Dispatchers.IO) {
            try {
                // Try connect to external image
                val connection: HttpURLConnection = URL(url)
                    .openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                //set stream to bitmap
                val input: InputStream = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        imageView.setImageBitmap(task.await() as Bitmap?)//se to image view
    }

    /**
     * Setting 3 amenitie if to exist
     */
    fun setAmenity(amenities: List<AmenityX>, am: TextView, am2: TextView, am3: TextView) {
        if (amenities.getOrNull(0) != null) am.text = amenities[0].name
        if (amenities.getOrNull(1) != null) am2.text = amenities[1].name
        if (amenities.getOrNull(2) != null) am3.text = amenities[2].name
    }

    /**
     * Set underline in some textview
     */
    fun setUnderline(textReadMoreLess: TextView, string: String) {
        val content = SpannableString(string)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        textReadMoreLess.text = content
    }

    /**
     * Open some external url (Merchandising)
     */
    fun openUrl(urls: String, context: Context) {
        val intents = Intent(Intent.ACTION_VIEW, Uri.parse(urls))
        Bundle().putBoolean("new_window", true)
        intents.putExtras(Bundle())
        context.startActivity(intents)
    }
}