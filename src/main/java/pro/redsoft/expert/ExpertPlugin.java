package pro.redsoft.expert;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.script.ScriptModule;
import pro.redsoft.expert.exampleModule.HelloRestModule;
import pro.redsoft.expert.issueNumber.IssueNumberScriptFactory;

import java.util.Collection;

/**
 * Created by Labotsky Alexey on 11/19/15. lesharb@gmail.com
 */
public class ExpertPlugin extends AbstractPlugin {

  @Override
  public String name() {
    return "ExpertPlugin";
  }

  @Override
  public String description() {
    return "Plugins for Expert";
  }

  public void onModule(ScriptModule scriptModule) {
    scriptModule.registerScript("IssueNumberScript", IssueNumberScriptFactory.class);
  }

  @Override
  public Collection<Class<? extends Module>> modules() {
    Collection<Class<? extends Module>> modules = Lists.newArrayList();
    modules.add(HelloRestModule.class);
    return modules;
  }
}