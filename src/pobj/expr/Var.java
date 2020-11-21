package pobj.expr;

public class Var implements Expression{
	private final String nom;

	public Var(String nom) {
		this.nom = nom; 
	}
	public String getName() {
		return nom;
	} 
	
	public boolean equals(Object o) {
		if (o instanceof Var) {
			String ONom = ((Var) o).getName(); 
			return (this.nom == ONom); 
		} else {
			return false;
		}
		
	}
	
	public String toString() {
		return nom; 
	}
	@Override
	public int eval(){
		while(true){
			throw new UnsupportedOperationException("UnsupportedOperationException"); 
		}
	}
	
	public <T> T accept(IVisitor<T> v) {
		return v.visit(this);
	}
	
}
