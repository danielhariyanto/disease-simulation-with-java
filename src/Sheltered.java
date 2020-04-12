
public class Sheltered extends Person {
	private int tick = 0;
	
	public Sheltered() {
		super();
	}

	@Override
	void tryToMove() {
		//only move once every 7 days(ticks)
		if(tick % 7 == 0) {
			super.tryToMoveRandomly();
		}
		tick++;
	}

}
