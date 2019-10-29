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
package dss.vector.solutions.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeBooleanDTO;
import com.runwaysdk.system.metadata.MdAttributeCharacterDTO;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.system.metadata.MdAttributeDateDTO;
import com.runwaysdk.system.metadata.MdAttributeDecimalDTO;
import com.runwaysdk.system.metadata.MdAttributeDoubleDTO;
import com.runwaysdk.system.metadata.MdAttributeFloatDTO;
import com.runwaysdk.system.metadata.MdAttributeIndicatorDTO;
import com.runwaysdk.system.metadata.MdAttributeIntegerDTO;
import com.runwaysdk.system.metadata.MdAttributeLongDTO;
import com.runwaysdk.system.metadata.MdAttributeReferenceDTO;
import com.runwaysdk.system.metadata.MdBusinessDTO;

public class SelectableOptionFactory implements Reloadable
{
  private String          suffix;

  private String          type;

  private SelectableGroup group;

  public SelectableOptionFactory(SelectableGroup group, String formName)
  {
    Matcher matcher = Pattern.compile("[^A-Za-z_]").matcher(formName.toLowerCase());

    this.group = group;
    this.suffix = "_" + matcher.replaceAll("");
    this.type = group.getClassType();
  }

  public void create(MdAttributeConcreteDTO mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeBooleanDTO)
    {
      group.addOption(new SelectableBooleanOption((MdAttributeBooleanDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeIndicatorDTO)
    {
      group.addOption(new SelectableIndicatorOption((MdAttributeIndicatorDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeCharacterDTO)
    {
      group.addOption(new SelectableCharacterOption((MdAttributeCharacterDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeDateDTO)
    {
      group.addOption(new SelectableDateOption((MdAttributeDateDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeDecimalDTO)
    {
      group.addOption(new SelectableDecimalOption((MdAttributeDecimalDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeDoubleDTO)
    {
      group.addOption(new SelectableDoubleOption((MdAttributeDoubleDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeFloatDTO)
    {
      group.addOption(new SelectableFloatOption((MdAttributeFloatDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeIntegerDTO)
    {
      group.addOption(new SelectableIntegerOption((MdAttributeIntegerDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeLongDTO)
    {
      group.addOption(new SelectableLongOption((MdAttributeLongDTO) mdAttribute, suffix, type));
    }
    else if (mdAttribute instanceof MdAttributeReferenceDTO)
    {
      MdAttributeReferenceDTO mdAttributeReference = (MdAttributeReferenceDTO) mdAttribute;

      MdBusinessDTO mdBusiness = mdAttributeReference.getMdBusiness();

      if (mdBusiness.getTypeName().equals("Term"))
      {
        group.addOption(new SelectableTermOption((MdAttributeReferenceDTO) mdAttribute, suffix, type));
      }
      if (mdBusiness.getTypeName().equals("Disease"))
      {
        group.addOption(new SelectableDiseaseOption((MdAttributeReferenceDTO) mdAttribute, suffix, type));
      }
      if (mdBusiness.getPackageName().equals("dss.vector.solutions.geo.generated"))
      {
        group.addOption(new SelectableGeoOption((MdAttributeReferenceDTO) mdAttribute, suffix, type));
      }
    }
  }
}
