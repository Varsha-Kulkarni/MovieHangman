package dev.varshakulkarni.moviehangman.presentation.utils

fun List<String>.contains(s: String, ignoreCase: Boolean = true): Boolean {

    return any { it.equals(s, ignoreCase) }
}