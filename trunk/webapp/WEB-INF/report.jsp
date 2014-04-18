<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<html>
  <head>
    <title>${pageTitle}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <jwr:style src="/bundles/yuiStyle.css" useRandomParam="false"/>
      <jwr:style src="/bundles/yui3Style.css" useRandomParam="false"/>
      <jwr:style src="/bundles/mdssScreen.css" media="all" useRandomParam="false"/>
      <jwr:script src="/bundles/yuiBundle.js" useRandomParam="false"/>
      <jwr:script src="/bundles/Mojo.js" useRandomParam="false"/>
      <jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>
      <script type="text/javascript" src="js/Localized.js.jsp" ></script>
    
      <style type="text/css">
          div#navigationBar {background-color:#F0F8FF; border-bottom-style:solid; border-bottom: medium; border-bottom-color: black;}
      </style>
  </head>
  <body class="style_0" style=" margin:0px;">    
  <!-- 
    <DIV id="navigationBar">
      <TABLE CELLSPACING="0" CELLPADDING="0" WIDTH="100%" HEIGHT="25px" CLASS="birtviewer_navbar">
        <TR><TD></TD></TR>
        <TR>
          <TD WIDTH="6px">&nbsp;</TD>
          <TD WIDTH="100%" NOWRAP>
            <B>
              <mdss:localize key="prompt.one"/> &nbsp;
              <SPAN ID='pageNumber'>${pageNumber}</SPAN>&nbsp;
              <mdss:localize key="prompt.two"/>&nbsp;
              <SPAN ID='totalPage'>${pageCount}</SPAN>
            </B>
          </TD>
          <TD WIDTH="15px">
            <c:url var="myURL" value="${url}">
              <c:param name="pageNumber" value="1"/>
            </c:url>            
            <mdss:localize key="first" var="first_page"/>            
            <a href="${myURL}"><img src="first_page.gif" alt="${first_page}" title="${first_page}" /></a>
          </TD>
          <TD WIDTH="2px">&nbsp;</TD>
          <TD WIDTH="15px">
            <c:url var="myURL" value="${url}">
              <c:param name="pageNumber">${pageNumber > 1 ? (pageNumber - 1) : 1}</c:param> 
            </c:url>            
            <mdss:localize key="prev" var="prev_page"/>            
            <a href="${myURL}"><img src="prev_page.gif" alt="${prev_page}" title="${prev_page}" /></a>
          </TD>
          <TD WIDTH="2px">&nbsp;</TD>
          <TD WIDTH="15px">
            <c:url var="myURL" value="${url}">
              <c:param name="pageNumber">${pageNumber < pageCount ? (pageNumber + 1) : pageCount}</c:param> 
            </c:url>            
            <mdss:localize key="next" var="next_page"/>            
            <a href="${myURL}"><img src="next_page.gif" alt="${next_page}" title="${next_page}" /></a>
          </TD>
          <TD WIDTH="2px">&nbsp;</TD>
          <TD WIDTH="15px">
            <c:url var="myURL" value="${url}">
              <c:param name="pageNumber">${pageCount}</c:param> 
            </c:url>            
            <mdss:localize key="last" var="last_page"/>            
            <a href="${myURL}"><img src="last_page.gif" alt="${last_page}" title="${last_page}" /></a>
          </TD>
          
          <TD WIDTH="8px">&nbsp;&nbsp;</TD>
          
          <TD ALIGN="right" NOWRAP><LABEL for="gotoPage"><b><mdss:localize key="goto"/></b></LABEL></TD>
          <TD WIDTH="2px">&nbsp;</TD>
          <TD ALIGN="right" WIDTH="50px">
            <INPUT ID='gotoPage' TYPE='text' VALUE='' MAXLENGTH="8" SIZE='5' CLASS="birtviewer_navbar_input">
          </TD>
          <TD WIDTH="4px">&nbsp;</TD>
          <TD ALIGN="right" WIDTH="10px">
            <mdss:localize key="go" var="go"/>
            <INPUT TYPE="image" SRC="go.gif" NAME='${go}' ALT="${go}" TITLE="${go}" CLASS="birtviewer_clickable" ID="go_id" />
          </TD>
          <TD WIDTH="6px">&nbsp;</TD>
        </TR>
      </TABLE>
    </DIV>
   -->
    <div class="reportDiv">
      ${report}
    </div>
    
    <script type="text/javascript">
      YAHOO.util.Event.onDOMReady(function(){
//           var button = document.getElementById('go_id');
//           var input = document.getElementById('gotoPage');
//           var pageCount = <%=request.getAttribute("pageCount")%>;
//           var url = '<%=request.getAttribute("url")%>';
//           
//           var handleClick = function() {
//             var pageNumber = parseInt(input.value);
//             
//             if(Mojo.Util.isNumber(pageNumber) && pageNumber >= 1 && pageNumber <= pageCount)
//             {
//               window.location.href = url + '&pageNumber=' + pageNumber;
//             }
//             else
//             {
//               alert(MDSS.localize('Invalid_page_input'));
//             }
//           };
//           
//           YAHOO.util.Event.on(button, 'click', handleClick);   
         });
    </script>    
  </body>
</html>
