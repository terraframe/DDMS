package com.runwaysdk.dataaccess.database;

import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleConnectionCacheManager;
import oracle.jdbc.pool.OracleDataSource;

import com.runwaysdk.constants.DatabaseProperties;

public class Oracle extends AbstractDatabase
{
  private final static String CACHE_NAME = "RunwaySDKOracleDBCachePool";

  /**
   * Initialize the datasource to point to a MySQL database.
   */
  public Oracle(DatabaseProperties databaseProperties)
  {
    super(databaseProperties);

    int portNumber = databaseProperties.getPort();
    String server = databaseProperties.getServerName();

    try
    {
      // If environment is not configured for connection pooling, do not pool
      // connections
      OracleDataSource oracleDataSource = new OracleDataSource();

      /* Set Host name */
      oracleDataSource.setServerName(server);
      /* Set Database SID */
      oracleDataSource.setServiceName(databaseProperties.getDatabaseName());
      /* Set Port number */
      oracleDataSource.setPortNumber(portNumber);
      /* Set Driver type */
      oracleDataSource.setDriverType("thin");
      /* Set User name */
      oracleDataSource.setUser(databaseProperties.getUser());
      /* Set Password */
      oracleDataSource.setPassword(databaseProperties.getPassword());

      this.dataSource = oracleDataSource;

      int initialDbConnections = databaseProperties.getInitialConnections();
      int maxDbConnections = databaseProperties.getMaxConnections() - 1;

      if (maxDbConnections < 2)
      {
        maxDbConnections = 2;
      }

      boolean pooling = databaseProperties.getConnectionPooling();
      if (pooling)
      {
        /* Enable cahcing */
        oracleDataSource.setConnectionCachingEnabled(true);

        /* Set the cache name */
        oracleDataSource.setConnectionCacheName(CACHE_NAME);

        /* Initialize the Connection Cache */
        OracleConnectionCacheManager connMgr = OracleConnectionCacheManager.getConnectionCacheManagerInstance();

        /*
         * This object holds the properties of the cache and is passed to the
         * ConnectionCacheManager while creating the cache. Based on these
         * properties the connection cache manager created the connection cache.
         */
        Properties properties = new Properties();

        /*
         * Set Min Limit for the Cache. This sets the minimum number of
         * PooledConnections that the cache maintains. This guarantees that the
         * cache will not shrink below this minimum limit.
         */
        properties.setProperty("MinLimit", "" + initialDbConnections);

        /*
         * Set Max Limit for the Cache. This sets the maximum number of
         * PooledConnections the cache can hold. There is no default MaxLimit
         * assumed meaning connections in the cache could reach as many as the
         * database allows.
         */
        // Rather than put threads to sleep that request a connection object
        // beyond this value, the oracle
        // Connection pool implementation seems to be throwing a null pointer
        // exception.
        // properties.setProperty("MaxLimit", "" + this.maxDbConnections);

        /*
         * Set the Initial Limit. This sets the size of the connection cache
         * when the cache is initially created or reinitialized. When this
         * property is set to a value greater than 0, that many connections are
         * pre-created and are ready for use.
         */
        properties.setProperty("InitialLimit", "" + initialDbConnections);

        /*
         * Create the cache by passing the cache name, data source and the cache
         * properties
         */
        connMgr.createCache(CACHE_NAME, oracleDataSource, properties);
      }
    }
    catch (SQLException sqlEx)
    {
      this.throwDatabaseException(sqlEx);
    }
  }
}
