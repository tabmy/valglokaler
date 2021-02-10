package com.example.demo

import com.example.demo.dto.VotingLocale
import com.example.demo.dto.VotingLocales
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class ValglokalerController(
        val valglokaleRepository: ValglokaleRepository
) {

    @GetMapping("/")
    fun index(model: Model): String {
        return "index.html"
    }

    @GetMapping("/api/locales", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getLocales(
            @RequestParam("municipality") municipality: String? = null,
            @RequestParam("county") county: String? = null,
            @RequestParam("postalcode") postalCode: String? = null
    ): ResponseEntity<VotingLocales> {

        if (postalCode != null && postalCode.length != 4) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val locales = getFilteredLocales(
                municipality = municipality,
                county = county,
                postalCode = postalCode
        )

        return ResponseEntity(VotingLocales(locales), HttpStatus.OK)
    }



    @GetMapping("/api/counties", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getCounties(
            @RequestParam("municipality") municipality: String? = null,
            @RequestParam("county") county: String? = null,
            @RequestParam("postalcode") postalCode: String? = null
    ) : List<String?> {
        val locales = getFilteredLocales(
                municipality = municipality,
                county = county,
                postalCode = postalCode
        )

       return locales.map { it.county_name }.toSet().toList().sortedBy { it }
    }

    @GetMapping("/api/municipalities", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getMunicipalities(
            @RequestParam("municipality") municipality: String? = null,
            @RequestParam("county") county: String? = null,
            @RequestParam("postalcode") postalCode: String? = null
    ) : List<String?>  {
        val locales = getFilteredLocales(
                municipality = municipality,
                county = county,
                postalCode = postalCode
        )

        return locales.map { it.municipality_name }.toSet().toList().sortedBy { it }

    }

    @GetMapping("/api/postalcodes", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getPostalCodes(
            @RequestParam("municipality") municipality: String? = null,
            @RequestParam("county") county: String? = null,
            @RequestParam("postalcode") postalCode: String? = null
    ) : List<String?>  {
        val locales = getFilteredLocales(
                municipality = municipality,
                county = county,
                postalCode = postalCode
        )

        return locales.map { it.postal_code }.toSet().toList().sortedBy { it }
    }


    private fun getFilteredLocales(municipality: String?, county: String?, postalCode: String?): List<VotingLocale> {
        return valglokaleRepository.getAllLocales()
                .locales
                .filter { municipality?.equals(it.municipality_name, ignoreCase = true) ?: true }
                .filter { county?.equals(it.county_name, ignoreCase = true) ?: true }
                .filter { postalCode?.equals(it.postal_code, ignoreCase = true) ?: true }
    }

}