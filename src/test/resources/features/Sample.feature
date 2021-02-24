Feature: Hello World

  @login
  Scenario: Registration flow validation via app
    As a user I should be able to see my google account
    when I try to register myself in Quikr

    When I launch Quikr App
    And I choose to log in using Google
    Then I see account picker screen with my email address "f.test.automator@gmail.com"


  @search
  Scenario: Search for a used Honda City car in Bangalore city

    When I launch Quikr App
    And I choose "Bangalore" as my city
    And I search for "Honda City" under Used Cars
    Then I should see the first car result as "Honda"