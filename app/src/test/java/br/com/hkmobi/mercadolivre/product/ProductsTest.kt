package br.com.hkmobi.mercadolivre.product

import br.com.hkmobi.mercadolivre.ProductFactory
import org.junit.Test

import org.junit.Assert.*

class ProductsTest {

    @Test
    fun titleTest() {
        val product = ProductFactory.product()
        assertEquals("Celular Xiaomi", product.titleFormatted())
    }

    @Test
    fun titleIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals("-", product.titleFormatted())
    }

    @Test
    fun titleIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals("-", product.titleFormatted())
    }

    @Test
    fun currencyFormattedTest() {
        val product = ProductFactory.product()
        assertEquals("UYU", product.currencyFormatted())
    }

    @Test
    fun currencyFormattedIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals("USD", product.currencyFormatted())
    }

    @Test
    fun currencyFormattedIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals("USD", product.currencyFormatted())
    }

    @Test
    fun containsImageTest() {
        val product = ProductFactory.product()
        assertEquals(true, product.containsImage())
    }

    @Test
    fun containsImageIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals(false, product.containsImage())
    }

    @Test
    fun containsImageIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals(false, product.containsImage())
    }

    @Test
    fun ratingAverageTest() {
        val product = ProductFactory.product()
        assertEquals(4.7F, product.ratingAverage())
    }

    @Test
    fun ratingAverageIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals(0.0F, product.ratingAverage())
    }

    @Test
    fun ratingAverageIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals(0.0F, product.ratingAverage())
    }

    @Test
    fun reviewTotalTest() {
        val product = ProductFactory.product()
        assertEquals(189, product.reviewTotal())
    }

    @Test
    fun reviewTotalIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals(0, product.reviewTotal())
    }

    @Test
    fun reviewTotalIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals(0, product.reviewTotal())
    }

    @Test
    fun priceFormattedTest() {
        val product = ProductFactory.product()
        assertEquals("UYU 189,00", product.priceFormatted())
    }

    @Test
    fun priceFormattedIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals("US$ 0,00", product.priceFormatted())
    }

    @Test
    fun priceFormattedIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals("US$ 0,00", product.priceFormatted())
    }

    @Test
    fun priceAmountFormattedTest() {
        val product = ProductFactory.product()
        assertEquals("UYU 100,00", product.priceAmountFormatted())
    }

    @Test
    fun priceAmountFormattedIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals("US$ 0,00", product.priceAmountFormatted())
    }

    @Test
    fun priceAmountFormattedIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals("US$ 0,00", product.priceAmountFormatted())
    }

    @Test
    fun urlFormattedTest() {
        val product = ProductFactory.product()
        assertEquals("https://mlu-s1-p.mlstatic.com/618049-MLU29438505313_022019-I.jpg", product.urlFormatted())
    }

    @Test
    fun urlFormattedIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals("-", product.urlFormatted())
    }

    @Test
    fun urlFormattedIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals("-", product.urlFormatted())
    }

    @Test
    fun containsInstallmentsTest() {
        val product = ProductFactory.product()
        assertEquals(true, product.containsInstallments())
    }

    @Test
    fun containsInstallmentsIsEmptyTest() {
        val product = ProductFactory.productIsEmpty()
        assertEquals(true, product.containsInstallments())
    }

    @Test
    fun containsInstallmentsIsNullTest() {
        val product = ProductFactory.productIsNull()
        assertEquals(false, product.containsInstallments())
    }

}
