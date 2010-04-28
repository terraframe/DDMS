package dss.vector.solutions.general;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class MenuItemViewQuery extends dss.vector.solutions.general.MenuItemViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1651769820;

  public MenuItemViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultMenuItemViewBuilder(queryFactory));
  }

  public MenuItemViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultMenuItemViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private MenuItemQuery menuItemQuery;
    
    public DefaultMenuItemViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      this.menuItemQuery = new MenuItemQuery(queryFactory);
    }

    protected MenuItemViewQuery getViewQuery()
    {
      return (MenuItemViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      MenuItemViewQuery q = this.getViewQuery();
      q.map(MenuItemView.DISEASE, this.menuItemQuery.getDisease());
      q.map(MenuItemView.TERMDISPLAY, this.menuItemQuery.getTerm().getDisplay());
      q.map(MenuItemView.URLDISPLAY, this.menuItemQuery.getUrl().getDisplayLabel().localize("asdf"));
      q.map(MenuItemView.MENUITEMID, this.menuItemQuery.getId());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      MenuItemViewQuery q = this.getViewQuery();

      Disease disease = DiseaseWrapper.getDisease();
      q.WHERE(this.menuItemQuery.getDisease().containsExactly(disease));
      
      q.ORDER_BY_ASC(this.menuItemQuery.getUrl().getDisplayLabel().localize());
    }

  }
}
