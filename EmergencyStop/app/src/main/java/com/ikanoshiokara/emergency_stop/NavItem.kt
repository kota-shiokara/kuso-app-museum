package com.ikanoshiokara.emergency_stop

sealed class NavItem(
    val name: String
) {
    object MainPage: NavItem("main")
}
