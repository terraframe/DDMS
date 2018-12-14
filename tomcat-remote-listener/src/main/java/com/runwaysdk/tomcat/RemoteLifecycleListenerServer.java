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
package com.runwaysdk.tomcat;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

  protected int                                   rmiCommunicationPortPlatform;

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
    // Default port configurations
    this.rmiRegistryPortPlatform = 18234;
    this.rmiCommunicationPortPlatform = 18235;

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
   * Get the port on which the Platform RMI registry is exported.
   * 
   * @returns The port number
   */
  public int getRmiCommunicationPortPlatform()
  {
    return rmiCommunicationPortPlatform;
  }

  /**
   * Set the port on which the Platform RMI registry is exported.
   * 
   * @param theRmiRegistryPortPlatform
   *          The port number
   */
  public void setRmiCommunicationPortPlatform(int theRmiCommunicationPortPlatform)
  {
    rmiCommunicationPortPlatform = theRmiCommunicationPortPlatform;
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
    // String protocolsValue =
    // System.getProperty("javax.rmi.ssl.client.enabledProtocols");
    // String ciphersValue =
    // System.getProperty("javax.rmi.ssl.client.enabledCipherSuites");

    // Get all the other parameters required from the standard system
    // properties. Only need to get the parameters that affect the creation
    // of the server port.

    // if (protocolsValue != null)
    // {
    // protocols = protocolsValue.split(",");
    // }

    // if (ciphersValue != null)
    // {
    // ciphers = ciphersValue.split(",");
    // }

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

        // I don't know why this code is here but it doesn't work
//        csf = new SslRMIClientSocketFactory()
//        {
//          /**
//           * 
//           */
//          private static final long serialVersionUID = -7791454104764208653L;
//
//          @Override
//          public Socket createSocket(String host, int port) throws IOException
//          {
//            return super.createSocket(host, RemoteLifecycleListenerServer.this.getRmiCommunicationPortPlatform());
//          }
//        };
//
//        ssf = new SslRMIServerSocketFactory(ciphers, protocols, clientAuth)
//        {
//          @Override
//          public ServerSocket createServerSocket(int port) throws IOException
//          {
//            return super.createServerSocket(RemoteLifecycleListenerServer.this.getRmiCommunicationPortPlatform());
//          }
//        };
        
        csf = new SslRMIClientSocketFactory();
        ssf = new SslRMIServerSocketFactory(this.ciphers, this.protocols, this.clientAuth);
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
      registry.rebind(NAME, UnicastRemoteObject.exportObject(this, getRmiCommunicationPortPlatform(), csf, ssf));

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

    if (this.isExitOnShutdown())
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
  
  public String getEnvInfo() throws RemoteException
  {
    JSONObject json = new JSONObject();
    
    printMemory(json);
    
    printDisk(json);
    
    printTime(json);
    
    printThreads(json);
    
//    printSessions(json);
    
    return json.toString();
  }
  
  private void printThreads(JSONObject json) throws JSONException
  {
    try
    {
      JSONArray threadsJ = new JSONArray();
      
      Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();
      Set<Thread> threads = stacks.keySet();
      for (Thread t : threads)
      {
        JSONObject threadJ = new JSONObject();
        
        threadJ.put("name", t.getName());
        threadJ.put("state", t.getState().toString());
        
        threadsJ.put(threadJ);
      }
      
      json.put("threads", threadsJ);
    }
    catch (Throwable t)
    {
      json.put("threads", t.getMessage());
    }
  }
  
  private void printTime(JSONObject json) throws JSONException
  {
    try
    {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");  
      LocalDateTime now = LocalDateTime.now();  
      String time = dtf.format(now);
      
      json.put("time", time);
    }
    catch (Throwable t)
    {
      json.put("time", t.getMessage());
    }
  }
  
//  private void printSessions(JSONObject json) throws JSONException
//  {
//    try
//    {
//      JSONObject sesJ = new JSONObject();
//      
//      JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
//      try(JMXConnector jmxc = JMXConnectorFactory.connect(url)) {
//        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
//        ObjectName mbeanName = new ObjectName("Catalina:type=Manager,context=/,host=localhost");
//        Object value = mbsc.getAttribute(mbeanName, "activeSessions");
//      }
//      
//      json.put("session", sesJ);
//    }
//    catch (Throwable t)
//    {
//      json.put("session", t.getMessage());
//    }
//  }
  
  private void printDisk(JSONObject json) throws JSONException
  {
    try
    {
      JSONObject diskJ = new JSONObject();
      
      File root = new File("/");
      
      long total = root.getTotalSpace();
      long free = root.getFreeSpace();
      long usable = root.getUsableSpace();
      
      diskJ.put("total", total);
      diskJ.put("free", free);
      diskJ.put("usable", usable);
      
      json.put("disk", diskJ);
    }
    catch (Throwable t)
    {
      json.put("disk", t.getMessage());
    }
  }
  
  private void printMemory(JSONObject json) throws JSONException
  {
    try
    {
      JSONObject memJ = new JSONObject();
      
      long total = Runtime.getRuntime().totalMemory();
      long free = Runtime.getRuntime().freeMemory();
      
      memJ.put("total", total);
      memJ.put("free", free);
      
      json.put("memory", memJ);
    }
    catch (Throwable t)
    {
      json.put("memory", t.getMessage());
    }
  }
  
  public String getStackDump() throws RemoteException
  {
    final StringBuilder dump = new StringBuilder();
    final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    final ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), 100);
    for (ThreadInfo threadInfo : threadInfos) {
        dump.append('"');
        dump.append(threadInfo.getThreadName());
        dump.append("\" ");
        final Thread.State state = threadInfo.getThreadState();
        dump.append("\n   java.lang.Thread.State: ");
        dump.append(state);
        final StackTraceElement[] stackTraceElements = threadInfo.getStackTrace();
        for (final StackTraceElement stackTraceElement : stackTraceElements) {
            dump.append("\n        at ");
            dump.append(stackTraceElement);
        }
        dump.append("\n\n");
    }
    
    return dump.toString();
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
