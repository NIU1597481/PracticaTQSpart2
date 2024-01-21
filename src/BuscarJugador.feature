Feature: BuscarJugador

  La pàgina que estem testejant està centrada en el seguiment del perfil de jugadors en divers-sos videojocs, veient estadístiques d'aquests
  i fites que han assolit. A partir d'aquesta feature en forma de cercador podem buscar el nom dels jugadors per veure les seves estadístiques.

  Scenario: Busquem el perfil d'un jugador verídic
    Given I am on the game tracking page
    When I enter the player nickname "ProCatalanMoDzHD"
    And I select the platform "playstation" where he plays
    Then I should be able to see all the information related to his profile in the game

  Scenario: Busquem el perfil d'un jugador inventat
    Given I am on the game tracking page
    When I enter the player nickname "inventada_prova"
    And I select the platform "xbox" where he plays
    Then I should be informed with the following error "404 Not Found. We are unable to find your profile."

  Scenario: Busquem el perfil d'un jugador verídic, però seleccionem que juga en una altra plataforma
    Given I am on the game tracking page
    When I enter the player nickname "ProCatalanMoDzHD"
    And I select the platform "xbox" where he plays
    Then I should be informed with the following error "404 Not Found. We are unable to find your profile."