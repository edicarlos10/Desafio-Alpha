package com.cide.challenge.view

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.cide.challenge.Class.hotel.Result
import com.cide.challenge.R
import com.cide.challenge.adapter.HotelImageSliderAdapter
import com.cide.challenge.constants.Constants
import com.cide.challenge.controller.HotelController
import com.cide.challenge.helper.Helper


class HotelDetailsView : AppCompatActivity() {
    private var controller: HotelController = HotelController() // Controller
    private lateinit var hotel: Result // Hotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_details_view)

        // Ids from xml designer
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val hotelName = findViewById<TextView>(R.id.hotelName)
        val hotelPrice = findViewById<TextView>(R.id.hotelPrice)
        val hotelCity = findViewById<TextView>(R.id.hotelCity)
        val hotelState = findViewById<TextView>(R.id.hotelState)
        val hotelSmallDesc = findViewById<TextView>(R.id.hotelSmallDesc)
        val itemAmenity1 = findViewById<TextView>(R.id.hotelAmenity1)
        val itemAmenity2 = findViewById<TextView>(R.id.hotelAmenity2)
        val itemAmenity3 = findViewById<TextView>(R.id.hotelAmenity3)
        val buttonReserve = findViewById<Button>(R.id.HotelButton)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)// view pager ID
        val adult = findViewById<TextView>(R.id.hotelAdult)
        val children = findViewById<TextView>(R.id.hotelChildre)
        val imageGallerySize = findViewById<TextView>(R.id.imageSize)
        val textReadMoreLess = findViewById<TextView>(R.id.hotelReadMoreLess)

        // get put extra
        val bundle = intent.extras
        var jsonString = ""
        if (bundle != null) {
            jsonString = bundle.getString(Constants.hotelConstant).toString()
        }

        // Json string mapper to hotel class
        hotel = controller.readHotelJson(jsonString)

        // value to property
        setValues(
            ratingBar,
            hotelName,
            hotelPrice,
            hotelCity,
            hotelState,
            hotelSmallDesc,
            adult,
            children,
            imageGallerySize
        )

        // 3 amenity to set
        Helper.setAmenity(
            hotel.amenities,
            itemAmenity1,
            itemAmenity2,
            itemAmenity3
        )

        // Button click redirect to hurb app or web site
        buttonReserve.setOnClickListener {
            Helper.openUrl(hotel.url, this)
        }

        // set in adapter
        imageSliderImplementation(viewPager)

        // Click to show description complete/small of the hotel
        textReadMoreLess.setOnClickListener {
            if (textReadMoreLess.text.contentEquals(getString(R.string.hotel_read_more), true)) {
                hotelSmallDesc.text = hotel.description
                textReadMoreLess.text = getString(R.string.hotel_read_less)
                Helper.setUnderline(textReadMoreLess, getString(R.string.hotel_read_less))
            } else {
                hotelSmallDesc.text = hotel.smallDescription
                textReadMoreLess.text = getString(R.string.hotel_read_more)
                Helper.setUnderline(textReadMoreLess, getString(R.string.hotel_read_more))
            }
        }
    }

    /**
     * Set the values in the property view
     */
    private fun setValues(
        ratingBar: RatingBar,
        hotelName: TextView,
        hotelPrice: TextView,
        hotelCity: TextView,
        hotelState: TextView,
        hotelSmallDesc: TextView,
        adult: TextView,
        children: TextView,
        imageGallerySize: TextView
    ) {
        ratingBar.numStars = hotel.stars
        hotelName.text = hotel.name
        hotelPrice.text = hotel.price.current_price.toString()
        hotelCity.text = hotel.address.city
        hotelState.text = hotel.address.state
        hotelSmallDesc.text = hotel.smallDescription
        adult.text = hotel.quantityDescriptors.maxAdults.toString()
        children.text = hotel.quantityDescriptors.maxChildren.toString()
        imageGallerySize.text = hotel.gallery.size.toString()
    }

    /**
     * Slider Implementation
     * The adapter processes the code and images, and the slide is done.
     */
    private fun imageSliderImplementation(viewPager: ViewPager) {
        val adapter = HotelImageSliderAdapter(this, hotel.gallery)// adapter
        viewPager.adapter = adapter
    }

}