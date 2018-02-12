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
package dss.vector.solutions.entomology;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.RelativeValueProblem;
import dss.vector.solutions.surveillance.ChildOption;
import dss.vector.solutions.util.MDSSProperties;

public class CollectionContainer extends CollectionContainerBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 228214925;

  public CollectionContainer(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public CollectionContainer(dss.vector.solutions.entomology.PremiseTaxon parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }

  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }

  @Override
  public void apply()
  {
    /*
     * Validations are as follows: # with water, # destroyed, # treated with
     * larvacide must all be LTE # containers # with immatures, # with larvae
     * and # with pupae must all be LTE # with water If # immatures is entered,
     * it must be GTE # with larvae and GTE # with pupae # Containers must be
     * GTE 0
     */
    validateNumberWithWater();
    validateNumberDestroyed();
    validateNumberWithLarvicide();
    validateNumberImmatures();
    validateNumberLarvae();
    validateNumberPupae();

    super.apply();
  }

  @Override
  public void validateNumberWithWater()
  {
    // Number with water must be LTE number of containers
    if (this.getNumberWithWater() != null && this.getNumberContainers() != null && this.getNumberWithWater() > this.getNumberContainers())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERWITHWATER);
      p.setAttributeDisplayLabel(getNumberWithWaterMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_LTE"));
      p.setRelativeAttributeLabel(getNumberContainersMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateNumberDestroyed()
  {
    // Number destroyed must be LTE number of containers
    if (this.getNumberDestroyed() != null && this.getNumberContainers() != null && this.getNumberDestroyed() > this.getNumberContainers())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERDESTROYED);
      p.setAttributeDisplayLabel(getNumberDestroyedMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_LTE"));
      p.setRelativeAttributeLabel(getNumberContainersMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateNumberWithLarvicide()
  {
    // Number with larvicide must be LTE number of containers
    if (this.getNumberWithLarvicide() != null && this.getNumberContainers() != null && this.getNumberWithLarvicide() > this.getNumberContainers())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERWITHLARVICIDE);
      p.setAttributeDisplayLabel(getNumberWithLarvicideMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_LTE"));
      p.setRelativeAttributeLabel(getNumberContainersMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateNumberImmatures()
  {
    // Number of containers with immatures must be LTE number of containers with
    // water
    if (this.getNumberImmatures() != null && this.getNumberWithWater() != null && this.getNumberImmatures() > this.getNumberWithWater())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERIMMATURES);
      p.setAttributeDisplayLabel(getNumberImmaturesMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_LTE"));
      p.setRelativeAttributeLabel(getNumberWithWaterMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }

    // Number of containers with immatures must be GTE number of containers with
    // larvae
    if (this.getNumberImmatures() != null && this.getNumberLarvae() != null && this.getNumberImmatures() < this.getNumberLarvae())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERIMMATURES);
      p.setAttributeDisplayLabel(getNumberImmaturesMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_GTE"));
      p.setRelativeAttributeLabel(getNumberLarvaeMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }

    // Number of containers with immatures must be GTE number of containers with
    // pupae
    if (this.getNumberImmatures() != null && this.getNumberPupae() != null && this.getNumberImmatures() < this.getNumberPupae())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERIMMATURES);
      p.setAttributeDisplayLabel(getNumberImmaturesMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_GTE"));
      p.setRelativeAttributeLabel(getNumberPupaeMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateNumberLarvae()
  {
    // Number of containers with larvae must be LTE number of containers with
    // water
    if (this.getNumberLarvae() != null && this.getNumberWithWater() != null && this.getNumberLarvae() > this.getNumberWithWater())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERLARVAE);
      p.setAttributeDisplayLabel(getNumberLarvaeMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_LTE"));
      p.setRelativeAttributeLabel(getNumberWithWaterMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateNumberPupae()
  {
    // Number of containers with pupae must be LTE number of containers with
    // water
    if (this.getNumberPupae() != null && this.getNumberWithWater() != null && this.getNumberPupae() > this.getNumberWithWater())
    {
      RelativeValueProblem p = new RelativeValueProblem();
      p.setNotification(this, NUMBERPUPAE);
      p.setAttributeDisplayLabel(getNumberPupaeMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setRelation(MDSSProperties.getString("Compare_LTE"));
      p.setRelativeAttributeLabel(getNumberWithWaterMd().getDisplayLabel(Session.getCurrentLocale()));
      p.apply();

      p.throwIt();
    }
  }

  public CollectionContainerView getView()
  {
    CollectionContainerView view = new CollectionContainerView();

    view.populateView(this);

    return view;
  }
}
