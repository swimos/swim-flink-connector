package swim.flink.connector;

import swim.uri.Uri;

interface UriProvider<InputT> {

  Uri get(final InputT record);

  @FunctionalInterface
  interface Function<InputT> {

    String get(final InputT record);

  }

}
