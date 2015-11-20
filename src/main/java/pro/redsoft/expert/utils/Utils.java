package pro.redsoft.expert.utils;

import org.elasticsearch.index.fielddata.ScriptDocValues;
import pro.redsoft.expert.issueNumber.NumberPart;

/**
 * Created by Labotsky Alexey on 11/20/15. lesharb@gmail.com
 */
public class Utils {

  // Числовая часть по умолчанию
  private static final String NUM_PART = "1000000";

  // Числовая часть для номеров, у которых ее нет (hack)
  private static final String NUM_PART_WITHOUT_NUMBER = "999999";

  public static Integer getIntNUM_PART() {
    return Integer.parseInt(NUM_PART);
  }

  public static NumberPart parseNumber(ScriptDocValues.Strings number) {
    // Проверяем на "безномерность"
    if (number != null && !number.isEmpty()) {
      String formatString = number.get(0).trim();
      if (formatString.equals("") || formatString.equals("-") || formatString.equals("б/н"))
        return new NumberPart(NUM_PART, "");
      else {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < formatString.length(); i++) {
          if (Character.isDigit(formatString.charAt(i)))
            sb.append(formatString.charAt(i));
          else
            break;
        }
        String firstPart = sb.toString();
        String secondPart = formatString.replaceFirst(firstPart, "");

        if (firstPart.isEmpty())
          firstPart = NUM_PART_WITHOUT_NUMBER;

        return new NumberPart(firstPart, secondPart);
      }
    } else
      return new NumberPart(NUM_PART, "");
  }

}
