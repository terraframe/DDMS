package dss.vector.solutions.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Coalesce;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;

import dss.vector.solutions.PanicButton;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.ontology.InactiveByDisease;
import dss.vector.solutions.ontology.InactiveProperty;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermRelationship;
import dss.vector.solutions.ontology.TermTermDisplayLabel;
import dss.vector.solutions.report.ReportItem;

/**
 * @author chris
 * 
 *         This class generates the menus.
 */
public class MenuGenerator implements Reloadable
{
  private static final String ANCESTOR_PARENT = "ancestor_parent";

  private static final String ANCESTOR_LABEL  = "ancestor_label";

  private static final String ANCESTOR_TERMID = "ancestor_termid";

  private static final String ANCESTOR_ID     = "ancestor_id";

  private static final String SYSTEMURL_URL   = "systemurl_url";

  private static final String SYSTEMURL_ID    = "systemurl_id";

  private static final String MENUITEM_ID     = "menuitem_id";

  private Disease             disease         = null;

  private GuiMenuItem         menu            = null;

  private final SessionIF     session         = Session.getCurrentSession();

  private MenuGenerator()
  {
    super();
  }

  public MenuGenerator(Disease disease)
  {
    this();
    this.disease = disease;
  }

  /*
   * Utility class to hold nodes of the menu tree
   */
  public class GuiMenuItem implements Reloadable
  {
    private String                   id;

    private String                   name;

    private String                   label;

    private String                   url;

    private boolean                  disabled = false;

    private String                   target;

    private Map<String, GuiMenuItem> children = new TreeMap<String, GuiMenuItem>();

    public GuiMenuItem(MenuItem menuItem, boolean disabled)
    {
      this(menuItem.getTerm().getTermId(), menuItem.getTerm().getTermDisplayLabel().getValue(), menuItem.getUrl().getUrl(), disabled);
    }

    public GuiMenuItem(Term term)
    {
      this(term.getTermId(), term.getTermDisplayLabel().getValue(), null);
    }

    public GuiMenuItem(String id, String label, String url)
    {
      this(id, label, url, false);
    }

    public GuiMenuItem(String id, String label, String url, boolean disabled)
    {
      this(id, label.replace(' ', '_'), label, url, disabled);
    }

    public GuiMenuItem(String id, String name, String label, String url)
    {
      this(id, name, label, url, false);
    }

    public GuiMenuItem(String id, String name, String label, String url, boolean disabled)
    {
      this.id = id;
      this.name = name;
      this.label = label;
      this.url = url;
      this.disabled = disabled;
      this.target = null;
    }

    public Map<String, GuiMenuItem> getChildren()
    {
      return this.children;
    }

    public String getId()
    {
      return this.id;
    }

    public String getLabel()
    {
      return this.label;
    }

    public String getName()
    {
      return this.name;
    }

    public String getUrl()
    {
      return this.url;
    }

    public boolean isDisabled()
    {
      return this.disabled;
    }

    public void setDisabled(boolean disabled)
    {
      this.disabled = disabled;
    }

    public String toString()
    {
      return this.getId() + " " + this.getLabel();
    }

    public void addChild(GuiMenuItem child)
    {
      this.children.put(child.getId(), child);
    }

    public String getTarget()
    {
      return target;
    }

    public void setTarget(String target)
    {
      this.target = target;
    }
  }

  private ValueQuery getMenuQuery(Disease disease)
  {
    QueryFactory factory = new QueryFactory();

    MdEntityDAOIF allPathsMd = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS);
    String allPathsTable = allPathsMd.getTableName();
    String allPathsChildTermCol = QueryUtil.getColumnName(allPathsMd, AllPaths.CHILDTERM);
    String allPathsParentTermCol = QueryUtil.getColumnName(allPathsMd, AllPaths.PARENTTERM);

    MdEntityDAOIF menuItemMd = MdEntityDAO.getMdEntityDAO(MenuItem.CLASS);
    String menuItemTable = menuItemMd.getTableName();
    String menuItemTermCol = QueryUtil.getColumnName(menuItemMd, MenuItem.TERM);
    String menuItemUrlCol = QueryUtil.getColumnName(menuItemMd, MenuItem.URL);
    String menuItemDiseaseCol = QueryUtil.getColumnName(menuItemMd, MenuItem.DISEASE);
    String menuItemIdCol = QueryUtil.getColumnName(menuItemMd, MenuItem.ID);

    MdEntityDAOIF termMd = MdEntityDAO.getMdEntityDAO(Term.CLASS);
    String termTable = termMd.getTableName();
    String termIdCol = QueryUtil.getColumnName(termMd, Term.ID);
    String termTermIdCol = QueryUtil.getColumnName(termMd, Term.TERMID);
    String termTermDisplayLabelCol = QueryUtil.getColumnName(termMd, Term.TERMDISPLAYLABEL);

