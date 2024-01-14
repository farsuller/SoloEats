package com.solodemo.main.model

import com.solo.components.Constants


enum class Featured(
    val imagePath: String = "",
    val title: String = "",
    val description: String = "Default Description",
    val price: String = "00.00"
) {
    CheesyHavenDeluxe(
        imagePath = Constants.StaticImages.CheesyHavenDeluxe,
        title = "Cheesy Haven Deluxe",
        description = "Immerse yourself in the ultimate cheesy delight atop a juicy patty.",
        price = "340.00"
    ),
    TropicalHawaiianPizza(
        imagePath = Constants.StaticImages.TropicalHawaiianPizza,
        title = "Tropical Hawaiian Pizza",
        description = "Experience the best of both worlds with a Hawaiian twist—ham, pineapple, and a duo of cheeses for a delightful tropical and cheesy fusion.",
        price = "480.00"
    ),
    FilipinoSpaghetti(
        imagePath = Constants.StaticImages.FilipinoSpaghetti,
        title = "Filipino Spaghetti",
        description = "Distinguished by its unique sauce made with banana ketchup and brown sugar.",
        price = "170.00"
    ),
    HeartyInfusedBolognese(
        imagePath = Constants.StaticImages.HeartyHerbBolognese,
        title = "Hearty Herb-Infused Bolognese Harmony",
        description = "Savor the rich and savory with a medley of aromatic herbs, perfectly paired with al dente pasta for a comforting and flavorful harmony.",
        price = "190.00"
    ),
    CaramelMachiatto(
        imagePath = Constants.StaticImages.CaramelMachiatto,
        title = "Caramel Machiatto",
        description = "Savor the exquisite harmony of bold espresso, smooth steamed milk, and a caramel drizzle in every sip.",
        price = "120.00"
    ),
    ChocoCookieCream(
        imagePath = Constants.StaticImages.ChocoCookieCream,
        title = "Choco Cookie Cream",
        description = "Offers a blissful combination of rich chocolate, decadent cookie crumbles, and velvety cream for an irresistibly indulgent treat.",
        price = "170.00"
    ),


}