package pro.redsoft.expert.exampleModule;

import org.elasticsearch.common.inject.AbstractModule;

/**
 * Created by Labotsky Alexey on 11/19/15. lesharb@gmail.com
 */
public class HelloRestModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(HelloRestHandler.class).asEagerSingleton();
  }
}
