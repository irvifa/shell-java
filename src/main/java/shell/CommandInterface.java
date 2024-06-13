// CommandInterface.java
package shell;

import java.util.List;

public interface CommandInterface {
    boolean execute(List<String> args);
}
