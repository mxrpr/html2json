@html
Feature: HTML convert

  Scenario: Convert simple html
    Given I have content <HTML><BODY><a href="www.index.hu"> text</a> </BODY></HTML>
    When I call html2json
    Then Result should be {"HTML":{"BODY":{"a":{"href":"www.index.hu","text":" text"}}}}