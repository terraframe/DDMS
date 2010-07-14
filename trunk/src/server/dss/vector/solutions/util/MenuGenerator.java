package dss.vector.solutions.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;

import dss.vector.solutions.PanicButton;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.ontology.Term;

/**
 * @author chris
 * 
 *         This class generates the menus.
 */
public class MenuGenerator implements Reloadable {
	private Disease disease = null;

	private GuiMenuItem menu = new GuiMenuItem("menu", "menu", null, false);
	
	private final SessionIF session = Session.getCurrentSession();

	private MenuGenerator() {
		super();
	}

	public MenuGenerator(Disease disease) {
		this();
		this.disease = disease;
	}

	/*
	 * Utility class to hold nodes of the menu tree
	 */
	public class GuiMenuItem implements Reloadable {
		private String id;
		private String name;
		private String label;
		private String url;
		private boolean disabled = false;

		private Map<String, GuiMenuItem> children = new TreeMap<String, GuiMenuItem>();

		public GuiMenuItem(MenuItem menuItem, boolean disabled) {
			this(menuItem.getTerm().getTermId(), menuItem.getTerm().getTermDisplayLabel().getValue(), menuItem.getUrl().getUrl(), disabled);
		}

		public GuiMenuItem(Term term) {
			this(term.getTermId(), term.getTermDisplayLabel().getValue(), null);
		}

		public GuiMenuItem(String id, String label, String url) {
			this(id, label, url, false);
		}
		
		public GuiMenuItem(String id, String label, String url, boolean disabled) {
			this(id, label.replace(' ', '_'), label, url, disabled);
		}

		public GuiMenuItem(String id, String name, String label, String url) {
			this(id, name, label, url, false);
		}
		
		public GuiMenuItem(String id, String name, String label, String url, boolean disabled) {
			this.id = id;
			this.name = name;
			this.label = label;
			this.url = url;
			this.disabled = disabled;
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
		
		public boolean isDisabled() {
			return this.disabled;
		}

		public String toString() {
			return this.getId() + " " + this.getLabel();
		}

		public void addChild(GuiMenuItem child) {
			this.children.put(child.getId(), child);
		}
	}

	/**
	 * Generate the full menu for the current disease. This includes the
	 * user-configurable menus, then the disease menu, then log out, about and
	 * print
	 */
	@Transaction
	public void generateMenu() {
		this.menu = new GuiMenuItem("menu", "menu", null);
		if (PanicButton.isEnabled()) {
			this.generateEmergencyMenu();
		}
		this.generateConfigurableMenu();
		this.generateDiseaseSubMenu();
		this.menu.addChild(new GuiMenuItem("ZZZZ:7000000", "Log_Out", MDSSProperties.getString("Log_Out"), "com.runwaysdk.defaults.LoginController.logout.mojo"));
		this.menu.addChild(new GuiMenuItem("ZZZZ:8000000", "About", MDSSProperties.getString("About"), "about.jsp"));
		this.menu.addChild(new GuiMenuItem("ZZZZ:9000000", "Print", "&nbsp;", "javascript:window.print()"));
	}

	private void generateEmergencyMenu() {
		GuiMenuItem emergencyMenu = new GuiMenuItem("AAAA:0100000", "Emergency_Menu", MDSSProperties.getString("Emergency_Menu"), null);
		emergencyMenu.addChild(new GuiMenuItem("AAAA:0100010", "Emergency_TermTree_MenuItem", MDSSProperties.getString("Emergency_TermTree_MenuItem"), "dss.vector.solutions.ontology.TermController.viewTree.mojo"));
		emergencyMenu.addChild(new GuiMenuItem("AAAA:0100020", "Emergency_Menu_MenuItem ", MDSSProperties.getString("Emergency_Menu_MenuItem"), "dss.vector.solutions.general.MenuItemController.viewAll.mojo"));
		this.menu.addChild(emergencyMenu);
	}

	/**
	 * Generate the user-configurable menus. For each MenuItem defined for the
	 * current disease that references a leaf term, attempt to add it to the
	 * menu structure.
	 */
	private void generateConfigurableMenu() {
		MenuItemQuery query = new MenuItemQuery(new QueryFactory());
		query.WHERE(query.getDisease().EQ(this.disease));
		query.ORDER_BY(query.getTerm().getTermId(), SortOrder.ASC);
		OIterator<? extends MenuItem> it = query.getIterator();

		try {
			while (it.hasNext()) {
				MenuItem menuItem = it.next();
				if (menuItem.getTerm().isLeaf()) {
					GuiMenuItem guiMenuItem = new GuiMenuItem(menuItem, !this.hasAccess(menuItem));
					this.processMenuItem(menuItem.getTerm(), guiMenuItem);
				}
			}
		} finally {
			it.close();
		}
	}

