package dss.vector.solutions.util;

import java.util.Comparator;
import java.util.Locale;

public class LocaleLanguageComparator implements Comparator<Locale>
{
  public int compare(Locale o1, Locale o2)
  {
    return o1.getDisplayLanguage().compareTo(o2.getDisplayLanguage());
  }
}
