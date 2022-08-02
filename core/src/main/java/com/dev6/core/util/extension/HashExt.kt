package com.dev6.core.util.extension

fun <K, V> HashMap<K, V>.getKeyFirst( value: V) : K  = filterValues {it == value}.keys.first()
