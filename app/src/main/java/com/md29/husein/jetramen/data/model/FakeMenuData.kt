package com.md29.husein.jetramen.data.model

import com.md29.husein.jetramen.R

object FakeMenuData {
    val dummyMenu = listOf(
        Menu(1, R.drawable.shoyu_ramen, "Shoyu Ramen", "Ramen klasik dengan kuah kaldu kaya rasa shoyu (kecap asin) yang memberikan cita rasa gurih. Dihidangkan dengan mi ramen, sayuran segar, dan potongan daging ayam lembut.", 18000),
        Menu(2, R.drawable.miso_ramen, "Miso Ramen", "Ramen dengan kuah miso yang kental dan bercita rasa unik. Disajikan dengan mi ramen, jamur, jagung manis, dan irisan daging sapi tanpa lemak.", 15000),
        Menu(3, R.drawable.shio_ramen, "Shio Ramen", "Ramen dengan kuah clear (jernih) berbumbu garam laut, memberikan rasa ringan dan segar. Tersedia dengan mi ramen, daun bawang, serta pilihan topping seafood seperti udang atau kerang.", 25000),
        Menu(4, R.drawable.vegetarian_ramen, "Vegetarian Ramen", "Ramen untuk para vegetarian dengan kuah sayuran yang kaya rasa. Mi ramen disajikan dengan berbagai jenis sayuran seperti wortel, brokoli, jamur, dan tahu sebagai alternatif protein.", 20000),
        Menu(5, R.drawable.tantanmen_ramen, "Tan Tan Men", "Ramen pedas dengan kuah gurih dan bumbu tan tan (pasta wijen pedas). Dihidangkan dengan mi ramen, daging ayam cincang, dan sayuran hijau seperti bayam atau bok choy.", 21000),
        Menu(6, R.drawable.curry_ramen, "Curry Ramen", "Ramen dengan kuah kari kental dan aromatik. Mi ramen disajikan dengan irisan daging sapi, kentang, wortel, dan bawang hijau, menciptakan perpaduan rasa kari yang lezat.", 30000),
        Menu(7, R.drawable.sesamechicken_ramen, "Sesame Chicken Ramen", "Ramen dengan kuah gurih berbasis wijen dan daging ayam yang dimasak dengan saus wijen. Mi ramen disajikan dengan potongan daging ayam yang renyah, biji wijen, dan sayuran segar.", 28000),
        Menu(8, R.drawable.kakeudon_ramen, "Kake Udon Ramen", "Udon dengan kuah clear yang lezat dan sederhana. Dihidangkan dengan mi udon yang tebal dan lembut, disertai dengan daun bawang dan irisan nori, menciptakan hidangan yang ringan namun memuaskan.", 35000),
        Menu(9, R.drawable.ginger_garlic_noodle_soup, "Ginger Garlic Soup Ramen", "Ramen dengan kuah yang dipenuhi dengan aroma jahe dan bawang putih. Mi ramen disajikan dengan potongan daging ayam, daun bawang, dan irisan tipis jahe, menciptakan hidangan yang menyegarkan.", 25000),
        Menu(10, R.drawable.coconutcurry_ramen, "Coconut Curry Ramen", "Ramen dengan kuah kari berbasis kelapa yang memberikan kelezatan kaya dan lembut. Mi ramen disajikan dengan potongan daging ayam, tauge, dan irisan daun ketumbar, menciptakan pengalaman kuliner yang eksotis.", 38000),
        Menu(11, R.drawable.green_tea_latte, "Green Tea Latte", "Minuman segar yang menggabungkan bubuk matcha dengan susu, menciptakan paduan rasa creamy dan sedikit pahit yang cocok sebagai pendamping hidangan ramen atau udon.", 12000),
        Menu(12, R.drawable.hojicha_roasted_tea, "Hojicha Roasted Tea", "Teh hojicha yang dipanggang dengan rasa yang kaya dan aroma yang unik.", 14000),
        Menu(13, R.drawable.passion_fruit_iced_tea, "Passion Fruit Iced Tea", "Teh es dengan perasan buah markisa yang memberikan rasa manis dan asam yang seimbang. Cocok sebagai pendamping untuk menyegarkan mulut setelah menyantap hidangan ramen atau udon.", 10000),
        Menu(14, R.drawable.sakura_blossom_iced_tea, "Sakura Blossom Ice Tea", "Teh es dengan aroma bunga sakura yang memberikan sentuhan lembut dan harum.", 18000),
        Menu(15, R.drawable.sesame_almond_milkshake, "Sesame Almond Milkshake", "Minuman susu shake dengan campuran pasta wijen dan almond. Kombinasi rasa creamy dan nutty menciptakan minuman penutup yang lezat.", 17000),
    )

    val dummyBestSellerMenu = dummyMenu.shuffled()
}