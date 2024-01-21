Feature: CanviNomUsuari

  Els usuaris a la pàgina web poden canviar el seu nom d'usuari, les proves d'aquesta feature estan relacionades amb els requisits
  que han de complir aquests noms.

  Scenario: Canvi de nom d'usuari amb caràcters no alfanumèrics
    Given I am on the username changing page
    When I enter the new username "TQS%$"
    Then I should not be able to change the username receiving the following message "New Username Can only contain alphanumeric characters."

  Scenario: Canvi de nom d'usuari ficant el mateix que ja tenim
    Given I am on the username changing page
    When I enter the new username "ProCatalanMoDzHD"
    Then I should not be able to change the username receiving the following message "Username already exists. Please enter a different username."

  Scenario: Canvi de nom d'usuari, amb nou nom diferent i amb caràcters alfanumèrics
    Given I am on the username changing page
    When I enter the new username "ProvaTQS"
    Then I should be able to change the username

  Scenario: Canvi de nom d'usuari, amb nou nom buit
    Given I am on the username changing page
    When I enter the new username ""
    Then I should not be able to change the username receiving the following message "Please enter a username."