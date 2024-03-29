package swim.flink.connector;

import swim.uri.Uri;

/**
 * Implementation of {@link UriProvider} which always returns the same {@link swim.uri.Uri},
 * regardless of incoming record.
 *
 * @param <InputT> the type of incoming record
 */
class StaticUriProvider<InputT> implements UriProvider<InputT> {

  private static final long serialVersionUID = 1L;

  private final String uri;

  StaticUriProvider(final String uri) {
    this.uri = uri;
  }

  public Uri get(final InputT record) {
    return Uri.parse(this.uri);
  }

}
