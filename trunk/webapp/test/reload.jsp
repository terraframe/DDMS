<%@page import="com.terraframe.mojo.generation.loader.LoaderDecorator"%>
<%
LoaderDecorator.reload();
out.println("<h1>Reloaded!</h1>");
%>