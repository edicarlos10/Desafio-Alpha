package com.cide.challenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cide.challenge.Class.hotel.Gallery
import com.cide.challenge.R
import com.cide.challenge.helper.Helper

class HotelImageSliderAdapter(
    private val context: Context, private val gallety: List<Gallery>
) : PagerAdapter() {

    private var inflater: LayoutInflater? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return gallety.size
    }

    /**
     * Instantiate item showing images to slide show
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.hotel_slide_image, null)

        // image galley to set
        Helper.loadImages(gallety[position].url, view.findViewById(R.id.imageView_slide))

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}