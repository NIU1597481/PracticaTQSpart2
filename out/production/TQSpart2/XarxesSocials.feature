Feature: XarxesSocials

  Des de diferents apartats de la pàgina web podem clicar a l'icona de diferents xarxes socials
  i en ser clicats ens han de redirigir al perfil de l'empresa a diferents xarxes socials.

  Scenario: Accedim al Twitter de l'empresa
    Given I am on the main page
    When I click on the "twitter" logo
    Then I should be redirected to "https://twitter.com/trackernetwork"

  Scenario: Accedim a l'Instagram de l'empresa
    Given I am on the main page
    When I click on the "instagram" logo
    Then I should be redirected to "https://www.instagram.com/trackernetwork/"

  Scenario: Accedim al Discord de l'empresa
    Given I am on the main page
    When I click on the "discord" logo
    Then I should be redirected to "https://discord.com/invite/trackernetwork"