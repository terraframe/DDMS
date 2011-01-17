<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="page_title" value="View_Knockdown_Assay"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.name" id="dss.vector.solutions.entomology.assay.KnockDownAssay.form.id" method="POST">
  <dl>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
          <mjl:property name="id" value="${collection.concreteId}"/>
          ${collection.collectionId}
        </mjl:commandLink>
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <span class="formatDate">${item.testDate}</span>
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        <c:if test="${testMethod != null}">
          ${testMethod.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="generation">
        <c:if test="${generation != null}">
          ${generation.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="isofemale">
        ${item.isofemale}
      </mjl:dt>
      <mjl:dt attribute="sex">
        <c:if test="${sex != null}">
          ${sex.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <c:if test="${specie != null}">
          ${specie.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        <c:if test="${identificationMethod != null}">
          ${identificationMethod.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="ageRange">
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint">
            ${item.ageRange.startPoint}
          </mjl:dt>
          <mjl:dt attribute="endPoint">
            ${item.ageRange.endPoint}
          </mjl:dt>
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="fed">
        ${item.fed}
      </mjl:dt>
      <mjl:dt attribute="gravid">
        ${item.gravid}
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        ${item.exposureTime}
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        ${item.insecticide.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        ${item.quantityTested}
      </mjl:dt>
      <mjl:dt attribute="kd50">
        ${item.kd50}
      </mjl:dt>
      <mjl:dt attribute="kd95">
        ${item.kd95}
      </mjl:dt>
      
      <div id="intervalsDiv">
        <table class="displayTable">
          <tr>
            <th><mdss:localize key="interval_time"/></th>
            <th><mdss:localize key="knock_down"/></th>
          </tr>        
          <c:if test="${item.interval10Readable}">
            <tr class="oddRow" id="interval10">
              <td>${item.interval10Md.displayLabel}</td>
              <td style="text-align:right">${item.interval10}</td>
            </tr>
          </c:if>
          <c:if test="${item.interval20Readable}">
            <tr class="oddRow" id="interval20">
              <td>${item.interval20Md.displayLabel}</td>
              <td style="text-align:right">${item.interval20}</td>
            </tr>
          </c:if>
          <c:if test="${item.interval30Readable}">
            <tr class="oddRow" id="interval30">
              <td>${item.interval30Md.displayLabel}</td>
              <td style="text-align:right">${item.interval30}</td>
            </tr>
          </c:if>
          <c:if test="${item.interval40Readable}">
            <tr class="oddRow" id="interval40">
              <td>${item.interval40Md.displayLabel}</td>
              <td style="text-align:right">${item.interval40}</td>
            </tr>
          </c:if>
          <c:if test="${item.interval50Readable}">
            <tr class="oddRow" id="interval50">
              <td>${item.interval50Md.displayLabel}</td>
              <td style="text-align:right">${item.interval50}</td>
            </tr>
          </c:if>
          <c:if test="${item.interval60Readable}">
            <tr class="oddRow" id="interval60">
              <td>${item.interval60Md.displayLabel}</td>
              <td style="text-align:right;padding-left:30px">${item.interval60}</td>
            </tr>
          </c:if>
        </table>    
      </div>  
    </mjl:component>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.edit.button" classes="submitButton" />
  </dl>
</mjl:form>

<ul>
  <li>
    <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
      <mjl:property name="id" value="${item.collection.id}"/>
      <mdss:localize key="Return_to_Collection"/>
    </mjl:commandLink>
  </li>
  <li> 
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.KnockDownAssayController.newInstance.mojo" name="newWiththisCollection">
      <mjl:property value="${item.collection.id}" name="collection_id" />
      <mdss:localize key="Create_Another_Knock_Down_Assay_With_This_Collection"/>
    </mjl:commandLink>  
  </li>
  <li>
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.KnockDownAssayController.viewAll.mojo" name="viewAll.link" >
      <mdss:localize key="View_All_KDA"/>
    </mjl:commandLink>      
  </li>
</ul>