package swim.flink.connector;

import swim.uri.Uri;

/**
 * Implementation of {@link UriProvider} which returns a different URI for
 * different incoming records.
 *
 * @param <InputT> the type of incoming record
 */
class DynamicUriProvider<InputT> implements UriProvider<InputT> {

  private static final long serialVersionUID = 1L;

  private final UriProvider.Function<InputT> function;

  DynamicUriProvider(final UriProvider.Function<InputT> function) {
    this.function = function;
  }

  public Uri get(final InputT record) {
    return Uri.parse(this.function.get(record));
  }

}
