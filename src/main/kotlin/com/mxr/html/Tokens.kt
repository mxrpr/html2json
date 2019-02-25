package com.mxr.html

enum class TokenType {
    HTML, TEXT, ROOT
}

open class Token(val tokenType: TokenType, val value : String) {

    // is the Token a terminating one? For example: </a>
    private var isTerminatingElement = false

    // stores the HTML value without <, /, >
    var elementValue = ""

    init {
        when(tokenType) {
            TokenType.HTML, TokenType.ROOT -> {
                val regexp = "<.*?\\/".toRegex()
                val sequence
                        : Sequence<MatchResult> = regexp.findAll(this.value)
                if (sequence.count() > 0)
                    this.isTerminatingElement = true

                if (this.tokenType == TokenType.ROOT)
                    this.elementValue = ""
                else
                    this.elementValue = if (this.isTerminatingElement)
                                            this.value.substring(sequence.first().range.last, this.value.length)
                                        else
                                            this.value.substring(1, this.value.length - 1)
            }
            TokenType.TEXT -> this.elementValue = this.value

        }
    }

    fun isTerminatingElement() = this.isTerminatingElement
}

class TextToken(elementValue: String) : Token(TokenType.TEXT, elementValue)

class HtmlToken(elementValue: String) : Token(TokenType.HTML, elementValue)