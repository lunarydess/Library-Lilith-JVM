// @formatter:off
package rip.lunarydess.lilith.utility.interfaces;
import java.io.Closeable;
public interface IService extends Runnable, Closeable, INameable { @Override void close(); }
