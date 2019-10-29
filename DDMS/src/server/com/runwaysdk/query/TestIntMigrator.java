/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.runwaysdk.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;

import dss.vector.solutions.MdssLog;

public class TestIntMigrator
{
  public static Connection connection;
  
  public static List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();
  
  public static void main(String[] args) throws SQLException
  {
    System.out.println("Starting migrating ids.");
    
    connection = Database.getConnection();
    
    migrateId("geo_entity", "id");
    migrateId("geo_entity", "entity_label");
    migrateId("geo_entity", "term");
    migrateId("geo_entity_entity_label", "id");
    
    migrateId("term_term_display_label", "id");
    
    migrateId("md_type", "id");
    migrateId("md_type", "display_label");
    
    migrateId("allpaths_geo", "parent_geo_entity");
    migrateId("allpaths_geo", "child_geo_entity");
    
    migrateId("individual_case", "id");
    migrateId("individual_case", "probable_source");
    migrateId("individual_case", "patient");
    
    migrateId("individual_instance", "id");
    migrateId("individual_instance", "lab_test");
    
    migrateId("individual_instance", "treatment");
    migrateId("individual_instance", "treatment_method");
    migrateId("individual_instance", "patient_category");
    migrateId("individual_instance", "diagnosis_type");
    
    migrateId("term", "id");
    migrateId("term", "term_display_label");
    
    migrateId("diagnosistype", "item_id");
    migrateId("diagnosistype", "set_id");
    
    migrateId("enumeration_master", "id");
    migrateId("enumeration_master", "display_label");
    
    migrateId("metadata_display_label", "id");
    
    migrateId("person", "id");
    migrateId("person", "patient_delegate");
    
    System.out.println("Id migration complete.");
  }
  
  public static void migrateId(String table, String column) throws SQLException
  {
//    System.out.println("Starting migration of [" + table + "." + column + "]");
    
    String index = "CREATE INDEX " + table + "_" + column + "_index ON " + table + " (" + column + "_int);";
    System.out.println(index);
    
//    String alterSql = "DO $$ BEGIN BEGIN\n" + 
//        "            ALTER TABLE " + table + " ADD COLUMN " + column + "_int bigint;\n" + 
//        "        EXCEPTION\n" + 
//        "            WHEN duplicate_column THEN RAISE NOTICE 'column " +column + " already exists in " + table + ".';\n" + 
//        "        END; END $$ language plpgsql";
//    System.out.println(alterSql);
//    preparedStatementList.add(connection.prepareStatement(alterSql));
//    
//    String updateSql = "UPDATE ddms." + table + " SET " + column + "_int = replace(decode(substring(" + column +  " from 0 for 33), 'base64')::text, '\\', '')::bit(32)::bigint;";
//    System.out.println(updateSql);
//    preparedStatementList.add(connection.prepareStatement(updateSql));
//    
//    Database.executeStatementBatch(preparedStatementList);
//    preparedStatementList.clear();
  }
  
  private static void runSql(String storedProcSource)
  {
    System.out.println(storedProcSource);
    
    Statement statement = null;
    try
    {
      statement = connection.createStatement();
      statement.execute(storedProcSource);
    }
    catch (SQLException e)
    {
      MdssLog.fatal(e);
      throw new DatabaseException(e);
    }
    finally
    {
      if (statement != null)
      {
        try
        {
          statement.close();
        }
        catch (SQLException e2)
        {
          MdssLog.fatal(e2);
          throw new DatabaseException(e2);
        }
      }
    }

  }
}
