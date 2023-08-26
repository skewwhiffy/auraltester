package com.skewwhiffy.auraltester.helper

import java.security.SecureRandom

private val random = SecureRandom()

fun <T> oneOf(source: List<T>) = source[random.nextInt(source.size)]

fun oneOf(source: IntRange) = oneOf(source.toList())

fun oneOf(source: String) = oneOf(source.toList()).toString()
