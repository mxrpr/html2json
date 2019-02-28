import com.mxr.html.HTML2JSON
import cucumber.api.java8.En
import org.junit.Assert

class Html2jsonStep  : En {
    private var html: String = ""
    private var result: String = ""
    private val html2json = HTML2JSON()

    init {
        Given("^I have content (.*?)$") { arg: String ->
            this.html = arg
        }

        When("^I call html2json$") {
            this.result = this.html2json.generateJSON(this.html)
        }

        Then("^Result should be (.*?)$") { expected: String ->
            Assert.assertEquals(this.result.replace("\n",""), expected)
        }
    }
}