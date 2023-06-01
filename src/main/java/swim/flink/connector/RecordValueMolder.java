package swim.flink.connector;

import java.io.Serializable;
import swim.structure.Value;

/**
 * A function for molding the incoming records of type {@code InputT} to
 * {@link swim.structure.Value}.
 *
 * @param <InputT>> the type of incoming records to be molded
 */
@FunctionalInterface
public interface RecordValueMolder<InputT> extends Serializable {

  /**
   * Mold input record to {@link swim.structure.Value}.
   *
   * @param record input record
   * @return
   */
  Value mold(final InputT record);

}
