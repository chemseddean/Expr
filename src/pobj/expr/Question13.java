package pobj.expr;

public class Question13 {
	public static <T> T compose(IVisitor<T> f, IVisitor<Expression> g, Expression e) {
		return (T) ((Expression) e.accept(g)).accept(f);
		} 
}
