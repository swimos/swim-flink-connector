package swim.flink.connector;

import swim.uri.Uri;

/**
 * Implementation of {@link UriProvider} which returns a different uri for
 * different incoming records.
 *
 * @param <InputT> the type of incoming record
 */
class DynamicUriProvider<InputT> implements UriProvider<InputT> {

  private final UriProvider.Function<InputT> function;

  DynamicUriProvider(final UriProvider.Function<InputT> function) {
    this.function = function;
  }

  public Uri get(final InputT record) {
    return Uri.parse(this.function.get(record));
  }

}
