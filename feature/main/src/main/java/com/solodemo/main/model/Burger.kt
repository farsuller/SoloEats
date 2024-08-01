package com.solodemo.main.model

import com.solo.components.Constants

enum class Burger(
    val imagePath: String = "",
    val title: String = "",
    val description: String = "Default Description",
    val price: String = "00.00",
) {
    CheesyHavenDeluxe(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Cheesy Haven Deluxe",
        description = "Immerse yourself in the ultimate cheesy delight atop a juicy patty.",
        price = "340.00",
    ),
    GardenGastronomyBurger(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Garden Gastronomy Burger",
        description = "Delight in a vegetarian masterpiece with a fresh garden vegetable patty.",
        price = "360.00",
    ),
    MapleBaconMarvelBurger(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Maple Bacon Marvel Burger",
        description = "Experience a harmonious blend of sweet maple syrup and crispy bacon.",
        price = "340.00",
    ),
    MediterraneanMingleBurger(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Mediterranean Mingle Burger",
        description = "Transport your taste buds with an herb-infused patty and regional toppings.",
        price = "290.00",
    ),
    SmokingSummitBurger(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Smoking Summit Burger",
        description = "Enjoy a smoky twist with doubled meaty char-grilled patty topped.",
        price = "490.00",
    ),
    SouthwestSizzleSupreme(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Southwest Sizzle Supreme",
        description = "Take a journey to the Southwest with a sizzling, spice-infused burger.",
        price = "290.00",
    ),
    SpicyBaconFusionFiestaBurger(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Spicy Bacon Fusion Fiesta Burger",
        description = "Embark on a fiery adventure with a boldly spiced patty and exciting toppings.",
        price = "320.00",
    ),
    ZestyZingBurger(
        imagePath = Constants.StaticImages.bannerBurger,
        title = "Zesty Zing Burger",
        description = "Experience a punch of zesty and tangy flavors in every bite.",
        price = "280.00",
    ),
}
