/*
 * Created on Jun 23, 2005
 */
package com.runwaysdk.dataaccess.database;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.runwaysdk.constants.DatabaseProperties;

/**
 * Concrete implementation of <a href="http://www.mysql.com/>mysql </a> in the
 * Database class family. Contains mysql-specific implemetation of certain
 * methods.
 * 
 * @author Eric
 * @version $Revision 1.0 $
 * @since
 */
public class MySQL extends AbstractDatabase
{
  /**
   * Initialize the datasource to point to a MySQL database.
   */
  public MySQL(DatabaseProperties databaseProperties)
  {
    super(databaseProperties);

    // The container is not providing a pooled datasource
    if (this.dataSource == null)
    {
      // We subtract 1 because we'll reserve a connection for sequence numbers
      int maxDbConnections = databaseProperties.getMaxConnections() - 1;
      boolean pooling = databaseProperties.getConnectionPooling();

      if (maxDbConnections < 2)
      {
        maxDbConnections = 2;
      }

      MysqlDataSource mysqlDataSource = null;
      if (pooling)
      {
        mysqlDataSource = new MysqlConnectionPoolDataSource();
      }
      else
      {
        mysqlDataSource = new MysqlDataSource();
      }

      mysqlDataSource.setServerName(databaseProperties.getServerName());
      mysqlDataSource.setPort(databaseProperties.getPort());
      mysqlDataSource.setDatabaseName(databaseProperties.getDatabaseName());
      mysqlDataSource.setUser(databaseProperties.getUser());
      mysqlDataSource.setPassword(databaseProperties.getPassword());

      if (pooling)
      {
        SharedPoolDataSource sharedPoolDataSource = new SharedPoolDataSource();
        sharedPoolDataSource.setConnectionPoolDataSource((MysqlConnectionPoolDataSource) mysqlDataSource);
        sharedPoolDataSource.setMaxActive(maxDbConnections);
        sharedPoolDataSource.setTestOnBorrow(true);
        sharedPoolDataSource.setValidationQuery("SELECT 1");

        this.dataSource = sharedPoolDataSource;
      }
      // If environment is not configured for connection pooling, do not pool
      // connections This does not actually create connection pooling. It is
      // used by the app server.
      else
      {
        this.dataSource = mysqlDataSource;
      }
    }
  }
}
