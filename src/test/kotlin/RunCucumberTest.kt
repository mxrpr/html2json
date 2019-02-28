import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features=["src/test/kotlin/features/"
              ],
//    ,html2json.feature",
//"src/test/kotlin/features/wronghtml2json.feature
//    format = [ "pretty","html: cucumber-html-reports",
//        "json: cucumber-html-reports/cucumber.json" ],
//    dryRun = false,
    glue=[""]
)

class RunCucumberTest {
}