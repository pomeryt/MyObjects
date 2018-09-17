package plain.validation.map;

import plain.contract.validation.MapGetValidation;
import plain.contract.validation.MapPutValidation;

/**
 * A marker interface that helps you compose your objects.
 * A validation that can be used for both put and get operation for a map.
 * A base class is {@link IsKeyRegisteredInMap}.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> THe type of value.
 */
public interface MapPutGetValidation<K, V> extends MapPutValidation<K, V>, MapGetValidation<K, V> {}
