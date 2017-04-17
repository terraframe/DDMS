package dss.vector.solutions.irs;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;

public class TeamMemberViewComparator implements Comparator<TeamMemberView>, Reloadable
{
  public int compare(TeamMemberView o1, TeamMemberView o2)
  {
    String _memberId1 = o1.getMemberId();
    String _memberId2 = o2.getMemberId();

    return _memberId1.compareTo(_memberId2);
  }
}
