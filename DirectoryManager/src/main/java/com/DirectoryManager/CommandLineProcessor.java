package com.DirectoryManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class CommandLineProcessor {
    private final FileSystem fs;

    public CommandLineProcessor() {
        this.fs = new FileSystem();
    }

    public String processCommands(String commandInput) {
        StringBuilder outputBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new StringReader(commandInput))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;

                outputBuilder.append(line).append("\n");

                String[] parts = line.split("\\s+", 2);
                String command = parts[0];
                try {
                    switch (command) {
                        case "CREATE":
                            if (parts.length < 2) {
                                throw new Exception("CREATE command requires a path");
                            }
                            fs.createDir(parts[1]);
                            break;

                        case "DELETE":
                            if (parts.length < 2) {
                                throw new Exception("DELETE command requires a path");
                            }
                            try {
                                fs.delete(parts[1]);
                            } catch (Exception e) {
                                outputBuilder.append("Cannot delete ").append(parts[1]).append(" - ").append(e.getMessage()).append("\n");
                            }
                            break;

                        case "MOVE":
                            if (parts.length < 2) {
                                throw new Exception("MOVE command requires a source and destination path");
                            }

                            String[] paths = parts[1].split("\\s+");
                            if (paths.length != 2) {
                                throw new Exception("MOVE command requires exactly two paths");
                            }
                            fs.move(paths[0], paths[1]);
                            break;

                        case "LIST":
                            StringBuilder listOutput = new StringBuilder();
                            fs.list(listOutput);
                            outputBuilder.append(listOutput);
                            break;

                        default:
                            outputBuilder.append("Unknown command: ").append(command).append("\n");
                    }
                } catch (Exception e) {
                    outputBuilder.append("ERROR: ").append(e.getMessage()).append("\n");
                }
            }
        } catch (IOException e) {
            outputBuilder.append("ERROR processing commands: ").append(e.getMessage()).append("\n");
        }
        return outputBuilder.toString();
    }
}
