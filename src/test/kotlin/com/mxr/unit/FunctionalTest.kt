package com.mxr.unit

import com.mxr.html.HTML2JSON
import com.mxr.html.InvalidHTMLContentException
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FuncTests {

    var html2JSON: HTML2JSON? = null

    @Before
    fun createConverter() {
        this.html2JSON = HTML2JSON()
    }

    @Test
    fun testEmptyHTML() {
        val jsonResult = this.html2JSON?.generateJSON("")
        Assert.assertEquals("{\n}", jsonResult)
    }

    @Test
    fun testSimpleHTML() {
        val jsonResult = this.html2JSON?.generateJSON("<HTML></HTML>")
        Assert.assertEquals("{\n" +
                "\"HTML\":{\n" +
                "}\n" +
                "}", jsonResult)
    }

    @Test(expected = InvalidHTMLContentException::class)
    fun testWrongHTML() {
        val jsonResult = this.html2JSON?.generateJSON("<HTML><a></a>")
        Assert.fail("Wrong html content must throw exception")
    }
}