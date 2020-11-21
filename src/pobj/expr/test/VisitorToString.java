package pobj.expr.test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.IVisitor;
import pobj.expr.Mult;
import pobj.expr.Var;

public class VisitorToString<T> implements IVisitor<T> {

	@Override
	public T visit(Constant c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Add e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Mult e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Var v) {
		// TODO Auto-generated method stub
		return null;
	}

}
