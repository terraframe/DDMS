package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.QueryConstants;

public class ThematicLabelFilter extends Filter implements Reloadable
{

  @Override
  protected void write(SLDWriter writer)
  {
    writer.writeln("<PropertyIsEqualTo>");
    writer.writeln("<Function name=\"isNull\">");
    writer.writeln("<PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</PropertyName>");
    writer.writeln("</Function>");
    writer.writeln("<Literal>false</Literal>");
    writer.writeln("</PropertyIsEqualTo>");
  }

}
