package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression> {

	@Override
	public Expression visit(Constant c) {
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public Expression visit(Add d) {
		
		if (Question10.isConstant(d.getLeft())) {
			
			if (Question10.evalConstantExpression(d.getLeft()) == 0) {
				if (Question10.isConstant(d.getRight())) {
					return new Constant(Question10.evalConstantExpression(d.getRight()));
				}
				return (Expression) d.getRight().accept(this);
			} else {
				if (Question10.isConstant(d.getRight()))
					return new Constant(Question10.evalConstantExpression(d));
			}
		} else {
			if (Question10.isConstant(d.getRight()) && Question10.evalConstantExpression(d.getRight()) == 0)
				return (Expression) d.getLeft().accept(this);
		}

		return d;
	}

	@Override
	public Expression visit(Mult t) {
		if (Question10.isConstant(t.getLeft()) && Question10.isConstant(t.getRight()))
			return new Constant(Question10.evalConstantExpression(t));
		if ((Question10.isConstant(t.getLeft()) && Question10.evalConstantExpression(t.getLeft()) == 0)
				|| (Question10.isConstant(t.getRight()) && Question10.evalConstantExpression(t.getRight()) == 0)) {
			return new Constant();
		}
		if (Question10.isConstant(t.getLeft())) {
			if (Question10.evalConstantExpression(t.getLeft()) == 1) {
				return (Expression) t.getRight().accept(this);
			}
		}
		if (Question10.isConstant(t.getRight())) {
			if (Question10.evalConstantExpression(t.getRight()) == 1) {
				return (Expression) t.getLeft().accept(this);
			}
		}
		return t;
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}

}