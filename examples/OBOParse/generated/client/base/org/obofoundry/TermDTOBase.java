package org.obofoundry;

public abstract class TermDTOBase extends org.obofoundry.ComponentDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.Term";
  private static final long serialVersionUID = 1229530475230L;
  
  protected TermDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TermDTOBase(com.terraframe.mojo.transport.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllHas_participant()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllHas_participant(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO> getAllHas_participantRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO> getAllHas_participantRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_has_participantDTO addHas_participant(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_has_participantDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_participantDTO addHas_participant(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_has_participantDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public void removeHas_participant(org.obofoundry.relationship.OBO_REL_has_participantDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHas_participant(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_has_participantDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHas_participant()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public static void removeAllHas_participant(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllImproper_part_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllImproper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO> getAllImproper_part_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO> getAllImproper_part_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_improper_part_ofDTO addImproper_part_of(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_improper_part_ofDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_improper_part_ofDTO addImproper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_improper_part_ofDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public void removeImproper_part_of(org.obofoundry.relationship.OBO_REL_improper_part_ofDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeImproper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllImproper_part_of()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public static void removeAllImproper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllAgent_in()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllAgent_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO> getAllAgent_inRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO> getAllAgent_inRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_agent_inDTO addAgent_in(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_agent_inDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_agent_inDTO addAgent_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_agent_inDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public void removeAgent_in(org.obofoundry.relationship.OBO_REL_agent_inDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeAgent_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_agent_inDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllAgent_in()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public static void removeAllAgent_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_adjacent_to()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO> getAllChildOBO_REL_adjacent_toRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO> getAllChildOBO_REL_adjacent_toRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_adjacent_toDTO addChildOBO_REL_adjacent_to(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_adjacent_toDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_adjacent_toDTO addChildOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_adjacent_toDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public void removeChildOBO_REL_adjacent_to(org.obofoundry.relationship.OBO_REL_adjacent_toDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_adjacent_toDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildOBO_REL_adjacent_to()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public static void removeAllChildOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermSynonymDTO> getAllSynonyms()
  {
    return (java.util.List<? extends org.obofoundry.TermSynonymDTO>) getRequest().getChildren(this.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermSynonymDTO> getAllSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermSynonymDTO>) clientRequestIF.getChildren(id, org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.HasSynonymDTO> getAllSynonymsRelationships()
  {
    return (java.util.List<? extends org.obofoundry.HasSynonymDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.HasSynonymDTO> getAllSynonymsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.HasSynonymDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public org.obofoundry.HasSynonymDTO addSynonyms(org.obofoundry.TermSynonymDTO child)
  {
    return (org.obofoundry.HasSynonymDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public static org.obofoundry.HasSynonymDTO addSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermSynonymDTO child)
  {
    return (org.obofoundry.HasSynonymDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public void removeSynonyms(org.obofoundry.HasSynonymDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.HasSynonymDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllSynonyms()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public static void removeAllSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllHas_part()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO> getAllHas_partRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO> getAllHas_partRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_has_partDTO addHas_part(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_has_partDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_partDTO addHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_has_partDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public void removeHas_part(org.obofoundry.relationship.OBO_REL_has_partDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_has_partDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHas_part()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public static void removeAllHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllContains()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllContains(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO> getAllContainsRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO> getAllContainsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_containsDTO addContains(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_containsDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_containsDTO addContains(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_containsDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public void removeContains(org.obofoundry.relationship.OBO_REL_containsDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeContains(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_containsDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllContains()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public static void removeAllContains(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_has_integral_part()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO> getAllChildOBO_REL_has_integral_partRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO> getAllChildOBO_REL_has_integral_partRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_has_integral_partDTO addChildOBO_REL_has_integral_part(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_has_integral_partDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_integral_partDTO addChildOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_has_integral_partDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public void removeChildOBO_REL_has_integral_part(org.obofoundry.relationship.OBO_REL_has_integral_partDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_has_integral_partDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildOBO_REL_has_integral_part()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public static void removeAllChildOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_transformation_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO> getAllChildOBO_REL_transformation_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO> getAllChildOBO_REL_transformation_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_transformation_ofDTO addChildOBO_REL_transformation_of(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_transformation_ofDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_transformation_ofDTO addChildOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_transformation_ofDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public void removeChildOBO_REL_transformation_of(org.obofoundry.relationship.OBO_REL_transformation_ofDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_transformation_ofDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildOBO_REL_transformation_of()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public static void removeAllChildOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_transformed_into()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO> getAllChildOBO_REL_transformed_intoRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO> getAllChildOBO_REL_transformed_intoRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_transformed_intoDTO addChildOBO_REL_transformed_into(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_transformed_intoDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_transformed_intoDTO addChildOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_transformed_intoDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public void removeChildOBO_REL_transformed_into(org.obofoundry.relationship.OBO_REL_transformed_intoDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_transformed_intoDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildOBO_REL_transformed_into()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public static void removeAllChildOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllLocated_in()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllLocated_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO> getAllLocated_inRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO> getAllLocated_inRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_located_inDTO addLocated_in(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_located_inDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_located_inDTO addLocated_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_located_inDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public void removeLocated_in(org.obofoundry.relationship.OBO_REL_located_inDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeLocated_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_located_inDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllLocated_in()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public static void removeAllLocated_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_instance_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO> getAllChildOBO_REL_instance_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO> getAllChildOBO_REL_instance_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_instance_ofDTO addChildOBO_REL_instance_of(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_instance_ofDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_instance_ofDTO addChildOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_instance_ofDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public void removeChildOBO_REL_instance_of(org.obofoundry.relationship.OBO_REL_instance_ofDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_instance_ofDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildOBO_REL_instance_of()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public static void removeAllChildOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_integral_part_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllChildOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO> getAllChildOBO_REL_integral_part_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO> getAllChildOBO_REL_integral_part_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_integral_part_ofDTO addChildOBO_REL_integral_part_of(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_integral_part_ofDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_integral_part_ofDTO addChildOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_integral_part_ofDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public void removeChildOBO_REL_integral_part_of(org.obofoundry.relationship.OBO_REL_integral_part_ofDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildOBO_REL_integral_part_of()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public static void removeAllChildOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllProper_part_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllProper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO> getAllProper_part_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO> getAllProper_part_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_proper_part_ofDTO addProper_part_of(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_proper_part_ofDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_proper_part_ofDTO addProper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_proper_part_ofDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public void removeProper_part_of(org.obofoundry.relationship.OBO_REL_proper_part_ofDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeProper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllProper_part_of()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public static void removeAllProper_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllDerives_from()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllDerives_from(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO> getAllDerives_fromRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO> getAllDerives_fromRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_derives_fromDTO addDerives_from(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_derives_fromDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_derives_fromDTO addDerives_from(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_derives_fromDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public void removeDerives_from(org.obofoundry.relationship.OBO_REL_derives_fromDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeDerives_from(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_derives_fromDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllDerives_from()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public static void removeAllDerives_from(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllPreceded_by()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getChildren(this.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllPreceded_by(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getChildren(id, org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO> getAllPreceded_byRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO> getAllPreceded_byRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_preceded_byDTO addPreceded_by(org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_preceded_byDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_preceded_byDTO addPreceded_by(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO child)
  {
    return (org.obofoundry.relationship.OBO_REL_preceded_byDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public void removePreceded_by(org.obofoundry.relationship.OBO_REL_preceded_byDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removePreceded_by(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_preceded_byDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllPreceded_by()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public static void removeAllPreceded_by(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllParticipates_in()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllParticipates_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO> getAllParticipates_inRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO> getAllParticipates_inRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_participantDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_has_participantDTO addParticipates_in(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_has_participantDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_participantDTO addParticipates_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_has_participantDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public void removeParticipates_in(org.obofoundry.relationship.OBO_REL_has_participantDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParticipates_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_has_participantDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParticipates_in()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  public static void removeAllParticipates_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_has_participantDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllHas_improper_part()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllHas_improper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO> getAllHas_improper_partRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO> getAllHas_improper_partRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_improper_part_ofDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_improper_part_ofDTO addHas_improper_part(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_improper_part_ofDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_improper_part_ofDTO addHas_improper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_improper_part_ofDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public void removeHas_improper_part(org.obofoundry.relationship.OBO_REL_improper_part_ofDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeHas_improper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllHas_improper_part()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  public static void removeAllHas_improper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_improper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllHas_agent()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllHas_agent(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO> getAllHas_agentRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO> getAllHas_agentRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_agent_inDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_agent_inDTO addHas_agent(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_agent_inDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_agent_inDTO addHas_agent(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_agent_inDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public void removeHas_agent(org.obofoundry.relationship.OBO_REL_agent_inDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeHas_agent(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_agent_inDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllHas_agent()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  public static void removeAllHas_agent(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_agent_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_adjacent_to()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO> getAllParentOBO_REL_adjacent_toRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO> getAllParentOBO_REL_adjacent_toRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_adjacent_toDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_adjacent_toDTO addParentOBO_REL_adjacent_to(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_adjacent_toDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_adjacent_toDTO addParentOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_adjacent_toDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public void removeParentOBO_REL_adjacent_to(org.obofoundry.relationship.OBO_REL_adjacent_toDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_adjacent_toDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentOBO_REL_adjacent_to()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  public static void removeAllParentOBO_REL_adjacent_to(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_adjacent_toDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllPart_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllPart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO> getAllPart_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO> getAllPart_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_partDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_has_partDTO addPart_of(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_has_partDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_partDTO addPart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_has_partDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public void removePart_of(org.obofoundry.relationship.OBO_REL_has_partDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_has_partDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPart_of()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  public static void removeAllPart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllContained_in()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllContained_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO> getAllContained_inRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO> getAllContained_inRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_containsDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_containsDTO addContained_in(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_containsDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_containsDTO addContained_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_containsDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public void removeContained_in(org.obofoundry.relationship.OBO_REL_containsDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeContained_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_containsDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllContained_in()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  public static void removeAllContained_in(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_containsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_has_integral_part()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO> getAllParentOBO_REL_has_integral_partRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO> getAllParentOBO_REL_has_integral_partRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_has_integral_partDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_has_integral_partDTO addParentOBO_REL_has_integral_part(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_has_integral_partDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_integral_partDTO addParentOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_has_integral_partDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public void removeParentOBO_REL_has_integral_part(org.obofoundry.relationship.OBO_REL_has_integral_partDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_has_integral_partDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentOBO_REL_has_integral_part()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  public static void removeAllParentOBO_REL_has_integral_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_has_integral_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_transformation_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO> getAllParentOBO_REL_transformation_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO> getAllParentOBO_REL_transformation_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformation_ofDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_transformation_ofDTO addParentOBO_REL_transformation_of(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_transformation_ofDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_transformation_ofDTO addParentOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_transformation_ofDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public void removeParentOBO_REL_transformation_of(org.obofoundry.relationship.OBO_REL_transformation_ofDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_transformation_ofDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentOBO_REL_transformation_of()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  public static void removeAllParentOBO_REL_transformation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_transformation_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_transformed_into()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO> getAllParentOBO_REL_transformed_intoRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO> getAllParentOBO_REL_transformed_intoRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_transformed_intoDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_transformed_intoDTO addParentOBO_REL_transformed_into(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_transformed_intoDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_transformed_intoDTO addParentOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_transformed_intoDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public void removeParentOBO_REL_transformed_into(org.obofoundry.relationship.OBO_REL_transformed_intoDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_transformed_intoDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentOBO_REL_transformed_into()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  public static void removeAllParentOBO_REL_transformed_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_transformed_intoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllLocation_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllLocation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO> getAllLocation_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO> getAllLocation_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_located_inDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_located_inDTO addLocation_of(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_located_inDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_located_inDTO addLocation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_located_inDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public void removeLocation_of(org.obofoundry.relationship.OBO_REL_located_inDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeLocation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_located_inDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllLocation_of()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  public static void removeAllLocation_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_located_inDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_instance_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO> getAllParentOBO_REL_instance_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO> getAllParentOBO_REL_instance_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_instance_ofDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_instance_ofDTO addParentOBO_REL_instance_of(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_instance_ofDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_instance_ofDTO addParentOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_instance_ofDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public void removeParentOBO_REL_instance_of(org.obofoundry.relationship.OBO_REL_instance_ofDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_instance_ofDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentOBO_REL_instance_of()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  public static void removeAllParentOBO_REL_instance_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_instance_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_integral_part_of()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllParentOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO> getAllParentOBO_REL_integral_part_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO> getAllParentOBO_REL_integral_part_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_integral_part_ofDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_integral_part_ofDTO addParentOBO_REL_integral_part_of(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_integral_part_ofDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_integral_part_ofDTO addParentOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_integral_part_ofDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public void removeParentOBO_REL_integral_part_of(org.obofoundry.relationship.OBO_REL_integral_part_ofDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentOBO_REL_integral_part_of()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  public static void removeAllParentOBO_REL_integral_part_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_integral_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllHas_proper_part()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllHas_proper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO> getAllHas_proper_partRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO> getAllHas_proper_partRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_proper_part_ofDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_proper_part_ofDTO addHas_proper_part(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_proper_part_ofDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_proper_part_ofDTO addHas_proper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_proper_part_ofDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public void removeHas_proper_part(org.obofoundry.relationship.OBO_REL_proper_part_ofDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeHas_proper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllHas_proper_part()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  public static void removeAllHas_proper_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_proper_part_ofDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllDerived_into()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllDerived_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO> getAllDerived_intoRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO> getAllDerived_intoRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_derives_fromDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_derives_fromDTO addDerived_into(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_derives_fromDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_derives_fromDTO addDerived_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_derives_fromDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public void removeDerived_into(org.obofoundry.relationship.OBO_REL_derives_fromDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeDerived_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_derives_fromDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllDerived_into()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  public static void removeAllDerived_into(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_derives_fromDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllPrecedes()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllPrecedes(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO> getAllPrecedesRelationships()
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO> getAllPrecedesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.relationship.OBO_REL_preceded_byDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public org.obofoundry.relationship.OBO_REL_preceded_byDTO addPrecedes(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_preceded_byDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public static org.obofoundry.relationship.OBO_REL_preceded_byDTO addPrecedes(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.relationship.OBO_REL_preceded_byDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public void removePrecedes(org.obofoundry.relationship.OBO_REL_preceded_byDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePrecedes(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.relationship.OBO_REL_preceded_byDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPrecedes()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public static void removeAllPrecedes(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.relationship.OBO_REL_preceded_byDTO.CLASS);
  }
  
  public static TermDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.EntityDTO dto = (com.terraframe.mojo.transport.EntityDTO)clientRequest.get(id);
    
    return (TermDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static org.obofoundry.TermDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.Term", "lock", _declaredTypes);
    return (org.obofoundry.TermDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.TermDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.Term", "unlock", _declaredTypes);
    return (org.obofoundry.TermDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
