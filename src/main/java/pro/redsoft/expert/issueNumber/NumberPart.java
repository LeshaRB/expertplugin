package pro.redsoft.expert.issueNumber;

import org.elasticsearch.index.fielddata.ScriptDocValues;
import pro.redsoft.expert.utils.Utils;

/**
 * Created by Labotsky Alexey on 11/20/15. lesharb@gmail.com
 */
public class NumberPart {
  private String firstPart;
  private String secondPart;
  private ScriptDocValues.Longs sIssueDate;
  private ScriptDocValues.Longs sUpdateDate;

  public NumberPart(String firstPart, String secondPart) {
    this.firstPart = firstPart;
    this.secondPart = secondPart;
  }

  public Integer getFirstPart() {
    return Integer.parseInt(firstPart);
  }

  public String getSecondPart() {
    return secondPart;
  }

  public void setFirstPart(String firstPart) {
    this.firstPart = firstPart;
  }

  public void setSecondPart(String secondPart) {
    this.secondPart = secondPart;
  }

  public long getsIssueDate() {
    if (sIssueDate != null && !sIssueDate.isEmpty())
      return sIssueDate.get(0);
    else
      return 0L;
  }

  public void setsIssueDate(ScriptDocValues.Longs sIssueDate) {
    this.sIssueDate = sIssueDate;
  }

  public long getsUpdateDate() {
    if (sUpdateDate != null && !sUpdateDate.isEmpty())
      return sUpdateDate.get(0);
    else
      return 0L;
  }

  public void setsUpdateDate(ScriptDocValues.Longs sUpdateDate) {
    this.sUpdateDate = sUpdateDate;
  }

  public String getHexEncode() {
    // StringBuilder sb = new StringBuilder().append("\"").append(getFirstPart()).append("\"_\"")
    // .append(getSecondPart()).append("\"_\"").append(String.valueOf(getsUpdateDate())).append("\"_\"")
    // .append(String.valueOf(getsUpdateDate())).append("\"");

    StringBuilder hash = new StringBuilder();
    hash.append(Utils.hexEncode(Utils.intToBytes(getFirstPart())));
    hash.append(Utils.hexEncode(getSecondPart().getBytes()));
    hash.append(Utils.hexEncode(Utils.longToBytes(getsIssueDate())));
    hash.append(Utils.hexEncode(Utils.longToBytes(getsUpdateDate())));
    return hash.toString();
  }
}
