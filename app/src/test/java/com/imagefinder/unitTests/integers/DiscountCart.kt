package com.imagefinder.unitTests.integers

import org.junit.Assert.assertEquals
import org.junit.Test

class DiscountCart {

    private fun calculateTotal(products: MutableList<Product>): Int {
        val numberOfBallsToDiscount = products.filter {
            it.name == "ball"
        }.size / 2

        val numberOfBatsToDiscount = products.filter {
            it.name == "bat"
        }.size / 3

        for (i in 1..numberOfBatsToDiscount) {
            products.add(Product("Free bat", -5))
        }

        for (i in 1..numberOfBallsToDiscount) {
            products.add(Product("Free Ball", -5))
        }

        val totalCost = products.sumOf {
            it.cost
        }
        return totalCost
    }

    @Test
    fun `Sum two unique items`() {
        val products = mutableListOf<Product>()
        products.add(Product("ball", 5))
        products.add(Product("bat", 5))
        assertEquals(10, calculateTotal(products = products))
    }

    @Test
    fun `Correct total if there is 2 for 1 on balls `() {
        val products2 = mutableListOf<Product>()
        products2.add(Product("ball", 5))
        products2.add(Product("ball", 5))
        products2.add(Product("ball", 5))
        products2.add(Product("bat", 5))
        assertEquals(15, calculateTotal(products = products2))
    }

    @Test
    fun `buy 3 bats get 1 free `() {
        val products3 = mutableListOf<Product>()
        products3.add(Product("ball", 5))
        products3.add(Product("ball", 5))
        products3.add(Product("bat", 5))
        products3.add(Product("bat", 5))
        products3.add(Product("bat", 5))
        assertEquals(15, calculateTotal(products = products3))
    }
}

data class Product(
    val name: String,
    val cost: Int,
)
//
//val numberOfBallsToDiscount = products.filter {
//    it.name == "ball"
//}.size / 2
//
//val numberOfBatsToDiscount = products.filter {
//    it.name == "bat"
//}.size / 3
//
//for (i in 1..numberOfBatsToDiscount) {
//    products.add(Product("Free bat", -5))
//}
//
//for (i in 1..numberOfBallsToDiscount) {
//    products.add(Product("Free Ball", -5))
//}
//
//val totalCost = products.sumOf {
//    it.cost
//}