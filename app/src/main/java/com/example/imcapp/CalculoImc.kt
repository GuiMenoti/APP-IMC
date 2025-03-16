package com.example.imcapp

import kotlin.math.pow

fun calularImc(peso: Double, altura: Double): Double {
    var imc = peso / (altura / 100).pow(2)
    return imc

}