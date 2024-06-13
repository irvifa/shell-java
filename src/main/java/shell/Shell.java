// src/shell/Shell.java
package shell;

import shell.commands.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Shell {
    private final Map<String, CommandInterface> commands = new HashMap<>();
    private final Set<String> builtins = new HashSet<>(Arrays.asList("exit", "echo", "cd", "type"));

    public Shell() {
        commands.put("exit", new ExitCommand());
        commands.put("echo", new EchoCommand());
        commands.put("cd", new CdCommand());
        commands.put("type", new TypeCommand(builtins));
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("$ ");
                String input = reader.readLine();
                if (input == null || input.trim().isEmpty()) {
                    continue;
                }

                Command command = tokenize(input);
                if (!execute(command)) {
                    runExternalCommand(command);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Command tokenize(String input) {
        List<String> tokens = new ArrayList<>(Arrays.asList(input.split("\\s+")));
        String name = tokens.remove(0);
        return new Command(name, tokens);
    }

    private boolean execute(Command command) {
        CommandInterface cmd = commands.get(command.name);
        if (cmd != null) {
            return cmd.execute(command.args);
        }
        return false;
    }

    private void runExternalCommand(Command command) {
        try {
            List<String> cmd = new ArrayList<>();
            cmd.add(command.name);
            cmd.addAll(command.args);
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            System.err.println(command.name + ": command not found");
        }
    }

    private static class Command {
        String name;
        List<String> args;

        Command(String name, List<String> args) {
            this.name = name;
            this.args = args;
        }
    }
}
