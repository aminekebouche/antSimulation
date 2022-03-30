package ch.epfl.moocprog;

public class Positionable {
	
	private ToricPosition position;
	
	
	public Positionable() {
		this.position = new ToricPosition();
	}
	
	public Positionable(ToricPosition t) {
		this.position = t;
	}
	
	public ToricPosition getPosition() {
		return this.position;
	}
	
	protected final void SetPosition(ToricPosition positio) {
		this.position = positio;
	}
	
	@Override
	public String toString(){
		return position.toString();
	}

}
