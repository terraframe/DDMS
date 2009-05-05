package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

public class ElseFilter extends Filter implements Reloadable
{

  @Override
  protected void write(SLDWriter writer)
  {
    writer.writeln("<ElseFilter />");
  }

}
