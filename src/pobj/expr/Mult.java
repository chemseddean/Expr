package pobj.expr;

import java.util.ArrayList;
import java.util.List;

public class Mult implements Expression{
	public final Expression left; 
	public final Expression right;
	
	public Mult(Expression left, Expression right) {
		this.left = left; 
		this.right = right; 
	}
	public Expression getLeft() {
		return left; 
	}
	
	public Expression getRight() {
		return right; 
	}
	
	public String toString() {
		return left.toString()+" * "+right.toString(); 
	}
	@Override
	public int eval(){
		List<Expression> exp = new ArrayList<Expression>();
		exp.add(left); 
		exp.add(right); 
		
		if ((left instanceof Var) || (right instanceof Var)) {
			throw new UnsupportedOperationException("UnsupportedOperationException");
		} 
		
		
//		
//		Constant cst_left = new Constant(Integer.valueOf(left.toString())); 
//		
//		Constant cst_right = new Constant(Integer.valueOf(right.toString()));
//		
//		cst_left.eval(); 
//		int c1 = cst_left.getValue(); 
//		int c2 = cst_right.getValue(); 
//		
//		return c1+c2; 
//		
		int sum = 0; 
		int mult = 1;
		
		for (Expression e: exp) {
			mult*= e.eval();
		}
		
		return mult; 

	}
}
