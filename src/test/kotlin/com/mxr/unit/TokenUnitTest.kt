package com.mxr.unit

import com.mxr.html.HtmlToken
import com.mxr.html.TokenType
import org.junit.Assert
import org.junit.Test

class TokenTest {

    @Test
    fun testHTMLToken_IsTerminating() {
        val htmlToken = HtmlToken("<HTML>")
        Assert.assertFalse(htmlToken.isTerminatingElement())
    }

    @Test
    fun testHTMLToken_ElementValue() {
        val htmlToken = HtmlToken("<HTML>")
        Assert.assertEquals("HTML",htmlToken.elementValue )
    }

    @Test
    fun testHTMLToken_ElementType() {
        val htmlToken = HtmlToken("<HTML>")
        Assert.assertEquals(TokenType.HTML,htmlToken.tokenType )
    }

    @Test
    fun testHTMLToken_value() {
        val htmlToken = HtmlToken("<HTML>")
        Assert.assertEquals("<HTML>",htmlToken.value )
    }

}