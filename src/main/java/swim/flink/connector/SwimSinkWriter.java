package swim.flink.connector;

import java.io.IOException;
import org.apache.flink.api.connector.sink2.SinkWriter;
import swim.client.ClientRuntime;

/**
 * Class responsible for sending incoming messages from Flink to a Swim application.
 *
 * @param <InputT> type of the records to be written to Swim application
 */
class SwimSinkWriter<InputT> implements SinkWriter<InputT> {

  private final UriProvider<InputT> hostUriProvider;
  private final UriProvider<InputT> nodeUriProvider;
  private final UriProvider<InputT> laneUriProvider;
  private final RecordValueMolder<InputT> recordValueMolder;

  private final ClientRuntime client;

  SwimSinkWriter(
          final UriProvider<InputT> hostUriProvider,
          final UriProvider<InputT> nodeUriProvider,
          final UriProvider<InputT> laneUriProvider,
          final RecordValueMolder<InputT> recordValueMolder) {
    this.hostUriProvider = hostUriProvider;
    this.nodeUriProvider = nodeUriProvider;
    this.laneUriProvider = laneUriProvider;
    this.recordValueMolder = recordValueMolder;

    this.client = new ClientRuntime();
    this.client.start();
  }

  @Override
  public void write(InputT element, Context context) throws IOException, InterruptedException {
    this.client.command(
            this.hostUriProvider.get(element),
            this.nodeUriProvider.get(element),
            this.laneUriProvider.get(element),
            this.recordValueMolder.mold(element)
    );
  }

  @Override
  public void flush(boolean endOfInput) throws IOException, InterruptedException {

  }

  @Override
  public void close() throws Exception {
    this.client.close();
  }

}
