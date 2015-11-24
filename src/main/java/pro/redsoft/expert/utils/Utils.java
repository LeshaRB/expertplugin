package pro.redsoft.expert.utils;

import org.elasticsearch.index.fielddata.ScriptDocValues;
import pro.redsoft.expert.issueNumber.NumberPart;

import java.nio.ByteBuffer;

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

  public static byte[] intToBytes(final int integer) {
    byte[] result = new byte[4];

    result[0] = (byte)((integer & 0xFF000000) >> 24);
    result[1] = (byte)((integer & 0x00FF0000) >> 16);
    result[2] = (byte)((integer & 0x0000FF00) >> 8);
    result[3] = (byte)(integer & 0x000000FF);

    return result;
  }

  public static byte[] longToBytes(long x) {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong(x);
    return buffer.array();
  }

  public static String hexEncode(byte[] aInput) {
    StringBuilder result = new StringBuilder();
    char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    for (int idx = 0; idx < aInput.length; ++idx) {
      byte b = aInput[idx];
      result.append(digits[(b & 0xf0) >> 4]);
      result.append(digits[b & 0x0f]);
    }
    return result.toString();
  }

}
