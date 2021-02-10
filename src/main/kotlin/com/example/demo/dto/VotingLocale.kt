package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class VotingLocale (
        @JsonProperty("area")
        val area: String?,
        @JsonProperty("borough_name")
        val borough_name: String?,
        @JsonProperty("address_line")
        val address_line: String?,
        @JsonProperty("polling_place_id")
        val polling_place_id: String?,
        @JsonProperty("borough_id")
        val borough_id: String?,
        @JsonProperty("county_name")
        val county_name: String?,
        @JsonProperty("polling_place_name")
        val polling_place_name: String?,
        @JsonProperty("gps_coordinates")
        val gps_coordinates: String?,
        @JsonProperty("municipality_id")
        val municipality_id: String?,
        @JsonProperty("county_id")
        val county_id: String?,
        @JsonProperty("municipality_name")
        val municipality_name: String?,
        @JsonProperty("election_day_voting")
        val election_day_voting: String?,
        @JsonProperty("info_text")
        val info_text: String?,
        @JsonProperty("opening_hours")
        val opening_hours: String?,
        @JsonProperty("postal_code")
        val postal_code: String?
        )

@JsonIgnoreProperties(ignoreUnknown = true)
data class VotingLocales(
        @JsonProperty("entries")
        val locales: List<VotingLocale>
)

//area	"Halden"
//borough_name	""
//address_line	"Øvre Bankegt 5"
//polling_place_id	"1"
//borough_id	""
//county_name	"Østfold"
//polling_place_name	"Konservativen"
//gps_coordinates	"59.12273755942663, 11.383713253967243"
//municipality_id	"101"
//county_id	"1"
//municipality_name	"Halden"
//election_day_voting	"1"
//info_text	"Husk gyldig legitimasjon…t er ikke legitimasjon."
//opening_hours	"2017-09-10 16:00:00,2017…:00,2017-09-11 20:00:00"
//postal_code	"1771"