package pro.redsoft.expert.issueNumber;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.index.fielddata.ScriptDocValues;
import org.elasticsearch.script.AbstractSearchScript;
import pro.redsoft.expert.utils.Utils;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Labotsky Alexey on 11/19/15. lesharb@gmail.com
 */
public class IssueNumberScript extends AbstractSearchScript {

  private static Logger logger = Logger.getLogger(IssueNumberScript.class.getSimpleName());

  private NumberPart numberPart;

  public IssueNumberScript(@Nullable Map params) {
  }

  @Override
  public Object run() {
    ScriptDocValues.Strings issueNumber = (ScriptDocValues.Strings) doc().get("sIssueNumber");
    numberPart = Utils.parseNumber(issueNumber);

    ScriptDocValues.Longs issueDate = (ScriptDocValues.Longs) doc().get("sIssueDate");
    ScriptDocValues.Longs updateDate = (ScriptDocValues.Longs) doc().get("sUpdateDate");

    // logger.info("size = " +fields().size());
    // logger.info(
    // "num = " + issueNumber + "part1 = " + numberPart.getFirstPart() + " part2 = " + numberPart.getSecondPart());

    // logger.info("size = "+source().size());
    StringBuilder sb = new StringBuilder().append(numberPart.getFirstPart()).append("_")
        .append(numberPart.getSecondPart()).append("_").append(String.valueOf(issueDate)).append("_")
        .append(String.valueOf(updateDate));

    return sb.toString();
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

}