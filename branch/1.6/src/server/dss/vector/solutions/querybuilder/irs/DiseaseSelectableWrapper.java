package dss.vector.solutions.querybuilder.irs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeCondition;
import com.runwaysdk.query.ColumnInfo;
import com.runwaysdk.query.ComponentQuery;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableAggregate;
import com.runwaysdk.query.Visitor;

public class DiseaseSelectableWrapper implements Reloadable, Selectable
{
  private Selectable selectable;

  private String     alias;

  public DiseaseSelectableWrapper(Selectable selectable, String alias)
  {
    super();

    this.selectable = selectable;
    this.alias = alias;
  }

  @Override
  public Selectable clone() throws CloneNotSupportedException
  {
    return selectable.clone();
  }

  public Condition EQ(String statement)
  {
    return selectable.EQ(statement);
  }

  public Condition NE(String statement)
  {
    return selectable.NE(statement);
  }

  public AttributeCondition SUBSELECT_IN(Selectable selectable)
  {
    return selectable.SUBSELECT_IN(selectable);
  }

  public AttributeCondition SUBSELECT_NOT_IN(Selectable selectable)
  {
    return selectable.SUBSELECT_NOT_IN(selectable);
  }

  public String _getAttributeName()
  {
    return selectable._getAttributeName();
  }

  public void accept(Visitor visitor)
  {
    selectable.accept(visitor);
  }

  public SelectableAggregate getAggregateFunction()
  {
    return selectable.getAggregateFunction();
  }

  public Set<MdAttributeConcreteDAOIF> getAllEntityMdAttributes()
  {
    return selectable.getAllEntityMdAttributes();
  }

  public Attribute getAttribute()
  {
    return selectable.getAttribute();
  }

  public String getAttributeNameSpace()
  {
    return this.alias;
  }

  public String getColumnAlias()
  {
    return Alias.DISEASE.getAlias();
  }

  public Condition getCondition(String operator, String value)
  {
    return selectable.getCondition(operator, value);
  }

  public String getDbColumnName()
  {
    return Alias.DISEASE.getAlias();
  }

  public String getDbQualifiedName()
  {
    return this.getFullyQualifiedNameSpace();
  }

  public String getDefiningTableAlias()
  {
    return this.alias;
  }

  public String getDefiningTableName()
  {
    return this.alias;
  }

  public Map<String, String> getFromTableMap()
  {
    return selectable.getFromTableMap();
  }

  public String getFullyQualifiedNameSpace()
  {
    return this.alias + "." + Alias.DISEASE.getAlias();
  }

  public Set<Join> getJoinStatements()
  {
    return selectable.getJoinStatements();
  }

  public MdAttributeConcreteDAOIF getMdAttributeIF()
  {
    return selectable.getMdAttributeIF();
  }

  public String getResultAttributeName()
  {
    return selectable.getResultAttributeName();
  }

  public ComponentQuery getRootQuery()
  {
    return selectable.getRootQuery();
  }

  public String getSQL()
  {
    return selectable.getSQL();
  }

  public String getSubSelectSQL()
  {
    return selectable.getSubSelectSQL();
  }

  public String getUserDefinedAlias()
  {
    return selectable.getUserDefinedAlias();
  }

  public String getUserDefinedDisplayLabel()
  {
    return selectable.getUserDefinedDisplayLabel();
  }

  public boolean isAggregateFunction()
  {
    return selectable.isAggregateFunction();
  }

  public void setAdditionalEntityMdAttributes(List<MdAttributeConcreteDAOIF> mdAttributeConcreteDAOIFList)
  {
    selectable.setAdditionalEntityMdAttributes(mdAttributeConcreteDAOIFList);
  }

  public void setColumnAlias(String alias)
  {
    selectable.setColumnAlias(alias);
  }

  public void setUserDefinedAlias(String userDefinedAlias)
  {
    selectable.setUserDefinedAlias(userDefinedAlias);
  }

  public void setUserDefinedDisplayLabel(String userDefinedDisplayLabel)
  {
    selectable.setUserDefinedDisplayLabel(userDefinedDisplayLabel);
  }

  @Override
  public ColumnInfo getColumnInfo()
  {
    return new ColumnInfo(this.getDefiningTableName(), this.getDefiningTableAlias(), this.getDbColumnName(), this.getColumnAlias());
  }

  @Override
  public List<ColumnInfo> getColumnInfoList()
  {
    List<ColumnInfo> columnInfoList = new LinkedList<ColumnInfo>();
    columnInfoList.add(this.getColumnInfo());
    return columnInfoList;
  }

}
