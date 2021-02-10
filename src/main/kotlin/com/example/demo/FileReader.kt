package com.example.demo

import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

fun readFile(fileName: String) =
        String(
                getFileInputStream(fileName)
                        .readBytes()
        )

fun getFileInputStream(fileName: String): InputStream =
        File(".").walk().firstOrNull { it.name.equals(fileName, ignoreCase = true) }
                ?.inputStream()
                ?: throw FileNotFoundException("File ${fileName} not found")