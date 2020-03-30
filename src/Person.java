package lesson19;
import java.util.Random;
/**
 The Person class models infected people in a 2d grid
 a person has a unique id, an (x,y) location in a country,
 and an infection status.
 The could be normal, infected, exposed, or recovered.

 Each person has an individual probablity of being infected when coming in contact with an infected person
 and they have an individual recovery time after they are infected, at which point they are no longer infectious.
 They can move one unit horizontally, vertically, or diagonally in the grid.

 The tick() method simulates them moving one step in the grid
 in some random direction as long as no one else is there.

*/

public class Person {
  // the persons fate depends on some random variables...
  private Random random = new Random();

  // we use the counter to give each Person a unique id
	private static int counter=1;
  int id = 0;

  // Next we need the location of the Person
  // the Country variable allows the user to "look around"
	int x;
	int y;
	Country country;


  // next we record their infection status
	boolean infected = false;
	boolean exposed = false; // after being exposed, one gets infect in next tick
	boolean recovered = false;
	double infectionProb = 0.5;  // probability of being infect when near a sick person
	int age = 0;  // their age in ticks
	int infectionTime = -1;  // -1 means they haven't yet been infected
	int recoveryTime = 5; // they are not infectious after recovery



	/**
	*/
	public Person(int x,int y,Country country) {
		this.x=x;
		this.y=y;
		this.id = Person.counter++;
		this.country=country;
	}

	public Person(){
		this.x=-1;
		this.y=-1;
		this.id = Person.counter++;
		this.country=null;
	}

	public void setPosition(int x, int y){
		this.x=x;
		this.y=y;
	}

	/**
	print a character for the Person representing
	their infection status
	*/
	public String toString() {
		String r = " ";
		if (this.recovered) {
			r="r";
		} else if (this.infected) {
			r="X";
		} else if (this.exposed) {
			r="E";
		} else {
			r="O";
		}
		return r;
	}

	/**
	 * tick() simulates the persons movement in one unit of time.
	 * it will change this.x and this.y
	 * We'll first just randomly try to move one step
	 */
	public void tick() {
    this.tryToMove();
    this.checkForInfection();

	}

  void tryToMove(){
		tryToMoveRandomly();
	}
  /**
	   try to move one step in a random direction.
		 if they way is blocked then don't move.
	*/
  void tryToMoveRandomly(){
    int dx = random.nextInt(3)-1; // -1,0,1
    int dy = random.nextInt(3)-1; // -1,0,1
    if (isOK(this.x+dx, this.y+dy,this.country)) {
      this.moveTo(this.x+dx, this.y+dy);
    }
  }

	/**
	  update their infection status.
		They move from normal to infected
		or from infected to recovered.
	*/
  void checkForInfection(){
    if (this.exposed && ! this.infected) {
			this.infected = true;
			this.infectionTime = this.age;
		}
		this.age++;
		if (infected && !this.recovered && (this.age - this.infectionTime > this.recoveryTime)) {
			this.recovered = true;
		}
  }


  /**
	  this is called if someone is near an infected person
		a random number is generated to see if they have become
		exposed to the virus. In the next step they will become
		infected if they were exposed.
	*/
	void infect(Person p) {
		// possibly become infected if you are near someone infected
		if (Math.random()<=this.infectionProb) {
			if (!this.infected) {
				this.exposed = true;
				//System.out.printf("infected! %3d %3d %3d by %3d %3d %3d%n",
				//		this.id,this.x,this.y,p.id,p.x,p.y);
			}
		}
	}

	/**
	this is called by an infected person and it
	visits all neighbors and infects them
	*/
	void infectNeighbors() {

		if (this.infected && (this.age -this.infectionTime < this.recoveryTime)) {
			for(int i=this.x-1; i<=this.x+1; i++) {
				for(int j=this.y-1; j<=this.y+1; j++) {
					if (inRange(i,j,country)) {
						Person p = country.places[i][j];
						if (p != null  && this.infected) {
							p.infect(this);
						}
					}
				}
			}
		}
	}

	public boolean inRange(int i, int j, Country country){
		return
		    (i>=0 && i<country.places.length
		 &&  j>=0 && j<country.places[i].length );
	}

	/**
	  this moves the person from one place to another
	*/
	void moveTo(int a, int b) {
		this.country.places[this.x][this.y]=null;
		if (this.country.places[a][b]!=null){
			throw new RuntimeException("a Person can't move to an occupied position.");
		}
		this.country.places[a][b] = this;
		this.x = a;
		this.y = b;
	}

	/**
	this tests to see if the position (a,b) is on the grid
	for the country and if it has the value null, so that
	a Person could move there.
	*/
	boolean isOK(int a, int b,Country country) {
		if (a<0 || a>=country.places.length || b<0
				|| b>= country.places[0].length) {
			return false;
		}else if (country.places[a][b] !=null) {
			return false;
		}else {
			return true;
		}

	}

}
