<%@page import="com.runwaysdk.generation.loader.LoaderDecorator"%>
<%
LoaderDecorator.reload();
out.println("<h1>Reloaded!</h1>");
%>