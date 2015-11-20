package pro.redsoft.expert.issueNumber;

/**
 * Created by Labotsky Alexey on 11/20/15. lesharb@gmail.com
 */
public class NumberPart {
  private String firstPart;
  private String secondPart;

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
}
