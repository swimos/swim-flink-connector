package swim.flink.connector;

import java.io.IOException;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.api.connector.sink2.SinkWriter;

/**
 * Flink Sink to send data into a Swim application.
 *
 * @param <InputT> type of the records to be written to Swim application
 * @see Builder on how to construct a SwimSink
 */
public class SwimSink<InputT> implements Sink<InputT> {

  private final UriProvider<InputT> hostUriProvider;
  private final UriProvider<InputT> nodeUriProvider;
  private final UriProvider<InputT> laneUriProvider;
  private final RecordValueMolder<InputT> recordValueMolder;

  SwimSink(
          final UriProvider<InputT> hostUriProvider,
          final UriProvider<InputT> nodeUriProvider,
          final UriProvider<InputT> laneUriProvider,
          final RecordValueMolder<InputT> recordValueMolder) {
    this.hostUriProvider = hostUriProvider;
    this.nodeUriProvider = nodeUriProvider;
    this.laneUriProvider = laneUriProvider;
    this.recordValueMolder = recordValueMolder;
  }

  @Override
  public SinkWriter<InputT> createWriter(InitContext context) throws IOException {
    return new SwimSinkWriter<>(
            this.hostUriProvider,
            this.nodeUriProvider,
            this.laneUriProvider,
            this.recordValueMolder
    );
  }

  /**
   * Create a {@link Builder} to construct a new {@link SwimSink}.
   *
   * @param <InputT>> type of incoming records
   * @return {@link Builder}
   * */
  public static <InputT> Builder<InputT> builder() {
    return new Builder<>();
  }

  /**
   * Builder to construct {@link SwimSink}.
   *
   * <p> An example configuration to send records to a local Swim application
   *  can be seen below.
   *
   * <pre>{@code
   * SwimSink<String> sink = SwimSink
   *     .<String>builder()
   *     .setHostUri("warp://localhost:9001")
   *     .setNodeUri(record -> "/agent/" + record)
   *     .setLaneUri("latest")
   *     .setRecordValueMolder(Text::from)
   *     .build();
   * }</pre>
   *
   * @param <InputT> type of the records to be written to Swim application
   */
  public static class Builder<InputT> {

    private UriProvider<InputT> hostUriProvider;
    private UriProvider<InputT> nodeUriProvider;
    private UriProvider<InputT> laneUriProvider;
    private RecordValueMolder<InputT> recordValueMolder;

    /**
     * Set the host uri.
     *
     * @param hostUri
     * @return {@link Builder}
     */
    public Builder<InputT> setHostUri(final String hostUri) {
      this.hostUriProvider = new StaticUriProvider<>(hostUri);
      return this;
    }

    /**
     * Set the host uri.
     *
     * @param hostUri a function to construct a uri based on the incoming record
     * @return {@link Builder}
     */
    public Builder<InputT> setHostUri(final UriProvider.Function<InputT> hostUri) {
      this.hostUriProvider = new DynamicUriProvider<>(hostUri);
      return this;
    }

    /**
     * Set the node uri.
     *
     * @param nodeUri
     * @return {@link Builder}
     */
    public Builder<InputT> setNodeUri(final String nodeUri) {
      this.nodeUriProvider = new StaticUriProvider<>(nodeUri);
      return this;
    }

    /**
     * Set the node uri.
     *
     * @param nodeUri a function to construct a uri based on the incoming record
     * @return {@link Builder}
     */
    public Builder<InputT> setNodeUri(final UriProvider.Function<InputT> nodeUri) {
      this.nodeUriProvider = new DynamicUriProvider<>(nodeUri);
      return this;
    }

    /**
     * Set the lane uri.
     *
     * @param laneUri
     * @return {@link Builder}
     */
    public Builder<InputT> setLaneUri(final String laneUri) {
      this.laneUriProvider = new StaticUriProvider<>(laneUri);
      return this;
    }

    /**
     * Set the lane uri.
     *
     * @param laneUri a function to construct a uri based on the incoming record
     * @return {@link Builder}
     */
    public Builder<InputT> setLaneUri(final UriProvider.Function<InputT> laneUri) {
      this.laneUriProvider = new DynamicUriProvider<>(laneUri);
      return this;
    }

    /**
     * Sets the {@link RecordValueMolder} that molds the incoming record into {@link
     * swim.structure.Value}.
     *
     * @param recordValueMolder
     * @return {@link Builder}
     */
    public Builder<InputT> setRecordValueMolder(final RecordValueMolder<InputT> recordValueMolder) {
      this.recordValueMolder = recordValueMolder;
      return this;
    }

    /**
     * Builds a {@link SwimSink} with the configured properties.
     *
     * @return {@link SwimSink}
     */
    public SwimSink<InputT> build() {
      return new SwimSink<>(
              this.hostUriProvider,
              this.nodeUriProvider,
              this.laneUriProvider,
              this.recordValueMolder
      );
    }

  }

}
