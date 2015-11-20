package pro.redsoft.expert.exampleModule;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.rest.*;

import static org.elasticsearch.rest.RestRequest.Method.GET;
import static org.elasticsearch.rest.RestStatus.OK;

/**
 * Created by Labotsky Alexey on 11/19/15. lesharb@gmail.com
 */
public class HelloRestHandler implements RestHandler {
  @Inject
  public HelloRestHandler(RestController restController) {
    restController.registerHandler(GET, "/_hello", this);
  }

  @Override
  public void handleRequest(final RestRequest request, final RestChannel channel) {
    String who = request.param("person");
    String whoSafe = (who != null) ? who : "мир";
    channel.sendResponse(new BytesRestResponse(OK, "Привет, " + whoSafe + "! =) \n"));
  }
}
