package com.solodemo.main.model

import com.solo.components.Constants


enum class Featured(
    val imagePath: String = "",
    val title: String = "",
    val description: String = "Default Description",
    val price: String = "₱00.00"
) {
    CheesyHavenDeluxe(
        imagePath = Constants.StaticImages.CheesyHavenDeluxe,
        title = "Cheesy Haven Deluxe",
        description = "Immerse yourself in the ultimate cheesy delight atop a juicy patty.",
        price = "₱340.00"
    ),
    TropicalHawaiianPizza(
        imagePath = Constants.StaticImages.TropicalHawaiianPizza,
        title = "Tropical Hawaiian Pizza",
        description = "Experience the best of both worlds with a Hawaiian twist—ham, pineapple, and a duo of cheeses for a delightful tropical and cheesy fusion.",
        price = "₱560.00"
    ),
    FilipinoSpaghetti(
        imagePath = Constants.StaticImages.FilipinoSpaghetti,
        title = "Filipino Spaghetti",
        description = "Distinguished by its unique sauce made with banana ketchup and brown sugar.",
        price = "₱260.00"
    ),


}