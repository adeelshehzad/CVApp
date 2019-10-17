package com.adeel.cvapp.retrofit

data class CVResponse(
    val name: String,
    val designation: String,
    val address: String,
    val postal_code: String,
    val mobile_number: String,
    val email_address: String,
    val highlights: List<String>,
    val experience: List<Experience>,
    val education: List<Education>
)