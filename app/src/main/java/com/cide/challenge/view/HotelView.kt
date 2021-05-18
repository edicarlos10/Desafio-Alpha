package com.cide.challenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cide.challenge.Class.hotel.Result
import com.cide.challenge.R
import com.cide.challenge.adapter.HotelRecyclerAdapter
import com.cide.challenge.controller.HotelController
import kotlinx.coroutines.*

class HotelView : Fragment() {

    companion object {
        fun newInstance() = HotelView()
    }

    private var controller: HotelController = HotelController() // Controller
    private lateinit var hotelRecyclerAdapter: HotelRecyclerAdapter // adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hotel_view, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val results: MutableList<Result> = mutableListOf()
        val constraintLayoutContainer: ConstraintLayout = itemView.findViewById(R.id.hotel_view)
        hotelRecyclerAdapter = HotelRecyclerAdapter(results)

        val recyclerView =
            view!!.findViewById(R.id.recycler_view) as RecyclerView // The recycler view id

        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            adapter = hotelRecyclerAdapter  // Set the custom adapter to the RecyclerView
        }

        // Coroutine process.Load hotel list asynchronously
        // by notifying the adapter and removing the animation at the end of loading.
        loadsHotelList(results, constraintLayoutContainer)
    }

    /**
     * Loads hotel list asynchronously
     */
    private fun loadsHotelList(
        results: MutableList<Result>,
        constraintLayoutContainer: ConstraintLayout
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val list: Deferred<List<Result>> = async {
                controller.getAllResults((context!!))
            } // getting hotels list
            val hotelList: List<Result> = list.await()// waiting hotels list

            // Delegates the list ready for the main thread
            // to be able to assign to the visualization interfaces
            withContext(Dispatchers.Main) {

                //let, to ensure that there is information
                hotelList.let {
                    results.clear()
                    results.addAll(hotelList)
                    hotelRecyclerAdapter.notifyDataSetChanged()//notify adapter

                    // Remove the lottie animation
                    constraintLayoutContainer.removeView(
                        constraintLayoutContainer.getViewById(
                            R.id.animationHotelView
                        )
                    )
                }
            }
        }
    }
}