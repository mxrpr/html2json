package com.mxr.unit

import com.mxr.html.*
import org.junit.Assert
import org.junit.Test

class NodeUnitTest {

    @Test
    fun testRootNode() {
        val rootNode = RootNode(Token(TokenType.ROOT, ""))
        Assert.assertEquals(TokenType.ROOT, rootNode.token.tokenType)
        Assert.assertEquals("", rootNode.htmlElement)
    }


    @Test
    fun testRootNode_addChildren() {
        val rootNode = RootNode(Token(TokenType.ROOT, ""))
        val token = HtmlToken("<HTML>")
        rootNode.addChildNode(Node(NodeType.NODE, token))
        Assert.assertEquals(1, rootNode.childCount())
    }

    @Test
    fun testRootNode_htmlElement() {
        val rootNode = RootNode(Token(TokenType.ROOT, ""))
        Assert.assertEquals("", rootNode.htmlElement)
    }

    @Test
    fun testNode_htmlElement() {

        val token = HtmlToken("<HTML>")
        //private val value: String, private val nodeType : NodeType, private val token: Token
        val node = Node(NodeType.NODE, token)

        Assert.assertEquals("HTML", node.htmlElement)
    }
}