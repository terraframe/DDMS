package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

/**
 * Superclass of all providers that assist in the collection of data for use by
 * the main queries (planned targets and spray activity).
 */
public abstract class AuxiliaryProvider extends AbstractSQLProvider implements Reloadable
{

  public AuxiliaryProvider(IRSQB irsQB)
  {
    super(irsQB);
  }

}
