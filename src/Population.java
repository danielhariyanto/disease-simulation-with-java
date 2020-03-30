package lesson19;

import java.util.Random;


/**
A population is a list of people whose types are
Person or some subclass of Person.

The Population class can have subclasses of different
kinds of populations!

*/
public class Population{
  public Person[] people;
  private int numPeople = 0;
  private Random random = new Random();

  public Population(int n){
    this.people = new Person[n];
    //this.createPeople();
  }

  public void createPeople(){
    for(int i=0; i<this.people.length; i++){
      this.addPerson(new Person());
    }
  }


  public void addPerson(Person p){
    people[numPeople++] = p;
  }

  public int getSize(){
    return numPeople;
  }

  /** this randomly places the people on the grid */
  public void placePeople(Country country){
    for(int k=0; k<numPeople; k++){
      Person p = people[k];
      p.country = country;

      // find an open random space for the person
      int i = random.nextInt(country.places.length);
      int j = random.nextInt(country.places[i].length);
      while (country.places[i][j] != null) {
        // loop to make sure it is an open space ...
        i = random.nextInt(country.places.length);
        j = random.nextInt(country.places[i].length);
      }
      //System.out.println("placing "+p.id+" at "+i+","+j);

      p.setPosition(i,j);
      country.places[i][j]=p;
    }
    // infect someone
    people[0].infected = true;
  }

}
