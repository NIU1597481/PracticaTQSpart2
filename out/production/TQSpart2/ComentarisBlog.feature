Feature: ComentarisBlog

  La pàgina web té un apartat on es penjen notícies de tant en tant. Els usuaris registrats poden fer comentaris en aquestes
  publicacions.

  Scenario: Intentem fer un comentari en una publicació sense estar identificats
    Given I am on a news post page
    When I type the following text as a comment "Prova per la assignatura TQS"
    Then I should not be able to post it and recieve the message "You need to be signed in to comment"

  Scenario: Intentem fer un comentari en una publicació estan identificats però sense escriure res
    Given I am on a news post page identified as a user
    When I type the following text as a comment ""
    Then I should not be able to post it

  Scenario: Intentem fer un comentari en una publicació estan identificats i escrivin alguna cosa
    Given I am on a news post page identified as a user
    When I type the following text as a comment "Prova per la assignatura TQS"
    Then I should be able to post it