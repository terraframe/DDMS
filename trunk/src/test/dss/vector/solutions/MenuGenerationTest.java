package dss.vector.solutions;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import junit.framework.TestCase;

import com.runwaysdk.dataaccess.metadata.MdEnumerationDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.StartSession;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.ontology.Term;


public class MenuGenerationTest extends TestCase {
	public class GuiMenuItem {
		private String id;
		private String label;
		private String url;
		private Map<String,GuiMenuItem> children = new TreeMap<String,GuiMenuItem>();
		public GuiMenuItem(MenuItem menuItem) {
			this(menuItem.getTerm().getTermId(), menuItem.getTerm().getDisplay(), menuItem.getUrl().getUrl());
		}
		public GuiMenuItem(Term term) {
			this(term.getTermId(), term.getDisplay(), null);
		}
		public GuiMenuItem(String id, String label, String url) {
			this.id = id;
			this.label = label;
			this.url = url;
		}
		public Map<String, GuiMenuItem> getChildren() {
			return children;
		}
		public String getId() {
			return this.id;
		}
		public String getLabel() {
			return this.label;
		}
		public String getUrl() { 
			return this.url;
		}
		public String toString() {
			return this.getId() + " " + this.getLabel();
		}
		public void addChild(GuiMenuItem child) {
			this.children.put(child.getId(), child);
		}
	}
	
	private GuiMenuItem menu = new GuiMenuItem("menu", "menu", null);
	private Term menuRoot = null;
	
	private void consolidateMenu(GuiMenuItem newMenu) {
		this.consolidateMenu(this.menu, newMenu);
	}
	
	private void consolidateMenu(GuiMenuItem oldMenu, GuiMenuItem newMenu) {
		GuiMenuItem existing = oldMenu.getChildren().get(newMenu.getId());
		if (existing == null) {
			oldMenu.addChild(newMenu);
		} else {
			for (GuiMenuItem child: newMenu.getChildren().values()) {
				this.consolidateMenu(existing, child);
			}
		}
	}
	
	@StartSession
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	@Transaction
	public void testMalariaMenu() {
		System.out.println("========== MALARIA MENU ============");
		menuRoot = Term.getByTermId("MDSS:0100000");
		this.generateMenu(Disease.MALARIA);
		this.generateDiseaseSubMenu(Disease.MALARIA);
		System.out.println(this.generateJsp(menu));
		System.out.println("====================================");
		System.out.println(this.generateJs(menu));
	}
	
	@Transaction
	public void xtestDengueMenu() {
		System.out.println("========== DENGUE MENU ============");
		menuRoot = Term.getByTermId("DDSS:0100000");
		this.generateMenu(Disease.DENGUE);
		System.out.println(this.generateJsp(menu));
		System.out.println("====================================");
		System.out.println(this.generateJs(menu));
	}

	@Transaction
	public void generateMenu(Disease disease) {
		MenuItemQuery query = new MenuItemQuery(new QueryFactory());
		query.WHERE(query.getDisease().containsExactly(disease));
		query.ORDER_BY(query.getTerm().getTermId(), SortOrder.ASC);
		OIterator<? extends MenuItem> it = query.getIterator();

		try {
			while (it.hasNext()) {
				MenuItem menuItem = it.next();
				GuiMenuItem guiMenuItem = new GuiMenuItem(menuItem);
				this.processMenuItem(menuItem.getTerm(), guiMenuItem);
			}
		} finally {
			it.close();
		}
	}
	
	@Transaction
	public void generateDiseaseSubMenu(Disease menuDisease) {
		int n = 9000000;
		GuiMenuItem diseaseSubMenu = new GuiMenuItem("ZZZZ:"+(n++), MdEnumerationDAO.getMdEnumerationDAO(Disease.CLASS).getDisplayLabel(Session.getCurrentLocale()), null);
		for (Disease disease: Disease.values()) {
			if (disease.equals(menuDisease)) {
				diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:"+(n++), disease.getDisplayLabel(), null));
			} else {
				diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:"+(n++), disease.getDisplayLabel(), null));
			}
		}
		menu.addChild(diseaseSubMenu);
	}

