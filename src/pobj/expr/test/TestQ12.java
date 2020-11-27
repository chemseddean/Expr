package pobj.expr.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.IVisitor;
import pobj.expr.Mult;
import pobj.expr.Question4;
import pobj.expr.Question8;
import pobj.expr.Var;
import pobj.expr.VisitorDerive;
import pobj.expr.VisitorEvalVar;
import pobj.expr.VisitorSimplify;
import pobj.expr.VisitorToString;

public class TestQ12 {

class VisitorNodeCount implements IVisitor<Integer> {
	@Override
	public Integer visit(Constant c) {
		return 1;
	}

	@Override
	public Integer visit(Add e) {
		return (Integer)e.getLeft().accept(this) + (Integer)e.getRight().accept(this) + 1;
	}

	@Override
	public Integer visit(Mult e) {
		return (Integer)e.getLeft().accept(this) + (Integer)e.getRight().accept(this) + 1;
	}

	@Override
	public Integer visit(Var v) {
		return 1;
	}
}


	@Test
	public void test1() throws Exception {
		int rese, resd;
		Var x = new Var("x");
		Var y = new Var("y");
		Var z = new Var("z");

		Expression e1 = Question4.e1();
		Expression e2 = Question4.e2();
		Expression e3 = Question4.e3();

		VisitorDerive vdx = new VisitorDerive(x);
		VisitorDerive vdy = new VisitorDerive(y);
		VisitorDerive vdz = new VisitorDerive(z);

		VisitorEvalVar vev1 = new VisitorEvalVar(Question8.env2());
		VisitorToString vts1 = new VisitorToString();

		System.out.println("TRACE: expr expr_derivee ==> (eval expr, eval expr_derivee)");
		Expression ed1 = (Expression)e1.accept(vdx);
		rese = (int) e1.accept(vev1);
		resd = (int) ed1.accept(vev1);
		System.out.println(
				"TRACE: e1=" + e1.accept(vts1) + ", ed1=" + ed1.accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(0 == resd);

		/* (x^2 + 7x + 12)'_x --> 2x + 7 */
		Expression ed2 = (Expression)e2.accept(vdx);
		rese = (int) e2.accept(vev1);
		resd = (int) ed2.accept(vev1);
		assertTrue(27 == resd);

		/* (x^2 + 7x + 12)'_y --> 0 */
		Expression ed3 = (Expression)e2.accept(vdy);
		rese = (int) e2.accept(vev1);
		resd = (int) ed3.accept(vev1);
		assertTrue(0 == resd);

		/* (x^2 + 7x + 12)'_z --> 0 */
		Expression ed4 = (Expression) e2.accept(vdz);
		rese = (int) e2.accept(vev1);
		resd = (int) ed4.accept(vev1);
		assertTrue(0 == resd);

		/* (xy + 10y - 8x -80)'x --> y -8 */
		Expression ed5 = (Expression) e3.accept(vdx);
		rese = (int) e3.accept(vev1);
		resd = (int) ed5.accept(vev1);
		System.out.println(
				"TRACE: e3=" + e3.accept(vts1) + ", ed5=" + ed5.accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(12 == resd);

		/* (xy + 10y - 8x -80)'y --> x + 10 */
		Expression ed6 = (Expression) e3.accept(vdy);
		rese = (int) e3.accept(vev1);
		resd = (int) ed6.accept(vev1);
		System.out.println(
				"TRACE: e3=" + e3.accept(vts1) + ", ed6=" + ed6.accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(20 == resd);
	}

	@Test
	public void test2() throws Exception {
		int rese, resd;
		Expression dix = new Constant(10);
		Expression vingt = new Constant(20);
		Var x = new Var("x");
		Var z = new Var("z");


		VisitorDerive vdx = new VisitorDerive(x);
		VisitorDerive vdz = new VisitorDerive(z);

		Map<String, Integer> env4 = new HashMap<>();
		env4.put("x", 3);
		env4.put("z", 7);
		VisitorEvalVar vev4 = new VisitorEvalVar(env4);

		Expression e4 = new Mult(z, new Mult(z, new Mult(z, z)));
		Expression e5 = new Mult(new Add(z, dix), new Add(vingt, z));
		Expression e6 = new Mult(new Add(z, dix), new Add(vingt, x));

		/* (z^4)_z --> 4 z^3 : z = 7 --> 1372 */
		Expression ed4 = (Expression) e4.accept(vdz);
		rese = (int) e4.accept(vev4);
		resd = (int) ed4.accept(vev4);
		assertTrue(1372 == resd);

		/*
		 * (z+10) * (z + 20) = (z^2 + 30z + 200 ^4)_z) | ' --> 2z + 30 : x=3, z = 7 -->
		 * 44
		 */
		Expression ed5 = (Expression) e5.accept(vdz);
		rese = (int) e5.accept(vev4);
		resd = (int) ed5.accept(vev4);
		assertEquals(459, rese);
		assertTrue(44 == resd);

		/*
		 * (z+10) * (x + 20) = (xz + 10x + 20z + 200)_z | ' -> x + 20 : x=3, z = 7 -->
		 * 23
		 */
		Expression ed6 = (Expression) e6.accept(vdz);
		rese = (int) e6.accept(vev4);
		resd = (int) ed6.accept(vev4);
		assertTrue(23 == resd);

		/*
		 * (z+10) * (x + 20) = (xz + 10x + 20z + 200)_x | ' -> z + 10 : x=3, z = 7 -->
		 * 17
		 */
		Expression ed7 = (Expression) e6.accept(vdx);
		rese = (int) e6.accept(vev4);
		resd = (int) ed7.accept(vev4);
		assertTrue(17 == resd);

		/* (z^4)_z --> 4 z^3 --> 12 z^2 : z = 7 --> 588 */
		Expression ed8 = (Expression) ed4.accept(vdz);
		rese = (int) ed4.accept(vev4);
		resd = (int) ed8.accept(vev4);
		assertTrue(588 == resd);

		/* (z^4)_z --> 4 z^3 --> 12 z^2 --> 24z : z = 7 --> 168 */
		Expression ed9 = (Expression) ed8.accept(vdz);
		rese = (int) ed8.accept(vev4);
		resd = (int) ed9.accept(vev4);
		assertTrue(168 == resd);

		/* (z^4)_z --> 4 z^3 --> 12 z^2 --> 24z --> 24 : z = 7 --> 24 */
		Expression ed10 = (Expression) ed9.accept(vdz);
		rese = (int) ed9.accept(vev4);
		resd = (int) ed10.accept(vev4);
		assertTrue(24 == resd);

		/* (z^4)_z --> 4 z^3 --> 12 z^2 --> 24z --> 24 --> 0 : z = 7 --> 0 */
		Expression ed11 = (Expression) ed10.accept(vdz);
		rese = (int) ed10.accept(vev4);
		resd = (int) ed11.accept(vev4);
		assertTrue(0 == resd);
	}

	@Test
	public void test3() throws Exception {
		int rese, resd;
		Expression dix = new Constant(10);
		Expression vingt = new Constant(20);
		Var x = new Var("x");
		Var z = new Var("z");

		// Expression e1 = Question4.e1();;
		// Expression e2 = Question4.e2();;
		// Expression e3 = Question4.e3();;

		VisitorDerive vdx = new VisitorDerive(x);
		VisitorDerive vdz = new VisitorDerive(z);

		VisitorSimplify vs1 = new VisitorSimplify();
		Map<String, Integer> env4 = new HashMap<>();
		env4.put("x", 3);
		env4.put("z", 7);
		VisitorEvalVar vev4 = new VisitorEvalVar(env4);
		VisitorToString vts1 = new VisitorToString();

		Expression e1 = new Mult(new Add(z, dix), new Mult(new Add(z, x), new Add(vingt, z)));

		/*
		 * (z + 10) * ( (z + x) * (z + 20)) = (z + 10) * (z^2 + 20z + zx + 20x) = z^3 +
		 * (20+x)z^2 + 20zx + 10z^2 + 30z + 10zx + 200x = z^3 + (30+x)z^2 + 30zx + 30z +
		 * 200x '_z -> 4z^2 + (2x+60)z + 30x : x=3, z=7 ==> 196 + 462 + 90 = 748
		 */
		Expression ed1 = (Expression) e1.accept(vdz);
		rese = (int) e1.accept(vev4);
		resd = (int) ed1.accept(vev4);
		System.out.println("TRACE: e1=" + e1.accept(vts1) + ", ed1=" + ed1.accept(vts1) + "\n!"
				+ ((Add) ed1.accept(vs1)).accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(899 == resd);

		// (4z^2 + (2x+60)z + 30)_z --> 8z + 2x + 60 : x=3, z=7 ==> 56 + 6 + 60 = 122
		Expression ed2 = (Expression) ed1.accept(vdz);
		rese = (int) ed1.accept(vev4);
		resd = (int) ed2.accept(vev4);
		System.out.println("TRACE: ed1=" + ed1.accept(vts1) + ", ed2=" + ed2.accept(vts1) + "\n!"
				+ ((Add) ed2.accept(vs1)).accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(108 == resd);

		// (8z + 2x + 60)_z --> 8 : x=3, z=7 ==> 8
		Expression ed3 = (Expression) ed2.accept(vdz);
		rese = (int) ed2.accept(vev4);
		resd = (int) ed3.accept(vev4);
		System.out.println("TRACE: ed2=" + ed2.accept(vts1) + ", ed3=" + ed3.accept(vts1) + "\n!"
				+ ((Add) ed3.accept(vs1)).accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(6 == resd);

		// (z^3 + (30+x)z^2 + 30zx + 30z + 200x)_x --> z^2 + 30z + 200 : x= 3, z= 7 ==>
		// 459
		Expression ed4 = (Expression) e1.accept(vdx);
		rese = (int) e1.accept(vev4);
		resd = (int) ed4.accept(vev4);
		System.out.println("TRACE: e1=" + e1.accept(vts1) + ", ed4=" + ed4.accept(vts1) + "\n!"
				+ ((Add) ed4.accept(vs1)).accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(459 == resd);

		// (z^3 + (30+x)z^2 + 30zx + 30z + 200x)_x --> z^2 + 30z + 200 : x= 3, z= 7 ==>
		// 459
		Expression ed5 = (Expression) ed4.accept(vdx);
		rese = (int) ed4.accept(vev4);
		resd = (int) ed5.accept(vev4);
		System.out.println("TRACE: ed4=" + ed4.accept(vts1) + ", ed5=" + ed5.accept(vts1) + "\n!"
				+ ((Add) ed5.accept(vs1)).accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(0 == resd);

		// (z^3 + (30+x)z^2 + 30zx + 30z + 200x)_x --> z^2 + 30z + 200 : x= 3, z= 7 ==>
		// 459
		Expression ed6 = (Expression) ed5.accept(vdx);
		rese = (int) ed5.accept(vev4);
		resd = (int) ed6.accept(vev4);
		System.out.println("TRACE: ed5=" + ed5.accept(vts1) + ", ed6=" + ed6.accept(vts1) + "\n!"
				+ ((Add) ed6.accept(vs1)).accept(vts1) + " ==> (" + rese + " " + resd + ")");
		assertTrue(0 == resd);
	}

}
