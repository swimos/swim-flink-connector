package swim.flink.connector;

import org.testng.annotations.Test;
import swim.uri.Uri;
import static org.testng.Assert.assertEquals;

public class StaticUriProviderSpec {

  @Test
  public void staticUri() {
    final StaticUriProvider<String> staticUriProvider = new StaticUriProvider<>("warp://localhost:9001");
    assertEquals(staticUriProvider.get("User_1"), Uri.parse("warp://localhost:9001"));
  }

}