	private void processMenuItem(Term term, GuiMenuItem guiMenuItem) {
		OIterator<? extends Term> parents = term.getAllParentTerm();
		try {
			if (parents.hasNext()) {
				while (parents.hasNext()) {
					Term parent = parents.next();
					if (parent.getId().equals(menuRoot.getId())) {
						this.consolidateMenu(guiMenuItem);
					} else {
						GuiMenuItem parentGuiMenuItem = new GuiMenuItem(parent);
						parentGuiMenuItem.addChild(guiMenuItem);
						this.processMenuItem(parent, parentGuiMenuItem);
					}
				}
			} else {
				// Got to top of tree without hitting menu root, so this is a dead end
			}
		} finally {
			parents.close();
		}
	}
	
	private String generateJson(GuiMenuItem guiMenuItem) {
		StringWriter out = new StringWriter();
		printTopMenu(new PrintWriter(out), 0, guiMenuItem);
		return out.toString();
	}
	
	private void printTopMenu(PrintWriter out, int level, GuiMenuItem guiMenuItem) {
		boolean first = true;
		for (GuiMenuItem child : guiMenuItem.getChildren().values()) {
			if (first) {
				first = false;
			} else {
				printIndented(out, level, ",");
			}

			printIndented(out, level, "{ id: '" + child.getLabel() + "',");
			printIndented(out, level, "    itemdata: [");
			printSubMenu(out, level+1, child);
			printIndented(out, level, "    ]");
			printIndented(out, level, "}");
		}
	}
	
	private void printSubMenu(PrintWriter out, int level, GuiMenuItem guiMenuItem) {
		boolean first = true;
		for (GuiMenuItem child: guiMenuItem.getChildren().values()) {
			if (first) {
				first = false;
			} else {
				printIndented(out, level+1, ",");
			}
			printItem(out, level+1, child);
		}
	}
	
	private void printItem(PrintWriter out, int level, GuiMenuItem guiMenuItem) {
		if (guiMenuItem.getChildren().size() == 0) {
			printIndented(out, level, "{ text: '" + guiMenuItem.getLabel() + "', url: '" + guiMenuItem.getUrl() + "', visibleTo:'Administrator'}");
		} else {
			printIndented(out, level, "{ text: '" + guiMenuItem.getLabel() + "',");
			printIndented(out, level, "  submenu: {");
			printIndented(out, level, "    id: '" + guiMenuItem.getLabel() + "',");
			printIndented(out, level, "    itemdata: [");
			boolean first = true;
			for (GuiMenuItem child : guiMenuItem.getChildren().values()) {
				if (first) {
					first = false;
				} else {
					printIndented(out, level+1, ",");
				}
				printItem(out, level+1, child);
			}
			printIndented(out, level, "    ]");
			printIndented(out, level, "  }");
			printIndented(out, level, "}");
		}
	}
	
	private void printIndented(PrintWriter out, int level, String label) {
			for (int i=0; i < level; i++) {
				out.print("\t");
			}
			out.println(label);
	}
	
	private String generateJsp(GuiMenuItem guiMenuItem) {
		Writer out = new StringWriter();
		
		VelocityEngine ve = new VelocityEngine();

		ve.setProperty("directive.foreach.counter.initial.value", "0");
		ve.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute" );
		ve.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		ve.setProperty("file.resource.loader.path", "/home/chris/TerraFrame/MDSS/webapp/WEB-INF/disease");

		try {
			ve.init();
			VelocityContext context = new VelocityContext();
			context.put("menu", guiMenuItem);
			ve.mergeTemplate("navMenuTemplate.jsp", "UTF-8", context, out);
		} catch (Exception e) {
			return "Error processing template (" + e.getLocalizedMessage()  + ")";
		}

		return out.toString();
	}
	
	private String generateJs(GuiMenuItem guiMenuItem) {
		Writer out = new StringWriter();
		
		VelocityEngine ve = new VelocityEngine();

		ve.setProperty("directive.foreach.counter.initial.value", "0");
		ve.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute" );
		ve.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		ve.setProperty("file.resource.loader.path", "/home/chris/TerraFrame/MDSS/webapp/js/disease");

		try {
			ve.init();
			VelocityContext context = new VelocityContext();
			context.put("menu", guiMenuItem);
			context.put("menuJson", this.generateJson(guiMenuItem));
			ve.mergeTemplate("navMenuTemplate.js", "UTF-8", context, out);
		} catch (Exception e) {
			return "Error processing template (" + e.getLocalizedMessage()  + ")";
		}

		return out.toString();
	}

}
