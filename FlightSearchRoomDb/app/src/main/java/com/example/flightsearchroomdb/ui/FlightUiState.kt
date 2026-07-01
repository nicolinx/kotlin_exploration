package com.example.flightsearchroomdb.ui

import com.example.flightsearchroomdb.data.Airport

data class FlightUiState(
    val searchQuery: String = "",
    val airportSuggestion: List<Airport> = emptyList(),
    val selectedAirport: Airport? = null,
)
