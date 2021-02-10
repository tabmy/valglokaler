package com.example.demo

import com.example.demo.dto.VotingLocales
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.stereotype.Component

@Component
class ValglokaleRepository {

    private final val objectMapper = ObjectMapper().registerModule(KotlinModule())

    init {
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
    }

    fun getAllLocales() : VotingLocales{
        val localesJson = readFile("valglokaler.json")

           return ObjectMapper().readerFor(VotingLocales::class.java).readValue(localesJson)

    }

}