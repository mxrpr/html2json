@wronghtml
Feature: Wrong HTML convert

  Scenario: Convert simple html
    Given I have content <HTML><BODY><a href="www.index.hu"> text </BODY></HTML>
    When I call html2json and InvalidHTMLContentException is thrown
    Then Result is empty