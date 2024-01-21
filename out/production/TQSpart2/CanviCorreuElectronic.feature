Feature: CanviCorreuElectronic

  Els usuaris a la pàgina web poden canviar el correu electrònic vinculat  la seva compte, les proves d'aquesta feature estan relacionades amb els requisits
  que han de complir aquests canvis de correu.

  Scenario: Canvi de correu ficant el mateix que ja es té actualment
    Given I am on the email changing page
    When I enter the new email "messi0074@gmail.com"
    Then I should not be able to change the email receiving the following message "The given email is already matched with another account."

  Scenario: Canvi de correu ficant un correu amb espais
    Given I am on the email changing page
    When I enter the new email "prova tqs @ gmail . com"
    Then I should not be able to change the email receiving the following message "Sorry, an error occurred while processing your request."

  Scenario: Canvi de correu ficant un correu gmail correcte
    Given I am on the email changing page
    When I enter the new email "arnaucg02@gmail.com"
    Then I should be able to change the email
