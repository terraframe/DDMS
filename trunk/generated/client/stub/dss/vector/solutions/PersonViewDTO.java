package dss.vector.solutions;

import com.terraframe.mojo.util.Converter;

import dss.vector.solutions.util.DateConverter;

public class PersonViewDTO extends PersonViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1240852495972L;

  public PersonViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }


  public Converter getDateOfBirthConverter()
  {
    String label = getDateOfBirthMd().getDisplayLabel();

    return new DateConverter(label);
  }
}
