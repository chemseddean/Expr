package pobj.expr;

public class VisitorConstant implements IVisitor<Boolean> {

	@Override
	public Boolean visit(Constant c) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean visit(Add e) {
		// TODO Auto-generated method stub
		return (Boolean) ( e.getLeft().accept(this)) && (Boolean) ( e.getRight().accept(this)) ;
	}

	@Override
	public Boolean visit(Mult e) {
		// TODO Auto-generated method stub
		return (Boolean) ( e.getLeft().accept(this)) && (Boolean) ( e.getRight().accept(this)) ;
	}

	@Override
	public Boolean visit(Var v) {
		// TODO Auto-generated method stub
		return false;
	}

}