    MdEntityDAOIF relationshipMd = MdEntityDAO.getMdEntityDAO(TermRelationship.CLASS);
    String termRelationshipTable = relationshipMd.getTableName();
    String termRelationshipParentIdCol = RelationshipDAOIF.PARENT_ID_COLUMN;
    String termRelationshipChildIdCol = RelationshipDAOIF.CHILD_ID_COLUMN;

    MdEntityDAOIF systemUrlMd = MdEntityDAO.getMdEntityDAO(SystemURL.CLASS);
    String systemUrlTable = systemUrlMd.getTableName();
    String systemUrlIdCol = QueryUtil.getColumnName(systemUrlMd, SystemURL.ID);
    String systemUrlUrlCol = QueryUtil.getColumnName(systemUrlMd, SystemURL.URL);

    MdEntityDAOIF inactiveByDiseaseMd = MdEntityDAO.getMdEntityDAO(InactiveByDisease.CLASS);
    String inactiveByDiseaseTable = inactiveByDiseaseMd.getTableName();
    String inactiveByDiseaseParentIdCol = RelationshipDAOIF.PARENT_ID_COLUMN;
    String inactiveByDiseaseChildIdCol = RelationshipDAOIF.CHILD_ID_COLUMN;

    MdEntityDAOIF inactivePropertyMd = MdEntityDAO.getMdEntityDAO(InactiveProperty.CLASS);
    String inactivePropertyTable = inactivePropertyMd.getTableName();
    String inactivePropertyIdCol = QueryUtil.getColumnName(inactivePropertyMd, InactiveProperty.ID);
    String inactivePropertyDiseaseCol = QueryUtil.getColumnName(inactivePropertyMd, InactiveProperty.DISEASE);
    String inactivePropertyInactiveCol = QueryUtil.getColumnName(inactivePropertyMd, InactiveProperty.INACTIVE);

    MdEntityDAOIF termTermDisplayLabelMd = MdEntityDAO.getMdEntityDAO(TermTermDisplayLabel.CLASS);
    String termTermDisplayLabelIdCol = QueryUtil.getColumnName(termTermDisplayLabelMd, TermTermDisplayLabel.ID);
    TermQuery termQuery = new TermQuery(factory);
    Coalesce labelCoalesce = termQuery.getTermDisplayLabel().localize();
    String termTermDisplayLabelTable = labelCoalesce.getDefiningTableName();
    String termTermDisplayLabelAlias = labelCoalesce.getDefiningTableAlias();

    ValueQuery query = new ValueQuery(factory);
    SelectableSQLCharacter menuitemId = query.aSQLCharacter(MENUITEM_ID, "menuitem." + menuItemIdCol);
    SelectableSQLCharacter menuitemUrl = query.aSQLCharacter(SYSTEMURL_URL, "url." + systemUrlUrlCol);
    SelectableSQLCharacter menuitemUrlId = query.aSQLCharacter(SYSTEMURL_ID, "url." + systemUrlIdCol);
    SelectableSQLCharacter ancestorId = query.aSQLCharacter(ANCESTOR_ID, "ancestor." + termIdCol);
    SelectableSQLCharacter ancestorTermId = query.aSQLCharacter(ANCESTOR_TERMID, "ancestor." + termTermIdCol);
    SelectableSQLCharacter ancestorLabel = query.aSQLCharacter(ANCESTOR_LABEL, labelCoalesce.getSQL());
    SelectableSQLCharacter ancestorParent = query.aSQLCharacter(ANCESTOR_PARENT, "tr." + termRelationshipParentIdCol);

