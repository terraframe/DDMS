<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<c:set var="page_title" value="Excel_Import_Header"  scope="request"/>

<jwr:script src="/bundles/jqueryBundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/jqueryDownloadBundle.js" useRandomParam="false"/>

<script>
    // Override the import form's action so that we can download both a spreadsheet and redirect the user to the synonyms page,
    //   depending on the server's response status code.
    // See the source for IE support: http://stackoverflow.com/questions/16086162/handle-file-download-from-ajax-post
    $(document).on("submit", "#uploadForm", function(e)
    {
        var formData = new FormData();
        formData.append("excelType", "${excelType}");
        formData.append("upfile", document.getElementById("fileInputEl").files[0]);
        
        var xhr = new XMLHttpRequest();
        xhr.open('POST', e.target.action, true);
        xhr.responseType = 'blob';
        xhr.onload = function () {
            if (
                this.status === 702 // Request completed with errors and synonyms
                || this.status == 701 // Request completed but with errors
                )
            {
                var filename = "";
                var disposition = xhr.getResponseHeader('Content-Disposition');
                if (disposition && disposition.indexOf('attachment') !== -1) {
                    var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                    var matches = filenameRegex.exec(disposition);
                    if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
                }
                var typee = xhr.getResponseHeader('Content-Type');
                var managerId = xhr.getResponseHeader('ExcelImportManagerId');
                
                $("#busySpinner").hide();
                
                var blob = new Blob([this.response], {type: typee});
                var URL = window.URL || window.webkitURL;
                var downloadUrl = URL.createObjectURL(blob);
                
                // Download the xls errors spreadsheet in a hidden iframe 
                var iframe = document.createElement('iframe');
                iframe.style.visibility = "hidden";
                if (this.status == 702)
                {
                  // Direct browser to synonyms matching
                  iframe.onload = function() { window.location="excelImportSynonyms?managerId=" + managerId + "&excelType=${excelType}" };
                }
                iframe.src = downloadUrl; 
                document.body.appendChild(iframe);
                
                setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
            }
            else if ( this.status == 200 )
            {
              var errorMsg = xhr.getResponseHeader('errorMessage');
              
              if (errorMsg == null)
              {
                window.location = "excelImportDone";
              }
              else
              {
                window.location = "excelImportDone?errorMessage=" + encodeURIComponent(errorMsg);
              }
            }
        };
        xhr.send(formData);
        
        $("#busySpinner").css('display', 'inline');

        e.preventDefault(); // Skip the form's default submit action, we're doing custom stuff here
    });
</script>

<jsp:include page="/WEB-INF/inlineError.jsp" />
<div class="fldContainer">
    <div class="fcTop">
    
    <form id="uploadForm" method="post" enctype="multipart/form-data" action="dss.vector.solutions.generator.ExcelController.surveyExcelImport.mojo" class="uploadForm">
      <mdss:localize key="xls_file" />:: <br />
      <input type="hidden" name="excelType" value="${excelType}" />
      <input id="fileInputEl" type="file" name="upfile"/> <br />
      <input class="submitButton" type="submit" value="Import" style="margin-left: 0px; top: 0px;" />
    </form>
    
    <img id="busySpinner" alt="Importing data..." src="./imgs/busy.gif" style="display: none">
    
    <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    </div>
