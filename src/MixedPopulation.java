
public class MixedPopulation extends Population{
  int numShelterInPlace;
  int numEssential;
  int numSkeptic;
  int numFlier;

  public MixedPopulation(int numShelterInPlace, int numEssential, int numSkeptic, int numFlier){
    super(numShelterInPlace + numEssential + numSkeptic + numFlier);
    this.numShelterInPlace = numShelterInPlace;
    this.numEssential = numEssential;
    this.numSkeptic = numSkeptic;
    this.numFlier = numFlier;

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
  }
  
}
