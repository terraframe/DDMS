package dss.vector.solutions.manager.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import dss.vector.solutions.manager.Localizer;
import dss.vector.solutions.manager.properties.ManagerProperties;

public class KeystoreAction extends Action
{
  public KeystoreAction()
  {
    this.setText(Localizer.getMessage("IMPORT_KEYSTORE"));
  }

  @Override
  public void run()
  {
    FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
    fd.setText(Localizer.getMessage("SELECT_KEYSTORE"));
    fd.setFilterExtensions(new String[] { "*.jks" });

    String selected = fd.open();
    String alias = ManagerProperties.getKeystoreAlias();

    try
    {
      if (selected != null)
      {
        // Put everything after here in your function.
        KeyStore keystore = this.loadKeystore();

        if (keystore.containsAlias(alias))
        {
          keystore.deleteEntry(alias);
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(selected)))
        {
          CertificateFactory cf = CertificateFactory.getInstance("X.509");

          while (bis.available() > 0)
          {
            Certificate cert = cf.generateCertificate(bis);
            keystore.setCertificateEntry(alias, cert);
          }
        }

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ManagerProperties.getKeystoreLocation())))
        {
          keystore.store(bos, ManagerProperties.getKeystorePassword().toCharArray());
        }
      }

    }
    catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e)
    {
      throw new RuntimeException(e);
    }
  }

  private KeyStore loadKeystore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, FileNotFoundException
  {
    try (BufferedInputStream kis = new BufferedInputStream(new FileInputStream(ManagerProperties.getKeystoreLocation())))
    {
      KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
      keystore.load(kis, ManagerProperties.getKeystorePassword().toCharArray());

      return keystore;
    }
  }
}
