package com.mellda.managers

import com.lambda.client.manager.Manager

object AutoSignManager : Manager{
    private var signline = arrayOf("","","","")

    fun getSign(offset: Int) = signline[offset-1]

    fun changeSign(offset: Int, text: String) {
        signline[offset-1] = text
    }

    fun addSign(offset: Int, text: String) {
        signline[offset-1] = signline[offset-1]+text
    }

    fun sliceSign(offset: Int, startoffset: Int, endoffset: Int) {
        signline[offset-1] = signline[offset-1].slice(IntRange(startoffset-1,endoffset-1))
    }

    fun moveSign(offset: Int, moveoffset: Int) {
        signline[offset] = signline[offset-1].slice(IntRange(moveoffset,(signline[offset-1].length-1)))
        signline[offset-1] = signline[offset-1].slice(IntRange(0,moveoffset-1))
    }
}