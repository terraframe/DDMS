<%
for (Integer i = 0; i < 52; i++)
{
  epiDate = EpiDateDTO.getInstanceByPeriod(clientRequest,PeriodTypeDTO.WEEK, i, testYear);
  week = new GregorianCalendar();
  week.clear();
  week.setTime(epiDate.getStartDate());
  out.println("<tr class=\"" + (i % 2 == 0 ? "evenRow" : "oddRow") +"\">");
  out.print("<td>" + Integer.toString(i) + "</td>");
  out.print("<td>" + mf.format(week.getTime()) + "</td>");
  for (int j = 0; j < 7; j++)
  {
    out.print("<td>" + df.format(week.getTime()) + "</td>");
    week.add(Calendar.DAY_OF_WEEK, 1);
  }
  out.print("<td>" + mf.format(week.getTime()) + "</td>");
  out.println("</tr>");
}
%>