package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.system.metadata.SupportedLocaleQueryDTO;

public class LocalizationController extends LocalizationControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/util/Localization/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 883845243;

  public LocalizationController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void selectLocales() throws IOException, ServletException
  {
    renderSelectLocales();
  }

  private void renderSelectLocales() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "selectLocales");

    SupportedLocaleQueryDTO query = LocalizationFacadeDTO.getInstalledLocales(getClientRequest());
    req.setAttribute("query", query);
    render("selectLocales.jsp");
  }

  @Override
  public void importFile(MultipartFileParameter upfile) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();
      if (upfile == null || upfile.getSize() == 0)
      {
        req.setAttribute(ErrorUtility.ERROR_MESSAGE, LocalizationFacadeDTO.getFromBundles(request, "File_Required"));
      }
      else
      {
        LocalizationFacadeDTO.importFile(request, upfile.getInputStream());
      }
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req, resp, false);
    }

    req.getRequestDispatcher("/WEB-INF/excelImportDone.jsp").forward(req, resp);
  }

  public void exportFile(String[] locales) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();

      InputStream stream = LocalizationFacadeDTO.exportFile(clientRequest, locales);

      FileDownloadUtil.writeXLS(resp, "Localization", stream);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failExportFile(locales);
      }
    }
  }

  @Override
  public void failExportFile(String[] locales) throws IOException, ServletException
  {
    this.selectLocales();
  }

  public void newLocale() throws IOException, ServletException
  {
    renderNewLocale();
  }

  private void renderNewLocale() throws IOException, ServletException
  {
    List<Locale> languages = new LinkedList<Locale>();
    List<Locale> countries = new LinkedList<Locale>();
    for (String s : Locale.getISOLanguages())
    {
      languages.add(new Locale(s));
    }

    for (String s : Locale.getISOCountries())
    {
      countries.add(new Locale("en", s));
    }

    Collections.sort(languages, new LocaleLanguageComparator());
    Collections.sort(countries, new LocaleCountryComparator());

    req.setAttribute("languages", languages);
    req.setAttribute("countries", countries);
    render("newLocale.jsp");
  }

  public void installLocale(String language, String country, String variant) throws IOException, ServletException
  {
    try
    {
      String localeString = language;
      if (country != null)
      {
        localeString += "_" + country;
        if (variant != null)
        {
          localeString += "_" + variant;
        }
      }
      LocalizationFacadeDTO.installLocale(getClientRequest(), localeString);
      renderSelectLocales();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failInstallLocale(language, country, variant);
      }
    }

  }

  @Override
  public void failInstallLocale(String language, String country, String variant) throws IOException, ServletException
  {
    renderNewLocale();
  }
}
