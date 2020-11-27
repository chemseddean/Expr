package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval implements IVisitor<Integer> {
	private Map<String,Integer> map;
	
	public VisitorEvalVar(Map<String,Integer> map) {
		this.map = map; 
	}
	
	public Integer visit(Constant c) {
		return c.getValue(); 
	}
	
	
	
	public Integer visit(Add e) {
		Integer partieGauche = (Integer) e.getLeft().accept(this);
		Integer partieDroite = (Integer) e.getRight().accept(this);
		return partieGauche + partieDroite;
	}
	
	public Integer visit(Mult t) {
		Integer left = (Integer) t.getLeft().accept(this); 
		int right = (int) t.getRight().accept(this); 
		
		return left * right; 
	}

	
	public Integer visit(Var v) {
		if (!map.keySet().contains(v.getName()))
			throw new UnsupportedOperationException("UnsupportedOperationException");
		
		return map.get(v.getName());
	}
	
}
