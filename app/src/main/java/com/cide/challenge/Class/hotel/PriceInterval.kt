package com.cide.challenge.Class.hotel

data class PriceInterval(
    val filterPattern: String, // price_[min|max]_[value]
    val max: Int, // 483750
    val min: Int // 6646
)