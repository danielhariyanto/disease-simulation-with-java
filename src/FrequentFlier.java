import java.util.Random;

public class FrequentFlier extends Person {
	private Random random = new Random();
	
	public FrequentFlier() {
		super();
	}

	@Override
	void tryToMove() {
		// find an open random space for the flier
		int i = random.nextInt(country.places.length);
		int j = random.nextInt(country.places[i].length);
		while (country.places[i][j] != null) {
			// loop to make sure it is an open space ...
			i = random.nextInt(country.places.length);
			j = random.nextInt(country.places[i].length);
		}
		//System.out.println("placing "+p.id+" at "+i+","+j);
		// moves the flier to an open position
		super.moveTo(i, j);
	} 
}
