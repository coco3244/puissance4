package Model;

public class Token {

	private State state;
	
	public Token() {
		this.state=State.Neutral;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return state.toString();
	}
	
	
	
}
