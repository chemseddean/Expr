package pobj.expr;

import pobj.expr.test.VisitorToString;

public interface Expression {
	int eval();

	<T> Object accept(IVisitor<T> vts);
}
