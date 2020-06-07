# COVID-19 Disease Simulation

Population -- a model of the Population of a country, consisting of a list of Person elements, exactly one of whom is infected with a virus.

Country -- a 2d grid simulating a country, each position can hold at most one person.

Person -- a model of a person in an epidemic. They have a position and an infected state and potentially different probabilities of being infected and potentially different recovery times. Person has a tick() method in which the person may choose to move to another place.

RunSimulation -- this is a class which gets some data from he command line and uses it to create a Country and a Population. Then it runs a simulation until there are no more infected people. At each step it calls the tick method on all people to model movement in the country, and then calls the infectNeighbors method on infected people to see who they infect.

This program also contains three subclasses of Person:
StayAtHome -- this kind of person never moves
StayAtHomeIfSick -- this models an essential person. They move as regular unless they get sick.
Note that the default person is a skeptic and always tries to move to a random neighboring position and so can spread the virus.

The program also contains a few subclasses of Population
- AllStayAtHome is a population where everyone stays at home all the time, even essential personnel.
- MixedPopulation models a population with a specified number of StayAtHome, StatHomeIfSick, and regular Persons.
