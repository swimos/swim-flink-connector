package swim.flink.connector;

import swim.uri.Uri;

class StaticUriProvider<InputT> implements UriProvider<InputT> {

  private final Uri uri;

  StaticUriProvider(final String uri) {
    this.uri = Uri.parse(uri);
  }

  StaticUriProvider(final Uri uri) {
    this.uri = uri;
  }

  public Uri get(final InputT record) {
    return this.uri;
  }

}
