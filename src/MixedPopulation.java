package lesson19;

public class MixedPopulation extends Population{
  int numShelterInPlace;
  int numEssential;
  int numOthers;

  public MixedPopulation(int numShelterInPlace, int numEssential, int numOthers){
    super(numShelterInPlace + numEssential + numOthers);
    this.numShelterInPlace = numShelterInPlace;
    this.numEssential = numEssential;
    this.numOthers = numOthers;

  }

  public void createPeople(){
    for(int i=0; i<this.numShelterInPlace; i++){
      this.addPerson(new StayAtHome());
    }
    for(int i=0; i<this.numEssential; i++){
      this.addPerson(new StayAtHomeIfSick());
    }
    for(int i=0; i<this.numOthers; i++){
      this.addPerson(new Person());
    }
  }
}
