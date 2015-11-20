package pro.redsoft.expert.issueNumber;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.AbstractSearchScript;

import java.util.Map;

/**
 * Created by Labotsky Alexey on 11/19/15. lesharb@gmail.com
 */
public class IssueNumberScript extends AbstractSearchScript {

  private String issueNumber;
  private Long issueDate;
  private Long updateDate;

  public IssueNumberScript(@Nullable Map params) {
    issueNumber = (String) params.get("sIssueNumber");
    issueDate = (Long) params.get("sIssueDate");
    updateDate = (Long) params.get("sUpdateDate");
  }

  @Override
  public Object run() {
    return null;
  }

  // Числовая часть по умолчанию
  private final String NUM_PART = "1000000";

  // Числовая часть для номеров, у которых ее нет (hack)
  private final String NUM_PART_WITHOUT_NUMBER = "999999";

  class NumberPart {
    String firstPart;
    String secondPart;

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

  private Integer getIntNUM_PART() {
    return Integer.parseInt(NUM_PART);
  }

  // private int checkDates(DocumentBean o1, DocumentBean o2) {
  // int issueDate = o1.getSissueDate().compareTo(o2.getSissueDate());
  // if (issueDate == 0) {
  // int updateDate = o1.getSupdateDate().compareTo(o2.getSupdateDate());
  // return updateDate;
  // } else
  // return issueDate;
  // }
  //
  // @Override
  // public int compare(DocumentBean o1, DocumentBean o2) {
  //
  // NumberPart numberPartDoc1;
  // NumberPart numberPartDoc2;
  //
  // // Анализируем и подготавливаем строки для сравнения по числовой состовляющей
  // numberPartDoc1 = parseNumber(o1.getIssueNumber().get(i));
  // numberPartDoc2 = parseNumber(o2.getIssueNumber().get(i));
  //
  // if (numberPartDoc1.getFirstPart() != getIntNUM_PART() && numberPartDoc2.getFirstPart() != getIntNUM_PART()) {
  // int firstPart = numberPartDoc1.getFirstPart().compareTo(numberPartDoc2.getFirstPart());
  // if (firstPart == 0) {
  // int secondPart = numberPartDoc1.getSecondPart().compareTo(numberPartDoc2.getSecondPart());
  // if (secondPart == 0) {
  // return checkDates(o1, o2);
  // } else
  // return secondPart;
  // }
  // return firstPart;
  // }
  // }return 0;
  //
  // }

  private NumberPart parseNumber(String number) {
    // Проверяем на "безномерность"
    if (number != null) {
      String formatString = number.trim();
      if (formatString.equals("") || formatString.equals("-") || formatString.equals("б/н"))
        return new NumberPart(NUM_PART, "");
      else {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
          if (Character.isDigit(number.charAt(i)))
            sb.append(number.charAt(i));
          else
            break;
        }
        String firstPart = sb.toString();
        String secondPart = number.replaceFirst(firstPart, "");

        if (firstPart.isEmpty())
          firstPart = NUM_PART_WITHOUT_NUMBER;

        return new NumberPart(firstPart, secondPart);
      }
    } else
      return new NumberPart(NUM_PART, "");
  }
}