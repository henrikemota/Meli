package br.com.hkmobi.mercadolivre.utils

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Copyright 2019.
 * ************************************************************
 * Descrição:
 * Autor : Henrique
 * Data : 10/02/2019
 * ************************************************************
 */

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}