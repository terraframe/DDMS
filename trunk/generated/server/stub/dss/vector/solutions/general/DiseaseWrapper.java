package dss.vector.solutions.general;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEnumerationDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableBoolean;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF;
import dss.vector.solutions.util.MDSSProperties;

public class DiseaseWrapper extends DiseaseWrapperBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = -619081050;

	public DiseaseWrapper() {
		super();
	}

	/*
	 * Utility class to hold nodes of the menu tree
	 */
	public static class GuiMenuItem implements Reloadable {
		private String id;
		private String name;
		private String label;
		private String url;
		private Map<String,GuiMenuItem> children = new TreeMap<String,GuiMenuItem>();
		public GuiMenuItem(MenuItem menuItem) {
			this(menuItem.getTerm().getTermId(), menuItem.getTerm().getDisplay(), menuItem.getUrl().getUrl());
		}
		public GuiMenuItem(Term term) {
			this(term.getTermId(), term.getDisplay(), null);
		}
		public GuiMenuItem(String id,String label, String url) {
			this(id, label.replace(' ', '_'), label, url);
		}
		public GuiMenuItem(String id, String name, String label, String url) {
			this.id = id;
			this.name = name;
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
		public String getName() {
			return this.name;
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
	
	
	/**
	 * Returns the name of the Term attribute for the inactive flag based on the
	 * current disease selected for the session user.
	 * 
	 * @return
	 */
	public static String getTermInactiveAttribute() {
		Disease disease = getDisease();

		if (disease == Disease.DENGUE) {
			return Term.INACTIVEDENGUE;
		} else if (disease == Disease.MALARIA) {
			return Term.INACTIVEMALARIA;
		} else {
			String error = "The disease [" + disease + "] is currently not supported.";
			throw new ProgrammingErrorException(error);
		}
	}

	/**
	 * Returns the correct SelectableBoolean for the active flag depending on
	 * which disease is being used by the current user.
	 * 
	 * @param termQuery
	 * @return
	 */
	public static SelectableBoolean getInactive(TermQuery termQuery) {
		Disease disease = getDisease();

		if (disease == Disease.DENGUE) {
			return termQuery.getInactiveDengue();
		} else if (disease == Disease.MALARIA) {
			return termQuery.getInactiveMalaria();
		} else {
			String error = "The disease [" + disease + "] is currently not supported.";
			throw new ProgrammingErrorException(error);
		}
	}

	/**
	 * Returns the correct SelectableBoolean for the active flag depending on
	 * which disease is being used by the current user.
	 * 
	 * @param termQuery
	 * @return
	 */
	public static SelectableBoolean getInactive(TermQueryReferenceIF termQueryRefIF) {
		Disease disease = getDisease();

		if (disease == Disease.DENGUE) {
			return termQueryRefIF.getInactiveDengue();
		} else if (disease == Disease.MALARIA) {
			return termQueryRefIF.getInactiveMalaria();
		} else {
			String error = "The disease [" + disease + "] is currently not supported.";
			throw new ProgrammingErrorException(error);
		}
	}

	/**
	 * Returns the Disease enum item of the user in the current session.
	 * 
	 * @return
	 */
	public static Disease getDisease() {
		String id = Session.getCurrentSession().getUser().getId();
		MDSSUser user = MDSSUser.get(id);

		String name = user.getDiseaseName();
		return Disease.valueOf(name);
	}

	public static String getMenuJson() {
		GuiMenuItem menu = generateMenu(getDisease());
		return generateJson(menu);
	}

	private static GuiMenuItem generateMenu(Disease disease) {
		GuiMenuItem menu = new GuiMenuItem("menu", "menu", null);
		generateConfigurableMenu(disease, menu);
		generateDiseaseSubMenu(disease, menu);
		menu.addChild(new GuiMenuItem("ZZZZ:7000000", "Log_Out", MDSSProperties.getString("Log_Out"), "com.runwaysdk.defaults.LoginController.logout.mojo"));
		menu.addChild(new GuiMenuItem("ZZZZ:8000000", "About", MDSSProperties.getString("About"), "about.jsp"));
		menu.addChild(new GuiMenuItem("ZZZZ:9000000", "Print", "&nbsp;", "javascript:window.print()"));
		return menu;
	}
	
	private static void generateConfigurableMenu(Disease disease, GuiMenuItem menu) {
		MenuItemQuery query = new MenuItemQuery(new QueryFactory());
		query.WHERE(query.getDisease().containsExactly(disease));
		query.ORDER_BY(query.getTerm().getTermId(), SortOrder.ASC);
		OIterator<? extends MenuItem> it = query.getIterator();

		try {
			while (it.hasNext()) {
				MenuItem menuItem = it.next();
				if (menuItem.getTerm().isLeaf()) {
					GuiMenuItem guiMenuItem = new GuiMenuItem(menuItem);
					processMenuItem(disease, menu, menuItem.getTerm(), guiMenuItem);
				}
			}
		} finally {
			it.close();
		}
	}
	
	private static void processMenuItem(Disease disease, GuiMenuItem menu, Term term, GuiMenuItem guiMenuItem) {
		if (!isInactive(term,disease)) {
			OIterator<? extends Term> parents = term.getAllParentTerm();
			try {
				if (parents.hasNext()) {
					while (parents.hasNext()) {
						Term parent = parents.next();
						if (parent.getId().equals(getDiseaseRoot(disease).getId())) {
							consolidateMenu(menu, guiMenuItem);
						} else {
							GuiMenuItem parentGuiMenuItem = new GuiMenuItem(parent);
							parentGuiMenuItem.addChild(guiMenuItem);
							processMenuItem(disease, menu, parent, parentGuiMenuItem);
						}
					}
				} else {
					// Got to top of tree without hitting menu root, so this is a dead end
				}
			} finally {
				parents.close();
			}
		}
	}

	private static void generateDiseaseSubMenu(Disease menuDisease, GuiMenuItem menu) {
		int n = 6000000;
		GuiMenuItem diseaseSubMenu = new GuiMenuItem("ZZZZ:"+(n++), MdEnumerationDAO.getMdEnumerationDAO(Disease.CLASS).getDisplayLabel(Session.getCurrentLocale()), null);
		for (Disease disease: Disease.values()) {
			if (disease.equals(menuDisease)) {
				diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:"+(n++), disease.getDisplayLabel(), "#"));
			} else {
				diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:"+(n++), disease.getDisplayLabel(), "dss.vector.solutions.PersonController.changeDisease.mojo?diseaseName=" + disease));
			}
		}
		menu.addChild(diseaseSubMenu);
	}

	private static String generateJson(GuiMenuItem guiMenuItem) {
		StringWriter out = new StringWriter();
		printMenu(new PrintWriter(out), 0, guiMenuItem);
		return out.toString();
	}
	
	private static void printMenu(PrintWriter out, int level, GuiMenuItem guiMenuItem) {
		boolean first = true;
		for (GuiMenuItem child: guiMenuItem.getChildren().values()) {
			if (first) {
				first = false;
			} else {
				printIndented(out, level, ",");
			}
			printItem(out, level, child);
		}
	}
	
	private static void printItem(PrintWriter out, int level, GuiMenuItem guiMenuItem) {
		if (guiMenuItem.getChildren().size() == 0) {
			String label = "text: '" + guiMenuItem.getLabel() + "', id: '" + guiMenuItem.getName() + "', url: '" + guiMenuItem.getUrl() + "', visibleTo:'Administrator'";
			if (guiMenuItem.getUrl().equals("#")) {
				label += ", checked: true";
			}
			printIndented(out, level, "{ " + label + "}");
		} else {
			printIndented(out, level, "{ text: '" + guiMenuItem.getLabel() + "',");
			printIndented(out, level, "  id: '" + guiMenuItem.getName() + "',");
			printIndented(out, level, "  submenu: {");
			printIndented(out, level, "    id: '" + guiMenuItem.getName() + "_Submenu',");
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
	
	private static void printIndented(PrintWriter out, int level, String label) {
			for (int i=0; i < level; i++) {
				out.print("\t");
			}
			out.println(label);
	}
	

	
	private static void consolidateMenu(GuiMenuItem oldMenu, GuiMenuItem newMenu) {
		GuiMenuItem existing = oldMenu.getChildren().get(newMenu.getId());
		if (existing == null) {
			oldMenu.addChild(newMenu);
		} else {
			for (GuiMenuItem child: newMenu.getChildren().values()) {
				consolidateMenu(existing, child);
			}
		}
	}
	
	// TODO - Replace this with the real code to get roots once Naifeh writes it!
	private static Term getDiseaseRoot(Disease disease) {
		if (disease.equals(Disease.MALARIA)) {
			return Term.getByTermId("MDSS:0100000");
		} else {
			return Term.getByTermId("DDSS:0100000");
		}
	}	
	
	// TODO - Replace this with the generic code to get inactive once Naifeh writes it!
	private static boolean isInactive(Term term, Disease disease) {
		if (disease.equals(Disease.MALARIA)) {
			return term.getInactiveMalaria();
		} else if (disease.equals(Disease.DENGUE)) {
			return term.getInactiveDengue();
		} else {
			return false;
		}
	}
}
