package com.cide.challenge.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cide.challenge.Class.hotel.Result
import com.cide.challenge.R
import com.cide.challenge.constants.Constants
import com.cide.challenge.controller.HotelController
import com.cide.challenge.helper.Helper
import com.cide.challenge.view.HotelDetailsView


class HotelRecyclerAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<HotelRecyclerAdapter.ViewHolder>() {
    private var controller: HotelController = HotelController() // Controller

    // View Holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Set ids
        var itemName: TextView = itemView.findViewById(R.id.hotelName)
        var itemPrice: TextView = itemView.findViewById(R.id.hotelPrice)
        var itemRatingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        var itemImage: ImageView = itemView.findViewById(R.id.hotelImageView)
        var itemAddress: TextView = itemView.findViewById(R.id.hotelCity)
        var itemState: TextView = itemView.findViewById(R.id.hotelState)
        var itemAmenity1: TextView = itemView.findViewById(R.id.hotelAmenity1)
        var itemAmenity2: TextView = itemView.findViewById(R.id.hotelAmenity2)
        var itemAmenity3: TextView = itemView.findViewById(R.id.hotelAmenity3)

        // Click item and open details and send data by json string.
        init {
            itemView.setOnClickListener {
                val result: String =
                    controller.writeHotelToJson(results[absoluteAdapterPosition])
                val intent = Intent(itemView.context, HotelDetailsView::class.java).apply {
                    putExtra(Constants.hotelConstant, result)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    /**
     *  Create view holder
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.hotel_recycler_adapter, viewGroup, false)
        return ViewHolder(v)
    }

    /**
     * Binding values to view holder shows
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemName.text = results[i].name
        viewHolder.itemPrice.text = results[i].price.current_price.toString()
        viewHolder.itemRatingBar.numStars = results[i].stars
        viewHolder.itemAddress.text = results[i].address.city
        viewHolder.itemState.text = results[i].address.state
        Helper.loadImages(results[i].image, viewHolder.itemImage)//image to set

        // 3 amenity to set
        Helper.setAmenity(
            results[i].amenities,
            viewHolder.itemAmenity1,
            viewHolder.itemAmenity2,
            viewHolder.itemAmenity3
        )
    }

    /**
     * Number of itens from list
     */
    override fun getItemCount(): Int {
        return results.size
    }
}