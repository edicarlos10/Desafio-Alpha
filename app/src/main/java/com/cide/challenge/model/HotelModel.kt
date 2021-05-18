package com.cide.challenge.model

import android.content.Context
import com.cide.challenge.Class.hotel.Hotel
import com.cide.challenge.helper.Helper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class HotelModel {

    /**
     * Function that reads the json file and returns the list of hotels and filters
     * using the Jackson library
     */
    fun loadAll(context: Context): Hotel {
        return jacksonObjectMapper().readValue(Helper.readJson(context, "hotel.json"))
    }
}