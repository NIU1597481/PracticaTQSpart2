Feature: Login

  El sistema de login de la web ens permet iniciar sessió a la pàgina web, un cop iniciada
  la sessió podem realitzar accions restringides als usuaris sense identificar.

  Scenario: Login correcte
    Given I am on the login page
    When I enter the username "ProCatalanMoDzHD"
    And I enter the password "undostres4"
    And I log in
    Then I should be logged in

  Scenario: Login camp contrasenya incorrecte
    Given I am on the login page
    When I enter the username "ProCatalabMoDzHD"
    And I enter the password "incorrecte"
    And I log in
    Then I should see the login error message "Invalid Username/Password"

  Scenario: Login amb camp buit
    Given I am on the login page
    When I enter the username ""
    And I enter the password "undostres4"
    And I log in
    Then I should not be able to log in and should be reminded by the page to complete the field

  Scenario: Login amb usuari incorrecte
    Given I am on the login page
    When I enter the username "admin"
    And I enter the password "undostres4"
    And I log in
    Then I should see the login error message "Invalid Username/Password"