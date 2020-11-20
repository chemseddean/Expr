package pobj.expr;

public class Constant implements Expression {
	private int entier;

	public Constant(int entier) {
		this.entier = entier; 
	}
	
	public Constant() {
		this.entier = 0; 
	}
	public int getValue() {
		return entier;
	} 
	
	public int equals() {
		return entier;
		
	}
	
	public String toString() {
		String str = Integer.toString(entier);
		return str;
	}

	@Override
	public int eval(){
		return entier; 
	}
}
