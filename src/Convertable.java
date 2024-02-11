/*
 * The Convertable interface represents a unit that can perform currency conversion.
 */

public interface Convertable {

  /**
   * Converts currency according to specific rules implemented by implementing classes.
   *
   * @return the converted amount
   */

  double convert();
}