	/**
	 * Process a menu item term by looking at each parent of that term: - If the
	 * parent is the menu root for the specified disease, then attempt to add
	 * the current subtree to the existing menu structure. - If the parent is
	 * the root node, then this hierarchy path was not part of the menu tree, so
	 * discard it. - Otherwise, add a new gui menu item to the tree, and repeat
	 * for its parents.
	 * 
	 * @param term
	 * @param guiMenuItem
	 */
	private void processMenuItem(Term term, GuiMenuItem guiMenuItem) {
		if (!Disease.isInactive(term, this.disease)) {
			OIterator<? extends Term> parents = term.getAllParentTerm();
			try {
				if (parents.hasNext()) {
					while (parents.hasNext()) {
						Term parent = parents.next();
						if (parent.getId().equals(this.disease.getMenuRoot().getId())) {
							// This is a valid menu path, so add it to the
							// current menu structure
							this.consolidateMenu(guiMenuItem);
						} else {
							// This could still be a valid menu path. Add the
							// parent to the menu and keep going up the
							// hierarchy
							GuiMenuItem parentGuiMenuItem = new GuiMenuItem(parent);
							parentGuiMenuItem.addChild(guiMenuItem);
							this.processMenuItem(parent, parentGuiMenuItem);
						}
					}
				} else {
					// Got to top of tree without hitting menu root, so this is
					// a dead end
				}
			} finally {
				parents.close();
			}
		}
	}

	/**
	 * Generate the diseases submenu. One for each configured disease, and check
	 * the current disease.
	 */
	private void generateDiseaseSubMenu() {
		int n = 6000000;
		GuiMenuItem diseaseSubMenu = new GuiMenuItem("ZZZZ:" + (n++), MdBusinessDAO.getMdBusinessDAO(Disease.CLASS).getDisplayLabel(Session.getCurrentLocale()), null);
		for (Disease disease : Disease.getAllDiseases()) {
			String label = disease.getDisplayLabel();
			if (disease.equals(this.disease)) {
				diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:" + (n++), label, "#"));
			} else {
				if (disease.getMenuRoot() != null && disease.getMenuRoot().getInactiveByDisease() != null && !disease.getMenuRoot().getInactiveByDisease().getInactive()) {
					diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:" + (n++), label, "dss.vector.solutions.PersonController.changeDisease.mojo?diseaseName=" + disease.getKey()));
				}
			}
		}
		this.menu.addChild(diseaseSubMenu);
	}

	public String getJson() {
		StringWriter out = new StringWriter();
		this.printMenu(new PrintWriter(out), 0, this.menu);
		return out.toString();
	}

	private void printMenu(PrintWriter out, int level, GuiMenuItem guiMenuItem) {
		boolean first = true;
		for (GuiMenuItem child : guiMenuItem.getChildren().values()) {
			if (first) {
				first = false;
			} else {
				this.printIndented(out, level, ",");
			}
			this.printItem(out, level, child);
		}
	}

	private void printItem(PrintWriter out, int level, GuiMenuItem guiMenuItem) {
		if (guiMenuItem.getChildren().size() == 0) {
			String label = "text: '" + guiMenuItem.getLabel() + "', id: '" + guiMenuItem.getName() + "', url: '" + guiMenuItem.getUrl() + "', visibleTo:'Administrator'";
			if (guiMenuItem.isDisabled()) {
				label += ", disabled: true";
			}
			if (guiMenuItem.getUrl().equals("#")) {
				label += ", checked: true";
			}
			this.printIndented(out, level, "{ " + label + "}");
		} else {
			this.printIndented(out, level, "{ text: '" + guiMenuItem.getLabel() + "',");
			this.printIndented(out, level, "  id: '" + guiMenuItem.getId() + "',");
			this.printIndented(out, level, "  submenu: {");
			this.printIndented(out, level, "    id: '" + guiMenuItem.getName() + "_Submenu',");
			this.printIndented(out, level, "    itemdata: [");
			boolean first = true;
			for (GuiMenuItem child : guiMenuItem.getChildren().values()) {
				if (first) {
					first = false;
				} else {
					this.printIndented(out, level + 1, ",");
				}
				this.printItem(out, level + 1, child);
			}
			this.printIndented(out, level, "    ]");
			this.printIndented(out, level, "  }");
			this.printIndented(out, level, "}");
		}
	}

	private void printIndented(PrintWriter out, int level, String label) {
		for (int i = 0; i < level; i++) {
			out.print("\t");
		}
		out.println(label);
	}

	/**
	 * @param newMenu
	 */
	private void consolidateMenu(GuiMenuItem newMenu) {
		this.consolidateMenu(this.menu, newMenu);
	}

	/**
	 * Combine the new menu into the old menu by checking the top of the menu to
	 * see if it is already a child of the menu: if not, add it; if so, continue
	 * down the levels to find the appropriate place to add it
	 * 
	 * @param oldMenu
	 * @param newMenu
	 */
	private void consolidateMenu(GuiMenuItem oldMenu, GuiMenuItem newMenu) {
		GuiMenuItem existing = oldMenu.getChildren().get(newMenu.getId());
		if (existing == null) {
			oldMenu.addChild(newMenu);
		} else {
			for (GuiMenuItem child : newMenu.getChildren().values()) {
				consolidateMenu(existing, child);
			}
		}
	}
	
	private boolean hasAccess(MenuItem menuItem) {
		Map<String, String> roles = this.session.getUserRoles();
		if (roles == null) {
			return false;
		} else {
			if (roles.containsKey(RoleDAOIF.ADMIN_ROLE)) {
				return true;
			}
		}

		// Get the read role of the current diesease
		RoleDAO readRole = menuItem.getUrl().getReadRoleDAO();
		if (readRole == null) {
			return false;
		}

		return roles.containsKey(readRole.getRoleName());
	}
}
