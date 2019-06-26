package com.runwaysdk.dataaccess.database;

import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

import com.runwaysdk.constants.DatabaseProperties;

public class SQLServer extends AbstractDatabase
{
  public SQLServer(DatabaseProperties databaseProperties)
  {
    super(databaseProperties);

    // The container is not providing a pooled datasource
    if (this.dataSource == null)
    {
      JtdsDataSource serverDataSource = new JtdsDataSource();
      serverDataSource.setServerName(databaseProperties.getServerName());
      serverDataSource.setPortNumber(databaseProperties.getPort());
      serverDataSource.setDatabaseName(databaseProperties.getDatabaseName());
      serverDataSource.setUser(databaseProperties.getUser());
      serverDataSource.setPassword(databaseProperties.getPassword());

      int maxDbConnections = databaseProperties.getMaxConnections() - 1;

      if (maxDbConnections < 2)
      {
        maxDbConnections = 2;
      }

      boolean pooling = databaseProperties.getConnectionPooling();
      if (pooling)
      {
        SharedPoolDataSource sharedPoolDataSource = new SharedPoolDataSource();
        sharedPoolDataSource.setConnectionPoolDataSource(serverDataSource);
        sharedPoolDataSource.setMaxActive(maxDbConnections);
        sharedPoolDataSource.setTestOnBorrow(true);
        sharedPoolDataSource.setValidationQuery("SELECT 1");
        this.dataSource = sharedPoolDataSource;
      }
      else
      {
        this.dataSource = serverDataSource;
      }
    }
  }
}
