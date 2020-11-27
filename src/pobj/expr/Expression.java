package pobj.expr;

import pobj.expr.test.VisitorToString;

public interface Expression {
	int eval();

	<T> T accept(IVisitor<T> vts);
}
