package com.cide.challenge.Class.hotel

data class Filters(
    val amenities: List<Amenity>,
    val cities: List<City>,
    val countries: List<Country>,
    val priceInterval: PriceInterval,
    val prices: List<Price>,
    val productType: List<ProductType>,
    val rooms: List<Room>,
    val stars: List<Star>,
    val states: List<State>
)