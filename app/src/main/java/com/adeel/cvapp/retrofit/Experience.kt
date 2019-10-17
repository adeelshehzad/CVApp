package com.adeel.cvapp.retrofit

data class Experience(
    val employer_name: String,
    val designation: String,
    val from_date: String,
    val is_current_job: Boolean,
    val location: String,
    val more_details: List<String>,
    val to_date: String
)