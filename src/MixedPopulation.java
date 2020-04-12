
public class MixedPopulation extends Population{
  int numShelterInPlace;
  int numEssential;
  int numSkeptic;
  int numFlier;
  int numSheltered;

  public MixedPopulation(int numShelterInPlace, int numEssential, int numSkeptic, int numFlier, int numSheltered){
    super(numShelterInPlace + numEssential + numSkeptic + numFlier + numSheltered);
    this.numShelterInPlace = numShelterInPlace;
    this.numEssential = numEssential;
    this.numSkeptic = numSkeptic;
    this.numFlier = numFlier;
    this.numSheltered = numSheltered;

  }

  public void createPeople(){
    for(int i=0; i<this.numShelterInPlace; i++){
      this.addPerson(new StayAtHome());
    }
    for(int i=0; i<this.numEssential; i++){
      this.addPerson(new StayAtHomeIfSick());
    }
    for(int i=0; i<this.numSkeptic; i++){
      this.addPerson(new Skeptic());
    }
    for(int i=0; i<this.numFlier; i++){
        this.addPerson(new FrequentFlier());
      }
    for(int i=0; i<this.numSheltered; i++){
        this.addPerson(new Sheltered());
      }
  }
  
}
