package swim.flink.connector;

import org.testng.annotations.Test;
import swim.uri.Uri;
import static org.testng.Assert.assertEquals;

public class DynamicUriProviderSpec {

  @Test
  public void dynamicUri() {
    final DynamicUriProvider<String> dynamicUriProvider = new DynamicUriProvider<>((string) -> "/agent/" + string);
    assertEquals(dynamicUriProvider.get("User_1"), Uri.parse("/agent/User_1"));
    assertEquals(dynamicUriProvider.get("User_2"), Uri.parse("/agent/User_2"));
  }

}
