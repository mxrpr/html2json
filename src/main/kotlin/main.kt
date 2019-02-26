import com.mxr.html.*

fun main(args: Array<String>) {
    val text = "<HTML><BODY><a href=\"www.index.hu\"> this is a long text </a> <an> another text </an><span>span text</span></BODY></HTML>";

    val html2json = HTML2JSON()

    val result : List<String> = html2json.parseHTML(text)
    val htmlElements : List<Token> = html2json.createTokens(result)
    val astTreeRootNode = html2json.buildAST(htmlElements)
    val jsonString = html2json.generateJSON(astTreeRootNode)

    println("JSON result for '$text': \n $jsonString")
}
