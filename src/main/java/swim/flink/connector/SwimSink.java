package swim.flink.connector;

import java.io.IOException;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.api.connector.sink2.SinkWriter;
import swim.uri.Uri;

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

  public static <InputT> Builder<InputT> builder() {
    return new Builder<>();
  }

  public static class Builder<InputT> {

    private UriProvider<InputT> hostUriProvider;
    private UriProvider<InputT> nodeUriProvider;
    private UriProvider<InputT> laneUriProvider;
    private RecordValueMolder<InputT> recordValueMolder;

    public Builder<InputT> setHostUri(final String hostUri) {
      this.hostUriProvider = new StaticUriProvider<>(hostUri);
      return this;
    }

    public Builder<InputT> setHostUri(final Uri hostUri) {
      this.hostUriProvider = new StaticUriProvider<>(hostUri);
      return this;
    }

    public Builder<InputT> setHostUri(final UriProvider.Function<InputT> hostUri) {
      this.hostUriProvider = new DynamicUriProvider<>(hostUri);
      return this;
    }
    
    public Builder<InputT> setNodeUri(final String nodeUri) {
      this.nodeUriProvider = new StaticUriProvider<>(nodeUri);
      return this;
    }

    public Builder<InputT> setNodeUri(final Uri nodeUri) {
      this.nodeUriProvider = new StaticUriProvider<>(nodeUri);
      return this;
    }

    public Builder<InputT> setNodeUri(final UriProvider.Function<InputT> nodeUri) {
      this.nodeUriProvider = new DynamicUriProvider<>(nodeUri);
      return this;
    }

    public Builder<InputT> setLaneUri(final String laneUri) {
      this.laneUriProvider = new StaticUriProvider<>(laneUri);
      return this;
    }

    public Builder<InputT> setLaneUri(final Uri laneUri) {
      this.laneUriProvider = new StaticUriProvider<>(laneUri);
      return this;
    }

    public Builder<InputT> setLaneUri(final UriProvider.Function<InputT> laneUri) {
      this.laneUriProvider = new DynamicUriProvider<>(laneUri);
      return this;
    }

    public Builder<InputT> setRecordValueMolder(final RecordValueMolder<InputT> recordValueMolder) {
      this.recordValueMolder = recordValueMolder;
      return this;
    }

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
