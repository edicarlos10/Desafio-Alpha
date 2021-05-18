package com.cide.challenge.controller

import android.content.Context
import com.cide.challenge.Class.hotel.Filters
import com.cide.challenge.Class.hotel.Result
import com.cide.challenge.model.HotelModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class HotelController {

    /**
     * Returns a list of hotels ordered by stars
     */
    fun getAllResults(context: Context): List<Result> {
        return HotelModel().loadAll(context).results.sortedWith(compareBy { it.stars }).asReversed()
    }

    /**
     * Returns filters
     */
    fun getFilters(context: Context): Filters {
        return HotelModel().loadAll(context).filters
    }

    /**
     * Write hotel class to json string
     */
    fun writeHotelToJson(hotel: Result): String {
        return jacksonObjectMapper().writeValueAsString(hotel)
    }

    /**
     * Read json string and maps to the class Hotel(Result)
     */
    fun readHotelJson(json: String): Result {
        return jacksonObjectMapper().readValue(json)
    }
}