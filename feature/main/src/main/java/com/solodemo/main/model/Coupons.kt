package com.solodemo.main.model

import com.solo.components.Constants

enum class Coupons(
    val imagePath: String = "",
    val title: String = "",
    val description: String = "Default Description",
) {
    DeliveryFee(
        imagePath = "",
        title = "100% Free",
        description = "Free Delivery",
    ),

    BurgerSpree(
        imagePath = "",
        title = "40% Burger Spree",
        description = "Discounted Burger Prices",
    ),
    FreeDrinks(
        imagePath = Constants.StaticImages.ChocoCookieCream,
        title = "Choco Cookie Freebie",
        description = "Enjoy 1 Free Drink",
    ),
    PizzaDiscount(
        imagePath = Constants.StaticImages.TropicalHawaiianPizza,
        title = "Pizza Mania",
        description = "50% Off on All Pizzas",
    ),
    ComboDeal(
        imagePath = "",
        title = "Combo Bonanza",
        description = "Save Big with Our Combo Deals",
    ),
}
