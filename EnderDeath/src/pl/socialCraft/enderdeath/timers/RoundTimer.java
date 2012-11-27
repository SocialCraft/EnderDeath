package pl.socialCraft.enderdeath.timers;

import pl.socialCraft.enderdeath.Round;

public class RoundTimer implements Runnable {
	
	private Round	round;
	
	public RoundTimer(Round round) {
		this.round = round;
	}
	
	@Override
	public void run() {
		round.tick();
	}
	
}
