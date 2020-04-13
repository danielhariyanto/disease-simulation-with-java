import java.util.Arrays;
/**
 * copied from RunSimulation; has its own main method
 * run to find reports on:
 * 		the average number of days it takes until there are no new infections,
 *  	the average number of people infected, and
 *  	the maximum number of people infected at any one time
 */

public class AnalyzeSimulation {
	// the maximum number of days the simulation will run
	private static int MAX_TICKS=1000;
	public static int tick;
	public static int numOfDays;
	public static int numOfInfected;
	public static int peakInfectedLevel;

	public static void main(String[] args) {
		// first we get the simulation parameters
		// from the command line

		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int numStayHome = Integer.parseInt(args[2]);
		int numEssential = Integer.parseInt(args[3]);
		int numSkeptic = Integer.parseInt(args[4]);
		int numFlier = Integer.parseInt(args[5]);
		int numSheltered = Integer.parseInt(args[6]);
		int numSimulations = Integer.parseInt(args[7]);
		
		System.out.printf("%10s%14s%18s%22s%n","Simulation #","numOfDays","numOfInfected","peakInfectedLevel");
		
		for (int i = 0; i < numSimulations; i++) {
			int[] peakInfected = new int[MAX_TICKS];
			
			// next we create the population and the country
			Population population;
	
			//population = new Population(numPeople);
			//population = new AllStayAtHome(numPeople);
			//int numEssential = numPeople/10;
			//int numOther = numPeople/20;
			//int numStayHome = numPeople - numEssential - numOther;
			population = new MixedPopulation(numStayHome, numEssential, numSkeptic, numFlier, numSheltered);
			population.createPeople();
	
			Country country = new Country(width,height);
			// and add a link to the population in the country
			country.population = population;
		
			// next we place the people into the country randomly
			population.placePeople(country);

			//System.out.println("Initial State of the Country");
			//country.printCountry();

			//System.out.println("\nTracking the Infection");
			for(tick = 0; tick <MAX_TICKS; tick++) {
				country.simulateOneStep();
				//country.printState(tick);
				
				peakInfected[tick] = country.numInfected;

				if (country.numInfected==0) {
					break;
				}
			}
			//System.out.println("\nFinal State of the Country");
			//country.printCountry();
			
			Arrays.sort(peakInfected);
			
			// we realize that tick will give numOfDays and country.numRecovered will give numOfInfected
			System.out.printf("%10d%14d%18d%22d%n",i+1,tick,country.numRecovered,peakInfected[MAX_TICKS-1]);
			numOfDays += tick;
			numOfInfected += country.numRecovered;
			peakInfectedLevel += peakInfected[MAX_TICKS-1];
		}
		
		System.out.printf("%n%10s%14d%18d%22d%n","Average:",numOfDays/numSimulations,numOfInfected/numSimulations,peakInfectedLevel/numSimulations);
	}
}
