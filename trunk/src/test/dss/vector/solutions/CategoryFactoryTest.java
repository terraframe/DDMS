package dss.vector.solutions;

import java.util.List;

import junit.framework.TestCase;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.AbstractCategory;
import dss.vector.solutions.query.CategoryGen;
import dss.vector.solutions.query.EqualSizeCategoryFactory;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryInfo;

public class CategoryFactoryTest extends TestCase implements Reloadable {
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
/*
	public void testInterpolation() {
		ExactCategoryFactory f = new ExactCategoryFactory();
		Color start = new Color(0, 0, 0);
		Color end = new Color(255, 255, 255);
		
		Color c0 = f.interpolateColor(0, 4, start, end);
		Color c1 = f.interpolateColor(1, 4, start, end);
		Color c2 = f.interpolateColor(2, 4, start, end);
		Color c3 = f.interpolateColor(3, 4, start, end);
		Color c4 = f.interpolateColor(4, 4, start, end);
		
		assertEquals(0, c0.getRed());
		assertEquals(64, c1.getRed());
		assertEquals(128, c2.getRed());
		assertEquals(191, c3.getRed());
		assertEquals(255, c4.getRed());
	}
	
	public void testDecode() {
		
	}
	
	public void testEncode() {
		ExactCategoryFactory f = new ExactCategoryFactory();
		assertEquals("#000000", f.encodeColor(new Color(0, 0, 0)));
		assertEquals("#0000FF", f.encodeColor(new Color(0, 0, 255)));
		assertEquals("#00FFFF", f.encodeColor(new Color(0, 255, 255)));
		assertEquals("#FFFFFF", f.encodeColor(new Color(255, 255, 255)));
	}
*/	
//	public class LayerMock extends Layer {
//		private static final long serialVersionUID = 1L;
//		private String min;
//		private String max;
//		private int count;
//		boolean numeric;
//		public LayerMock(String min, String max, int count, boolean numeric) {
//			super();
//			this.min = "" + min;
//			this.max = "" + max;
//			this.count = count;
//			this.numeric = numeric;
//		}
//		
//		public LayerMock(double min, double max) {
//			this("" + min, "" + max, 0, true);
//		}
//		
//		public QueryInfo calculateQueryInfo() {
//			QueryInfo info = new QueryInfo();
//			info.setMinimum(this.min);
//			info.setMaximum(this.max);
//			info.setTotalResults(this.count);
//			info.setIsThematicVariable(this.numeric);
//			return info;
//		}
//	}
//	
//	public void testRanges() {
//		testRange02();
//		testRange12();
//		testRange22();
//		testRange32();
//		testRange33();
//		testRange31();
//		testRange51();
//		testRange91();
//		testRange30a();
//		testRange30b();
//	}
//	
//	private void testRange02() {
//		List<AbstractCategory> categories = this.testRange(0d, 10d, 0, 2);
//		assertEquals(0, categories.size());
//	}
//	
//	private void testRange12() {
//		List<AbstractCategory> categories = this.testRange(0d, 10d, 1, 2);
//		assertEquals(0, categories.size());
//	}
//	
//	private void testRange22() {
//		List<AbstractCategory> categories = this.testRange(0d, 10d, 2, 2);
//		assertEquals(2, categories.size());
//	}
//	
//	private void testRange32() {
//		List<AbstractCategory> categories = this.testRange(0d, 10d, 3, 2);
//		assertEquals(3, categories.size());
//	}
//	
//	private void testRange33() {
//		List<AbstractCategory> categories = this.testRange(1d, 11d, 3, 3);
//		assertEquals(3, categories.size());
//	}
//	
//	private void testRange31() {
//		List<AbstractCategory> categories = this.testRange(1.1d, 1.2d, 3, 1);
//		assertEquals(3, categories.size());
//	}
//	
//	private void testRange51() {
//		List<AbstractCategory> categories = this.testRange(1.1d, 1.2d, 5, 1);
//		assertEquals(3, categories.size());
//	}	
//	
//	private void testRange91() {
//		List<AbstractCategory> categories = this.testRange(1.1d, 1.2d, 9, 1);
//		assertEquals(3, categories.size());
//	}
//	
//	private void testRange30a() {
//		List<AbstractCategory> categories = this.testRange(1.1d, 1.2d, 3, 0);
//		assertEquals(3, categories.size());
//	}
//	
//	private void testRange30b() {
//		List<AbstractCategory> categories = this.testRange(0d, 10d, 3, 0);
//		assertEquals(3, categories.size());
//	}
//	
//	private List<AbstractCategory> testRange(double min, double max, int count, int precision) {
//		System.out.println("---------- " + min + "->" + max + " (" + count + " range(s)," + precision + " sigfig) ----------");
//		EqualSizeCategoryFactory f = new EqualSizeCategoryFactory();
//		Layer l = new LayerMock(min, max);
//		CategoryGen cg = new CategoryGen();
//		cg.setCategoryCount(count);
//		cg.setPrecisionFigures(precision);
//		return f.createInternal(l, cg);
//	}
}