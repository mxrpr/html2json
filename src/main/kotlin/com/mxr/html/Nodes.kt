package com.mxr.html

enum class NodeType {
    ROOT, NODE, TEXT
}

open class Node (private val nodeType : NodeType, private val token: Token) {

    protected val childNodes : MutableList<Node> = mutableListOf()

    // html element name
    var htmlElement : String = ""

    // properties of and element like <a href="index.hu">
    var properties : MutableMap<String, String> = mutableMapOf()

    init {
        when (this.nodeType ) {
            // check for properties
            NodeType.NODE -> {
                val subElements = this.token.elementValue.split(" ")
                this.htmlElement = subElements[0]

                if (subElements.size > 1) {
                    for (i in 1 until subElements.size) {
                        val (key, value) = subElements[i].split("=")
                        this.properties.put(key, value)
                    }
                }
            }
            NodeType.TEXT -> {
                this.htmlElement = token.value
            }

        }
    }

    fun addChildNode(node: Node) {
        this.childNodes.add(node)
    }

    open fun render() : String {
        var stringBuilder = StringBuilder(500)

        with(stringBuilder) {
            append("\"$htmlElement\"")
            append(":")
            append("{\n")

            if (properties.count() > 0) {
                properties.forEach { (k, v) ->
                    append("\"$k\"")
                    append(":")
                    if (v.startsWith("\"")) {
                        append("\"")
                        append("${v.replace("\"", "", true)}")
                        append("\"")
                    }
                    else
                        append("\"$v\"")
                    append(",")
                }
            }

            if (childNodes.count() == 0)
                stringBuilder = delete(length-1, length)

            for (child in childNodes) {
                append(child.render())
                append(",")
            }
            stringBuilder = delete(length-1, length)
            append("}\n")
        }

        return stringBuilder.toString()
    }

    fun childCount() : Int = this.childNodes.count()
}

class TextNode(val token: Token ) : Node (NodeType.TEXT, token) {

    override fun render() : String {

        return with(StringBuilder()) {
            append("\"text\"")
            append(":")
            append("\"")
            append(htmlElement)
            append("\"")
        }.toString()
    }
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
