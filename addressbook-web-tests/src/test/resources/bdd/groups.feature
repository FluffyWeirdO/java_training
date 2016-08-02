Feature: Groups

  Scenario: Group creation
    Given a set of groups
    When a new group with name xxx, header yyy and footer zzz is created
    Then the new set of groups is equal to the old set with the added group


  Scenario Outline: Group creation
    Given a set of groups
    When a new group with name <name>, header <header> and footer <footer> is created
    Then the new set of groups is equal to the old set with the added group

    Examples:
      | name | header | footer |
      | aaa  | bbb    | ccc    |
