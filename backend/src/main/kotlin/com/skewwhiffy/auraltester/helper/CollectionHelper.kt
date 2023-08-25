package com.skewwhiffy.auraltester.helper

import java.security.SecureRandom

private val random = SecureRandom()

fun <T> oneOf(source: List<T>) = source.get(random.nextInt(source.size))