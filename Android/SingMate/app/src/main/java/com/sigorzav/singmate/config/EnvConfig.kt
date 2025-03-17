package com.sigorzav.singmate.config

object EnvConfig {

    const val ENVIRONMENT = "LOCAL" // "LOCAL", "PROD"

    // ✅ API Base URL
    enum class BaseUrl(val url: String) {
        // LOCAL("http://localhost:8080/"),
        LOCAL("http://192.168.35.137:8080/"),
        // PROD("https://api.example.com/")
    }

}