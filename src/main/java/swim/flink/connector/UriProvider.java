package swim.flink.connector;

import java.io.Serializable;
import swim.uri.Uri;

/**
 * A function for constructing a {@link swim.uri.Uri} from an incoming record.
 *
 * @param <InputT> the type of incoming records
 */
interface UriProvider<InputT> extends Serializable {

  /**
   * Construct a {@link swim.uri.Uri} from an incoming record.
   *
   * @param record
   * @return
   */
  Uri get(final InputT record);

  /**
   * A function for constructing a URI {@link String} from an incoming record.
   *
   * @param <InputT> the type of incoming records
   */
  @FunctionalInterface
  interface Function<InputT> extends Serializable {

    /**
     * Construct a string from an incoming record, to be parsed into a {@link swim.uri.Uri}.
     *
     * @param record
     * @return
     */
    String get(final InputT record);

  }

}
