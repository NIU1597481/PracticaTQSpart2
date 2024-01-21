Feature: DescarregaAplicacio

  La pàgina web també ofereix els seus serveis que ofereixen a la web, però en forma d'aplicació per ordinadors, des d'un
  botó que hi ha a la pàgina al ser clicat ens descarrega l'installer de l'aplicació.

  Scenario: Descarreguem l'apliació
    Given I am on the page where the download is available
    When I click on the download button
    Then I should get the file downloaded on my computer