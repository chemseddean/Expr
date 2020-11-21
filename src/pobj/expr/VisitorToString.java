package pobj.expr;

public class VisitorToString implements IVisitor {

	@Override
	public String visit(Constant c) {
		return Integer.toString(c.getValue());
	}

	@Override
	public String visit(Add e) {
		// TODO Auto-generated method stub
		String s1 = (String) e.getLeft().accept(this);
		String s2 = (String) e.getRight().accept(this);
		return "( "+ s1 + " + " + s2 + " )";
	}

	@Override
	public String visit(Mult e) {
		// TODO Auto-generated method stub
		String s1 = (String) e.getLeft().accept(this);
		String s2 = (String) e.getRight().accept(this);
		return s1 + " * " + s2;
	}

	@Override
	public String visit(Var v) {
		// TODO Auto-generated method stub
		return v.getName(); 
	}

}
