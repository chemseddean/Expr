package pobj.expr;

public class Question4 {
	public static Expression e1() {
		Constant c1 = new Constant(2); 
		Constant c2 = new Constant(3); 
		Constant c3 = new Constant(4); 

		Add d = new Add(c1, c2); 
		Mult t = new Mult(d, c3);
		
		return t; 
	}
	
	public static Expression e2() {
		Constant c1 = new Constant(3); 
		Var v1 = new Var("x"); 
		Constant c2 = new Constant(4); 

		Add d = new Add(v1, c1); 
		
		Add d2 = new Add(v1, c2); 
		
		Mult t = new Mult(d, d2);
		
		return t; 
	}
	
	public static Expression e3() {
		Constant c1 = new Constant(10);
		
		Var v1 = new Var("x"); 
		Var v2 = new Var("y"); 
		
		Constant c2 = new Constant(-8); 

		Add d = new Add(v1, c1); 
		
		Add d2 = new Add(v2, c2); 
		
		Mult t = new Mult(d, d2);
		
		return t; 
	}
}
