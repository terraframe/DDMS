package com.runwaysdk.query;



public class RawLeftJoinEq extends LeftJoinEq implements com.runwaysdk.generation.loader.Reloadable
{
  String sql_override;

  /**
   * Represents a left outer join between tables in the query.
   * @param attribute1   attribute name in the left side of the join
   * @param tableName1   name of the table that defines attribute1
   * @param tableAlias1  alias of the table that defines attribute1
   * @param attribute2   attribute name in the right side of the join
   * @param tableName2   name of the table that defines attribute2
   * @param tableAlias2  alias of the table that defines attribute2
   */
  public RawLeftJoinEq(String attribute1, String tableName1, String tableAlias1, String attribute2, String tableName2, String tableAlias2)
  {
    super(attribute1, tableName1, tableAlias1, attribute2, tableName2, tableAlias2);
    this.sql_override = null;
  }


  public void setSql(String sql){
    this.sql_override = sql;
  }


  @Override
  public String getSQL()
  {
    if(sql_override != null)
    {
      return " LEFT JOIN "+this.tableName2+" "+this.tableAlias2+" ON "+"("+this.sql_override+")\n";
    }

    return super.getSQL();
  }
}