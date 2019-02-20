package br.com.hkmobi.mercadolivre

import br.com.hkmobi.mercadolivre.data.model.Installment
import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.data.model.Review

class ProductFactory {

    companion object {
        fun product(): Product{
            return Product("MLU460267000",
                "Celular Xiaomi",
                "Descrição",
                "http://mlu-s1-p.mlstatic.com/618049-MLU29438505313_022019-I.jpg",
                Review(4.7F, 189),
                189.0,
                "UYU",
                Installment(10, 100.0, "UYU"),
                ArrayList()
            )
        }

        fun productIsEmpty(): Product{
            return Product("",
                "",
                "",
                "",
                Review(0F, 0),
                0.0,
                "",
                Installment(0, 0.0, ""),
                ArrayList())
        }

        fun productIsNull(): Product{
            return Product(null,
                null,
                null,
                null,
               null,
                null,
                null,
                null,
                null)
        }
    }
}