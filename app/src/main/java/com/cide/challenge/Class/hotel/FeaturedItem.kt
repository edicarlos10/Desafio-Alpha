package com.cide.challenge.Class.hotel

data class FeaturedItem(
    val amenities: List<String>,
    val description: String, // Para seu conforto e bem estar, todas as acomodações oferecem frigobar, calefação central, televisores LCD com TV a Cabo e DVD, secador de cabelo, roupas de cama em algodão, toalhas king size, ducha pressurizada com aquecimento central, sabonetes e shampoos com aroma do campo, facilidade de bebidas quentes. Será disponível um berço para a criança na gratuidade como cortesia.
    val image: String, // https://static.hotelurbano.com/reservas/prodaccommodation/30607/58c8470703a3d_pousada-aardvark-inn.jpg
    val name: String // Standard Duplo
)