package com.DirectoryManager;

public class DirectoryManagerApplication {
    public static void main(String[] args) {
        String commands = "CREATE fruits\n" +
                "CREATE vegetables\n" +
                "CREATE grains\n" +
                "CREATE fruits/apples\n" +
                "CREATE fruits/apples/fuji\n" +
                "LIST\n" +
                "CREATE grains/squash\n" +
                "MOVE grains/squash vegetables\n" +
                "CREATE foods\n" +
                "MOVE grains foods\n" +
                "MOVE fruits foods\n" +
                "MOVE vegetables foods\n" +
                "LIST\n" +
                "DELETE fruits/apples\n" +
                "DELETE foods/fruits/apples\n" +
                "LIST";
        CommandLineProcessor clp = new CommandLineProcessor();
        String output = clp.processCommands(commands);
        System.out.println(commands + "\n\n\n\n");
        System.out.println(output);
    }

}
