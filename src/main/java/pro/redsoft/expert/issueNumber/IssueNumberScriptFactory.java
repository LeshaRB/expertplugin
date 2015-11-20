package pro.redsoft.expert.issueNumber;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Labotsky Alexey on 11/19/15. lesharb@gmail.com
 */
public class IssueNumberScriptFactory implements NativeScriptFactory {

  private static Logger logger = Logger.getLogger(IssueNumberScript.class.getSimpleName());

  @Override
  public ExecutableScript newScript(@Nullable Map<String, Object> params) {
    return new IssueNumberScript(params);
  }

}