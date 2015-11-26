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
    ScriptDocValues.Longs issueDate = (ScriptDocValues.Longs) doc().get("sIssueDate");
    ScriptDocValues.Longs updateDate = (ScriptDocValues.Longs) doc().get("sUpdateDate");

    numberPart = Utils.parseNumber(issueNumber);
    numberPart.setsIssueDate(issueDate);
    numberPart.setsUpdateDate(updateDate);

    String hash = numberPart.getHexEncode();
    // logger.info(hash);

    return hash;
  }

}