    query.SELECT(new Selectable[] { menuitemId, menuitemUrl, menuitemUrlId, ancestorId, ancestorTermId, ancestorLabel, ancestorParent });
    String from = allPathsTable + " ap" + "\n" + "join " + menuItemTable + " mi on ap." + allPathsChildTermCol + " = mi." + menuItemTermCol + "\n" + "join " + termTable + " ancestor on ap." + allPathsParentTermCol + " = ancestor." + termIdCol + "\n" + "join " + termTable + " menuitem on ap." + allPathsChildTermCol + " = menuitem." + termIdCol + "\n" + "join " + termRelationshipTable + " tr on tr." + termRelationshipChildIdCol + " = ancestor." + termIdCol + "\n" + "join " + systemUrlTable
        + " url on mi." + menuItemUrlCol + " = url." + systemUrlIdCol + "\n" + "join " + termTermDisplayLabelTable + " " + termTermDisplayLabelAlias + " on " + termTermDisplayLabelAlias + "." + termTermDisplayLabelIdCol + " = ancestor." + termTermDisplayLabelCol + "\n" + "where ap."
        + allPathsChildTermCol
        + " in ("
        + "\n"
        +
        // This selects all active, leaf terms underneath the given menu root
        // that are associated with menuitems for the given disease
        "select mi." + menuItemTermCol + "\n" + "from " + menuItemTable + " mi" + "\n" + "join " + termTable + " term on mi." + menuItemTermCol + " = term." + termIdCol + "\n" + "join " + systemUrlTable + " url on mi." + menuItemUrlCol + " = url." + systemUrlIdCol + "\n" + "join " + allPathsTable + " undermenuroot on undermenuroot." + allPathsChildTermCol + " = mi." + menuItemTermCol + " and undermenuroot." + allPathsParentTermCol + " = '" + disease.getMenuRoot().getId() + "'" + "\n" + "join "
        + allPathsTable + " isleaf on isleaf." + allPathsParentTermCol + " = mi." + menuItemTermCol + "\n" + "join " + inactiveByDiseaseTable + " ibd on ibd." + inactiveByDiseaseParentIdCol + " = term." + termIdCol + "\n" + "join " + inactivePropertyTable + " ip on ibd." + inactiveByDiseaseChildIdCol + " = ip." + inactivePropertyIdCol + " and ip." + inactivePropertyDiseaseCol + " = mi." + menuItemDiseaseCol + " and ip." + inactivePropertyInactiveCol + " = 0" + "\n" + "where mi."
        + menuItemDiseaseCol + " = '" + disease.getId() + "'" + "\n" + "group by mi." + menuItemTermCol + "\n" + "having count(*) = 1" + "\n" + ")" + "\n" + "and ap." + allPathsParentTermCol + " in (" + "\n" +
        // This selects all terms who are descendants of the given menu root
        "select ap." + allPathsChildTermCol + "\n" + "from " + allPathsTable + " ap" + "\n" + "where ap." + allPathsParentTermCol + " = '" + disease.getMenuRoot().getId() + "'" + "\n" + ")";
    query.FROM(from, "");
    // MdssLog.debug(query.getSQL());
    return query;
  }

  /**
   * Generate the full menu for the current disease. This includes the
   * user-configurable menus, then the disease menu, then log out, about and
   * print
   */
  @Transaction
  public void generateMenu()
  {
    this.menu = new GuiMenuItem(this.disease.getMenuRoot().getTermId(), "ROOT", null);
    if (PanicButton.isEnabled())
    {
      this.generateEmergencyMenu();
    }
    this.generateConfigurableMenu();
    this.generateDiseaseSubMenu();
    this.menu.addChild(new GuiMenuItem("ZZZZ:7000000", "Log_Out", MDSSProperties.getString("Log_Out"), "com.runwaysdk.defaults.LoginController.logout.mojo"));
    this.menu.addChild(new GuiMenuItem("ZZZZ:8000000", "About", MDSSProperties.getString("About"), "about.jsp"));
    this.menu.addChild(new GuiMenuItem("ZZZZ:9000000", "Print", "&nbsp;", "javascript:window.print()"));
    this.disableSubmenus(this.menu);
  }

  private void generateEmergencyMenu()
  {
    GuiMenuItem emergencyMenu = new GuiMenuItem("AAAA:0100000", "Emergency_Menu", MDSSProperties.getString("Emergency_Menu"), null);
    emergencyMenu.addChild(new GuiMenuItem("AAAA:0100010", "Emergency_TermTree_MenuItem", MDSSProperties.getString("Emergency_TermTree_MenuItem"), "dss.vector.solutions.ontology.TermController.viewTree.mojo"));
    emergencyMenu.addChild(new GuiMenuItem("AAAA:0100020", "Emergency_Menu_MenuItem ", MDSSProperties.getString("Emergency_Menu_MenuItem"), "dss.vector.solutions.general.MenuItemController.viewAll.mojo"));
    this.menu.addChild(emergencyMenu);
  }

  /**
   * Generate the user-configurable menus. For each MenuItem defined for the
   * current disease that references a leaf term, attempt to add it to the menu
   * structure.
   */
  private void generateConfigurableMenu()
  {
    Map<String, GuiMenuItem> guiMenuItems = new HashMap<String, GuiMenuItem>();
    Map<String, Set<String>> children = new HashMap<String, Set<String>>();
    guiMenuItems.put(this.disease.getMenuRoot().getId(), this.menu);

    ValueQuery query = this.getMenuQuery(this.disease);
    OIterator<ValueObject> i = query.getIterator();
    // MdssLog.debug(query.getSQL());
    try
    {
      for (ValueObject valueObject : i)
      {
        String ancestorId = valueObject.getValue(ANCESTOR_ID);
        String ancestorTermId = valueObject.getValue(ANCESTOR_TERMID);
        String ancestorLabel = valueObject.getValue(ANCESTOR_LABEL);
        String ancestorParent = valueObject.getValue(ANCESTOR_PARENT);
        String menuitemId = valueObject.getValue(MENUITEM_ID);
        String systemUrlUrl = valueObject.getValue(SYSTEMURL_URL);
        String systemUrlId = valueObject.getValue(SYSTEMURL_ID);

        if (!guiMenuItems.containsKey(ancestorId))
        {
          String url = null;
          if (ancestorId.equals(menuitemId))
          {
            url = systemUrlUrl;
          }

          GuiMenuItem guiMenuItem = new GuiMenuItem(ancestorTermId, ancestorLabel, url, !this.hasAccess(systemUrlId));

          if (url != null && url.startsWith(ReportItem.BASE_URL))
          {
            guiMenuItem.setTarget("_blank");
          }

          guiMenuItems.put(ancestorId, guiMenuItem);
        }

        if (!children.containsKey(ancestorId))
        {
          children.put(ancestorId, new TreeSet<String>());
        }

        children.get(ancestorId).add(ancestorParent);
      }
    }
    finally
    {
      i.close();
    }

    for (Map.Entry<String, Set<String>> e : children.entrySet())
    {
      GuiMenuItem child = guiMenuItems.get(e.getKey());
      Set<String> parentIds = e.getValue();

      for (String parentId : parentIds)
      {
        GuiMenuItem parent = guiMenuItems.get(parentId);

        if (parent != null && child != null)
        {
          parent.addChild(child);
        }
        else
        {
          // MdssLog.debug(e.getKey() + "->" + e.getValue());
        }
      }
    }
  }

  /**
   * Generate the user-configurable menus. For each MenuItem defined for the
   * current disease that references a leaf term, attempt to add it to the menu
   * structure.
   */
  private void OLDgenerateConfigurableMenu()
  {
    MenuItemQuery query = new MenuItemQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(this.disease));
    query.ORDER_BY(query.getTerm().getTermId(), SortOrder.ASC);
    OIterator<? extends MenuItem> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        MenuItem menuItem = it.next();
        if (menuItem.getTerm().isLeaf())
        {
          GuiMenuItem guiMenuItem = new GuiMenuItem(menuItem, !this.hasAccess(menuItem.getUrl().getId()));
          this.processMenuItem(menuItem.getTerm(), guiMenuItem);
        }
      }
    }
    finally
    {
      it.close();
    }
  }

  /**
   * Process a menu item term by looking at each parent of that term: - If the
   * parent is the menu root for the specified disease, then attempt to add the
   * current subtree to the existing menu structure. - If the parent is the root
   * node, then this hierarchy path was not part of the menu tree, so discard
   * it. - Otherwise, add a new gui menu item to the tree, and repeat for its
   * parents.
   * 
   * @param term
   * @param guiMenuItem
   */
  private void processMenuItem(Term term, GuiMenuItem guiMenuItem)
  {
    if (!Disease.isInactive(term, this.disease))
    {
      OIterator<? extends Term> parents = term.getAllParentTerm();
      try
      {
        if (parents.hasNext())
        {
          while (parents.hasNext())
          {
            Term parent = parents.next();
            if (parent.getId().equals(this.disease.getMenuRoot().getId()))
            {
              // This is a valid menu path, so add it to the
              // current menu structure
              this.consolidateMenu(guiMenuItem);
            }
            else
            {
              // This could still be a valid menu path. Add the
              // parent to the menu and keep going up the
              // hierarchy
              GuiMenuItem parentGuiMenuItem = new GuiMenuItem(parent);
              parentGuiMenuItem.addChild(guiMenuItem);
              this.processMenuItem(parent, parentGuiMenuItem);
            }
          }
        }
        else
        {
          // Got to top of tree without hitting menu root, so this is
          // a dead end
        }
      }
      finally
      {
        parents.close();
      }
    }
  }

  /**
   * Generate the diseases submenu. One for each configured disease, and check
   * the current disease.
   */
  private void generateDiseaseSubMenu()
  {
    int n = 6000000;
    GuiMenuItem diseaseSubMenu = new GuiMenuItem("ZZZZ:" + ( n++ ), MdBusinessDAO.getMdBusinessDAO(Disease.CLASS).getDisplayLabel(Session.getCurrentLocale()), null);
    for (Disease disease : Disease.getAllDiseases())
    {
      String label = disease.getDisplayLabel();
      if (disease.equals(this.disease))
      {
        diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:" + ( n++ ), label, "#"));
      }
      else
      {
        if (disease.getMenuRoot() != null && disease.getMenuRoot().getInactiveByDisease() != null && !disease.getMenuRoot().getInactiveByDisease().getInactive())
        {
          diseaseSubMenu.addChild(new GuiMenuItem("ZZZZ:" + ( n++ ), label, "dss.vector.solutions.PersonController.changeDisease.mojo?diseaseName=" + disease.getKey()));
        }
      }
    }
    this.menu.addChild(diseaseSubMenu);
  }

  private void disableSubmenus(GuiMenuItem menuItem)
  {
    Collection<GuiMenuItem> children = menuItem.getChildren().values();

    if (children.size() > 0)
    {
      for (GuiMenuItem child : children)
      {
        this.disableSubmenus(child);

        if (!child.isDisabled())
        {
          menuItem.setDisabled(false);
        }
      }
    }
  }

  public String getJson()
  {
    JSONArray jsonArray = new JSONArray();
    Map<String, Integer> map = new HashMap<String, Integer>();

    this.printMenu(map, jsonArray, this.menu);

    return jsonArray.toString();
  }

  private void printMenu(Map<String, Integer> map, JSONArray jsonArray, GuiMenuItem guiMenuItem)
  {
    for (GuiMenuItem child : guiMenuItem.getChildren().values())
    {
      jsonArray.put(printItem(map, child));
    }
  }

  private JSONObject printItem(Map<String, Integer> map, GuiMenuItem guiMenuItem)
  {
    String id = guiMenuItem.getId();

    if (!map.containsKey(id))
    {
      map.put(id, 0);
    }
    else
    {
      map.put(id, ( map.get(id) + 1 ));
    }

    JSONObject json = new JSONObject();
    try
    {
      json.put("text", guiMenuItem.getLabel());
      json.put("id", "_" + id + "_" + map.get(id));

      if (guiMenuItem.getTarget() != null)
      {
        json.put("target", guiMenuItem.getTarget());
      }

      if (guiMenuItem.getChildren().size() == 0)
      {
        json.put("url", guiMenuItem.getUrl());
        json.put("visibleTo", "Administrator");
        if (guiMenuItem.isDisabled())
          json.put("disabled", true);
        else
          json.put("disabled", false);

        if (guiMenuItem.getUrl().equals("#"))
          json.put("checked", true);
        else
          json.put("checked", false);
      }
      else
      {
        if (guiMenuItem.isDisabled())
          json.put("classname", "grayed");

        OrientationType orientation = LocalizationFacade.getSessionLocaleOrientation();

        JSONObject submenu = new JSONObject();
        submenu.put("id", "_" + id + "_" + map.get(id) + "_Submenu");
        if (orientation.equals(OrientationType.RTL))
        {
          JSONArray alignment = new JSONArray();
          alignment.put("tr");
          alignment.put("tl");
          submenu.put("submenualignment", alignment);
        }

        JSONArray menuItems = new JSONArray();
        for (GuiMenuItem child : guiMenuItem.getChildren().values())
        {
          menuItems.put(printItem(map, child));
        }

        submenu.put("itemdata", menuItems);
        json.put("submenu", submenu);
      }
    }
    catch (JSONException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return json;
  }

  /**
   * @param newMenu
   */
  private void consolidateMenu(GuiMenuItem newMenu)
  {
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
  private void consolidateMenu(GuiMenuItem oldMenu, GuiMenuItem newMenu)
  {
    GuiMenuItem existing = oldMenu.getChildren().get(newMenu.getId());
    if (existing == null)
    {
      oldMenu.addChild(newMenu);
    }
    else
    {
      for (GuiMenuItem child : newMenu.getChildren().values())
      {
        consolidateMenu(existing, child);
      }
    }
  }

  private boolean hasAccess(String systemUrlId)
  {
    if (this.session == null)
    {
      return false;
    }
    Map<String, String> roles = this.session.getUserRoles();

    if (roles == null)
    {
      return false;
    }
    else
    {
      if (roles.containsKey(RoleDAOIF.ADMIN_ROLE))
      {
        return true;
      }
    }

    // Get the read role of the current diesease
    SystemURL url = SystemURL.get(systemUrlId);
    RoleDAO readRole = url.getReadRoleDAO();

    if (readRole == null)
    {
      return false;
    }

    return roles.containsKey(readRole.getRoleName());
  }
}
