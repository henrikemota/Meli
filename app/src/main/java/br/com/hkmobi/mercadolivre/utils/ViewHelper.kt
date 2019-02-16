package br.com.hkmobi.mercadolivre.utils

import android.view.View

class ViewHelper {

    fun show(show: Boolean): Int {
        return if (show) View.VISIBLE else View.GONE
    }


    fun showOrInvisible(show: Boolean): Int {
        return if (show) View.VISIBLE else View.INVISIBLE
    }
}