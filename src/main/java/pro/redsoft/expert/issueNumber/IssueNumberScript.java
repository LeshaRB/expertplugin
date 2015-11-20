package pro.redsoft.expert.issueNumber;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.AbstractSearchScript;

import java.util.Map;

/**
 * Created by Labotsky Alexey on 11/19/15. lesharb@gmail.com
 */
public class IssueNumberScript extends AbstractSearchScript {
  String fieldParam;
  int lengthParam;

  public IssueNumberScript(@Nullable Map params) {
    fieldParam = (String) params.get("field");
    lengthParam = new Integer(params.get("length").toString()).intValue();
  }

  @Override
  public Object run() {
    return null;
  }
}