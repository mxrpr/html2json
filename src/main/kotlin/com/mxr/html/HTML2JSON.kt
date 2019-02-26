package com.mxr.html

import java.io.File
import java.io.FileNotFoundException

class HTML2JSON {

    /**
     * Generates JSON by calling the AST root element's render method
     *
     * @return String The JSON string
     */
    fun generateJSON(astRoot: Node): String {
        return astRoot.render()
    }


    /**
     * Build AST from the parsed tokens.
     *
     * @return Node The Abstract Syntax Tree
     */
    fun buildAST(htmlTokens: List<Token>): Node {
        val stack: MutableList<Node> = mutableListOf()
        val rootNode = RootNode(Token(TokenType.ROOT, ""))
        stack.add(rootNode)

        for (token in htmlTokens) {
            when (token.tokenType) {
                TokenType.HTML -> {
                    if (token.isTerminatingElement()) {
                        // remove the last node from the stack
                        stack.removeAt(stack.size - 1);
                    } else {
                        //create node
                        val tmpElement = Node(NodeType.NODE, token)
                        // add element to stack
                        stack.last().addChildNode(tmpElement)
                        stack.add(tmpElement)
                    }
                }
                TokenType.TEXT -> stack.last().addChildNode(TextNode(token))
                TokenType.ROOT -> {/*do nothing here*/
                }
            }
        }

        // if the stack contains more then one element, then the input html is wrong
        if (stack.count() > 1)
            throw InvalidHTMLContentException(stack.last().htmlElement)
        return rootNode
    }

    /**
     * Create Token objects from the parsed HTML string
     *
     * @return List<Token> List of tokens
     */
    fun createTokens(htmlElementList: List<String>): List<Token> {
        val result = mutableListOf<Token>()

        for (stringElement in htmlElementList) {
            if (stringElement.startsWith("<")) {
                val htmlElement = Token(TokenType.HTML, stringElement)
                result.add(htmlElement)
            } else {
                if (stringElement.trim().isEmpty())
                    continue
                val htmlElement = Token(TokenType.TEXT, stringElement)
                result.add(htmlElement)
            }
        }

        return result
    }

    /**
     * Parse HTML : try to divide into components
     *
     * @return List List of HTML elements
     */
    fun parseHTML(str: String): List<String> {
        val regexpStr = "<.+?>|[^<]*"
        val regexp = regexpStr.toRegex()
        val sequence: Sequence<MatchResult> = regexp.findAll(str)
        val result = mutableListOf<String>()
        for (element in sequence) {
            result.add(element.value)
        }

        return result
    }

    fun parseHTMLFromFile(fileName: String) {
        val file = File(fileName)
        if (!file.exists()) {
            println("File $fileName does not exists")
            throw FileNotFoundException("$fileName")
        }

        val content = file.inputStream().bufferedReader().use { it.readLine() }
        this.parseHTML(content)

    }
    /**
     * Generates the JSON from HTML
     */
    fun generateJSON(html: String) : String {
        val result : List<String> = this.parseHTML(html)
        val htmlElements : List<Token> = this.createTokens(result)
        val astTreeRootNode = this.buildAST(htmlElements)
        return this.generateJSON(astTreeRootNode)
    }
}