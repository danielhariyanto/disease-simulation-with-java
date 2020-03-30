package lesson19;

public class Country {

	/** these are summary statistics about the epidemic
	*/
	int numInfected=0;
	int numRecovered=0;
	int numPeople=0;

	/**
	 * places is a 2d array representing the places a person could be
	 * each entry can contain either a Person or null
	 */
	public Person[][] places;

	/**
	population is an array of all people in the country
	this is the same set of people as in the places array
	*/
	Population population;

	public Country(int height, int width) {
		this.places = new Person[height][width];
	}

	public Country() {
		this(10,20);
	}

	public void printState(int day){
		System.out.printf("%3d ",day);
		for(int i =0; i<numInfected; i++){
			System.out.print("I");
		}
		for(int i=0; i<numPeople-numInfected-numRecovered; i++){
			System.out.print("_");
		}
		for(int i=0; i<numRecovered; i++){
			System.out.print("R");
		}
		System.out.println();
	}

	public void simulateOneStep(){
		//System.out.println("size= "+population.getSize());
		//System.out.println(java.util.Arrays.toString(population.people));
		for(int i=0; i<population.getSize(); i++){
			population.people[i].tick();
		}
		for(int i=0; i<population.getSize(); i++){
			population.people[i].infectNeighbors();
		}
		this.getStats();
	}

	public void getStats() {
		// count number of infected and print
		int count = 0;
		int numPeople=0;
		int recovered = 0;
		// here we iterate through places,
		// but it would be better to iterate through population
		for(int i=0; i<places.length; i++) {
			for(int j=0; j<places[i].length; j++) {
				if (places[i][j] != null) {
					numPeople++;
					if (places[i][j].recovered) {
						recovered++;
					}
					else if (places[i][j].infected) {
						count++;
					}
				}
			}
		}
		this.numInfected = count;
		this.numPeople = numPeople;
		this.numRecovered = recovered;
	}

	public void printStats() {
		this.getStats();
		System.out.println("infected: "+ this.numInfected
		      +"/"+this.numPeople
					+" recovered:"+this.numRecovered);

	}

	public void printCountry() {

		for(int i=0; i<places.length; i++) {
			for(int j=0; j<places[i].length; j++) {
				Person p = places[i][j];
				String s = "_";
				if (p!=null) {
					s = p.toString();
				}
				System.out.printf("%s",s);
			}
			System.out.println();
		}
	}

}
