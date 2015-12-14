package com.runwaysdk.tomcat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.rmi.registry.Registry;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.core.StandardService;
import org.junit.Test;

public class RemoteLifecycleListenerServerTest
{

  @Test
  public void testCreateAndDestroy() throws Exception
  {
    try
    {
      for (int i = 0; i < 1; i++)
      {
        RemoteLifecycleListenerServer server = new RemoteLifecycleListenerServer();

        Registry registry = server.createServer(4352, null, null);

        server.destroyServer(registry);

        Thread.sleep(100);
      }
    }
    catch (Exception e)
    {
      fail(e.getLocalizedMessage());
    }
  }

  @Test
  public void testAddListener() throws Exception
  {
    MockRemoteLifecycleListener listener = new MockRemoteLifecycleListener();

    RemoteLifecycleListenerServer server = new RemoteLifecycleListenerServer();
    server.addListener(listener);

    assertEquals(1, server.listeners.size());
    assertTrue(server.listeners.contains(listener));
  }

  @Test
  public void testRemoveListener() throws Exception
  {
    MockRemoteLifecycleListener listener = new MockRemoteLifecycleListener();

    RemoteLifecycleListenerServer server = new RemoteLifecycleListenerServer();
    server.addListener(listener);
    server.removeListener(listener);

    assertEquals(0, server.listeners.size());
    assertFalse(server.listeners.contains(listener));
  }

  @Test
  public void testUnsecuredCallback() throws Exception
  {
    int port = 4352;
    LifecycleEvent event = new LifecycleEvent(new StandardService(), Lifecycle.AFTER_STOP_EVENT, null);

    RemoteLifecycleListenerServer server = new RemoteLifecycleListenerServer();
    server.setUseSSL(false);
    server.setRmiRegistryPortPlatform(port);
    server.lifecycleEvent(new LifecycleEvent(new StandardService(), Lifecycle.BEFORE_START_EVENT, null));

    Thread.sleep(100);

    MockClient client = new MockClient(port);

    server.lifecycleEvent(event);

    assertEquals(event.getType(), client.getListener().getType());
  }

  @Test
  public void testSSLCallback() throws Exception
  {
    System.setProperty("javax.net.ssl.keyStore", new File("doc/ddms.ks").getAbsolutePath());
    System.setProperty("javax.net.ssl.keyStorePassword", "4b657920666fZ");
    System.setProperty("javax.net.ssl.trustStore", new File("doc/ddms.ts").getAbsolutePath());
    System.setProperty("javax.net.ssl.trustStorePassword", "1206b6579Acb3");
//    System.setProperty("javax.rmi.ssl.client.enabledCipherSuites", "SSL_RSA_WITH_RC4_128_MD5");
//    System.setProperty("javax.rmi.ssl.client.enabledProtocols", "TLSv1");

    int port = 4352;
    LifecycleEvent event = new LifecycleEvent(new StandardService(), Lifecycle.AFTER_START_EVENT, null);

    RemoteLifecycleListenerServer server = new RemoteLifecycleListenerServer();
    server.setUseSSL(true);
    server.setRmiRegistryPortPlatform(port);
    server.lifecycleEvent(new LifecycleEvent(new StandardService(), Lifecycle.BEFORE_START_EVENT, null));

    Thread.sleep(100);

    MockSecureClient client = new MockSecureClient(port);

    server.lifecycleEvent(event);

    assertEquals(event.getType(), client.getListener().getType());
  }
}
