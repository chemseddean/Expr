package pobj.expr;

public class VisitorDerive implements IVisitor<Expression> {
	
	private final Var derive;

	public VisitorDerive(Var derive) {
		super();
		this.derive = derive;
	}

	@Override
	public Expression visit(Constant c) {
		return (c.getValue() == 0) ? c : new Constant();
	}

	@Override
	public Expression visit(Add e) {
		return new Add((Expression)e.getLeft().accept(this), (Expression)e.getRight().accept(this));
	}

	@Override
	public Expression visit(Mult e) {
		Expression left = new Mult((Expression)e.getLeft(), (Expression)e.getRight().accept(this));
		Expression right = new Mult((Expression)e.getLeft().accept(this), (Expression)e.getRight());
		return new Add(left, right);
	}

	@Override
	public Expression visit(Var v) {
		if (v.equals(derive))
			return new Constant(1);
		
		return new Constant();
	}

}