package com.cide.challenge.Class.hotel

data class Result(
    val address: Address,
    val amenities: List<AmenityX>,
    val category: String, // hotel
    val description: String, // Espaços charmosos, bons serviços, conforto nos detalhes e ambiente informalmente descontraído. Com atendimento personalizado feito por uma equipe de funcionários e gerenciamento familiar, buscamos oferecer constantemente um alto padrão de hospitalidade aliados a preços acessíveis.
    val featuredItem: FeaturedItem,
    val gallery: List<Gallery>,
    val hu_free_cancellation: Boolean, // true
    val id: String, // AT7987
    val image: String, // https://static.hotelurbano.com/reservas/prod0/7/7987/5d28d5cf6fa3c_pousada-aardvark-inn.jpg
    val isHotel: Boolean, // true
    val name: String, // Pousada Aardvark Inn
    val price: PriceX,
    val quantityDescriptors: QuantityDescriptors,
    val sku: String, // NHU-7987-0-0-0-0
    val smallDescription: String, // Espaço planejado para o total bem estar dos hóspedes que procuram prazer e comodidade em uma estadia na Serra.
    val stars: Int, // 3
    val tags: List<String>,
    val url: String // https://www.hurb.com/hoteis/gramado/pousada-aardvark-inn-7987
)