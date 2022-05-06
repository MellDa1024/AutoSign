package com.mellda.managers

import com.lambda.client.manager.Manager

object AutoSignManager : Manager{
    private var signline = arrayOf("","","","")

    fun getSign(offset: Int) = signline[offset]

    fun changeSign(offset: Int, text: String) {
        signline[offset-1] = text
    }
}