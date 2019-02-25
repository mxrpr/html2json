package com.mxr.html

enum class NodeType {
    ROOT, NODE
}

open class Node (private val nodeType : NodeType, private val token: Token) {
    protected val childNodes : MutableList<Node> = mutableListOf()
    var htmlElement : String = ""
    var properties : MutableMap<String, String> = mutableMapOf()
    init {
        if (this.nodeType == NodeType.NODE) {
            // check for properties

            val subElements = this.token.elementValue.split(" ")
            this.htmlElement = subElements[0]

            if (subElements.size > 1) {
                for (i in 1..subElements.size) {
                    val (key, value) = subElements[i].split("=")
                    this.properties.put(key, value)
                }
            }
        }
    }

    fun addChildNode(node: Node) {
        this.childNodes.add(node)
    }

    open fun render() : String {

        return with(StringBuilder()) {
            append("\"$htmlElement\"")
            append(":")
            append("{\n")
            for (child in childNodes) {
                append(child.render())
            }
            append("}\n")
        }.toString()
    }

    fun childCount() : Int = this.childNodes.count()
}

class RootNode(val token: Token ) : Node (NodeType.ROOT, token) {

    override fun render() : String {

        return with(StringBuilder()) {
            append("{\n")
            for (child in childNodes) {
                append(child.render())
            }
            append("}")
        }.toString()
    }
}
