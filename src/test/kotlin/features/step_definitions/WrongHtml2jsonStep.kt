package features.step_definitions

import com.mxr.html.HTML2JSON
import com.mxr.html.InvalidHTMLContentException
import cucumber.api.java8.En
import org.junit.Assert


class WrongHtml2jsonStep : En {
    private var html: String = ""
    private var result: String = ""
    private val html2json = HTML2JSON()

    init {
        Given("^I have content (.*?)$") { arg: String ->
            this.html = arg
        }

        When("^I call html2json and InvalidHTMLContentException is thrown$") {
            try {
                this.result = this.html2json.generateJSON(this.html)
                Assert.fail("Test should throw InvalidHTMLContentException")
            }catch(e : InvalidHTMLContentException){
                //pass
            }
        }

        Then("^Result is empty$") {
            Assert.assertEquals(this.result, "")
        }
    }
}