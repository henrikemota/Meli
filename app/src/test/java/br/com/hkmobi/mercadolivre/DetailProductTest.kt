package br.com.hkmobi.mercadolivre

import org.junit.Test

import org.junit.Assert.*

class DetailProductTest {

    @Test
    fun descriptionTest() {
        val product = ProductFactory.product()
        assertEquals("Descrição", product.plainTextFormmated())
    }

    @Test
    fun descriptionIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals("-", product.plainTextFormmated())
    }

    @Test
    fun descriptionIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals("-", product.plainTextFormmated())
    }
}
