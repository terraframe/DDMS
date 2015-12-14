package com.runwaysdk.tomcat;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class RemoteLifecycleListenerServer implements LifecycleListener, RemoteLifecycleListenerServerIF
{
  private static Log                              log              = LogFactory.getLog(RemoteLifecycleListenerServer.class);

  /**
   * 
   */
  private static final long                       serialVersionUID = -5136898886138195505L;

  /**
   * Name of the server
   */
  public static final String                      NAME             = "RemoteLifecycleListenerServerIF";

  protected int                                   rmiRegistryPortPlatform;

  protected boolean                               useSSL;

  protected String[]                              ciphers;

  protected String[]                              protocols;

  protected boolean                               clientAuth;

  protected boolean                               useLocalPorts;

  protected Collection<RemoteLifecycleListenerIF> listeners;

  protected Registry                              registry;

  protected String                                currentState;

  /**
   * Flag indicating if the listener should call System.exit(0) on shutdown
   */
  protected boolean                               exitOnShutdown;

  public RemoteLifecycleListenerServer() throws RemoteException
  {
    this.rmiRegistryPortPlatform = -1;
    this.useSSL = true;
    this.clientAuth = true;
    this.useLocalPorts = true;
    
    this.ciphers = null;
    this.protocols = null;

    this.registry = null;
    this.listeners = new LinkedList<RemoteLifecycleListenerIF>();
    this.currentState = new String();
    this.exitOnShutdown = false;
  }

  public boolean isUseSSL()
  {
    return useSSL;
  }

  public void setUseSSL(boolean useSSL)
  {
    this.useSSL = useSSL;
  }

  /**
   * Get the port on which the Platform RMI registry is exported.
   * 
   * @returns The port number
   */
  public int getRmiRegistryPortPlatform()
  {
    return rmiRegistryPortPlatform;
  }

  /**
   * Set the port on which the Platform RMI registry is exported.
   * 
   * @param theRmiRegistryPortPlatform
   *          The port number
   */
  public void setRmiRegistryPortPlatform(int theRmiRegistryPortPlatform)
  {
    rmiRegistryPortPlatform = theRmiRegistryPortPlatform;
  }

  /**
   * Get the flag that indicates that local ports should be used for all
   * connections. If using SSH tunnels, or similar, this should be set to true
   * to ensure the RMI client uses the tunnel.
   * 
   * @returns <code>true</code> if local ports should be used
   */
  public boolean getUseLocalPorts()
  {
    return useLocalPorts;
  }

  /**
   * Set the flag that indicates that local ports should be used for all
   * connections. If using SSH tunnels, or similar, this should be set to true
   * to ensure the RMI client uses the tunnel.
   * 
   * @param useLocalPorts
   *          Set to <code>true</code> if local ports should be used
   */
  public void setUseLocalPorts(boolean useLocalPorts)
  {
    this.useLocalPorts = useLocalPorts;
  }

  public boolean isExitOnShutdown()
  {
    return exitOnShutdown;
  }

  /**
   * Set the flag that indicates that the listener will call System.exit(0) on
   * shutdown
   * 
   * @param exitOnShutdown
   *          Set to <code>true</code> if System.exit(0) should be called
   */
  public void setExitOnShutdown(boolean exitOnShutdown)
  {
    this.exitOnShutdown = exitOnShutdown;
  }

  public boolean isClientAuth()
  {
    return clientAuth;
  }

  /**
   * Set the flag that indicates that client authentication should be used for
   * all connections.
   * 
   * @param clientAuth
   *          Set to <code>true</code> if client authentication should be used
   */
  public void setClientAuth(boolean clientAuth)
  {
    this.clientAuth = clientAuth;
  }

  private void init()
  {
//    String protocolsValue = System.getProperty("javax.rmi.ssl.client.enabledProtocols");
//    String ciphersValue = System.getProperty("javax.rmi.ssl.client.enabledCipherSuites");

    // Get all the other parameters required from the standard system
    // properties. Only need to get the parameters that affect the creation
    // of the server port.

//    if (protocolsValue != null)
//    {
//      protocols = protocolsValue.split(",");
//    }

//    if (ciphersValue != null)
//    {
//      ciphers = ciphersValue.split(",");
//    }
    
    log.info("Setup RMI Socket(" + useSSL + ", " + String.valueOf(protocols) + ", " + String.valueOf(ciphers) + ", " + clientAuth + ")");
  }

  public void lifecycleEvent(LifecycleEvent event)
  {
    log.info("Lifecycle event: (" + event.getType() + ")");

    this.currentState = event.getType();

    // When the server starts, configure RMI
    if (Lifecycle.BEFORE_START_EVENT == event.getType())
    {
      // Configure using standard jmx system properties
      init();

      // Prevent an attacker guessing the RMI object ID
      System.setProperty("java.rmi.server.randomIDs", "true");

      RMIClientSocketFactory csf = null;
      RMIServerSocketFactory ssf = null;

      // Configure SSL for RMI connection if required
      if (useSSL)
      {
        log.info("Using trust/keystore(" + System.getProperty("javax.net.ssl.trustStore") + ", " + System.getProperty("javax.net.ssl.keyStore"));

        csf = new SslRMIClientSocketFactory();
        ssf = new SslRMIServerSocketFactory(ciphers, protocols, clientAuth);
      }

      // Force the use of local ports if required
      if (useLocalPorts)
      {
        csf = new RmiClientLocalhostSocketFactory(csf);
      }

      // Create the Platform server
      registry = this.createServer(rmiRegistryPortPlatform, csf, ssf);
    }
    else if (Lifecycle.AFTER_STOP_EVENT == event.getType())
    {
      this.fireLifecycleEvent(event);

      this.shutdown();
    }
    else
    {
      this.fireLifecycleEvent(event);
    }
  }

  protected Registry createServer(int theRmiRegistryPort, RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
  {

    // Create the RMI registry
    try
    {
      Registry registry = LocateRegistry.createRegistry(theRmiRegistryPort, csf, ssf);
      registry.rebind(NAME, UnicastRemoteObject.exportObject(this, 0, csf, ssf));

      return registry;
    }
    catch (RemoteException e)
    {
      log.error("Failed to bind:" + e.getLocalizedMessage(), e);

      return null;
    }
  }

  protected void destroyServer(Registry registry)
  {
    if (registry != null)
    {
      try
      {
        UnicastRemoteObject.unexportObject(registry, true);
      }
      catch (Exception e)
      {
        log.error("Failed to unbind:" + e.getLocalizedMessage(), e);
      }
    }
  }

  private void shutdown()
  {
    this.listeners.clear();

    this.destroyServer(registry);
    
    if(this.isExitOnShutdown())
    {
      System.exit(0);
    }
  }

  public void addListener(RemoteLifecycleListenerIF listener) throws RemoteException
  {
    listeners.add(listener);
  }

  public void removeListener(RemoteLifecycleListenerIF listener) throws RemoteException
  {
    listeners.remove(listener);
  }

  public String getCurrentState() throws RemoteException
  {
    return this.currentState;
  }

  public synchronized void fireLifecycleEvent(LifecycleEvent evt)
  {
    Iterator<RemoteLifecycleListenerIF> it = listeners.iterator();

    while (it.hasNext())
    {
      RemoteLifecycleListenerIF listener = it.next();

      try
      {
        listener.lifecycleEvent(evt.getType());
      }
      catch (RemoteException e)
      {
        // The remote listener is no longer available. As such remove it from
        // the collection of RemoteLifecycleListenerIF.

        it.remove();
      }
    }
  }

  public static class RmiClientLocalhostSocketFactory implements RMIClientSocketFactory, Serializable
  {
    /**
     * 
     */
    private static final long      serialVersionUID = 7285016050642343802L;

    private static final String    FORCED_HOST      = "localhost";

    private RMIClientSocketFactory factory          = null;

    public RmiClientLocalhostSocketFactory(RMIClientSocketFactory theFactory)
    {
      factory = theFactory;
    }

    public Socket createSocket(String host, int port) throws IOException
    {
      if (factory == null)
      {
        return new Socket(FORCED_HOST, port);
      }
      else
      {
        return factory.createSocket(FORCED_HOST, port);
      }
    }

  }
}
