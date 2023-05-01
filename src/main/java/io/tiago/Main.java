package io.tiago;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main implements QuarkusApplication {

    private static final List<String> SINGLE_COMMANDS = List.of("help", "init");

    private static final List<String> COMPOUND_COMMANDS = List.of("enc", "dec");

    private static final List<String> ARGS = List.of("--f");

    @Override
    public int run(String... args) {

        try {
            Predicate<String> hasSingle = e -> SINGLE_COMMANDS.contains(e);
            Predicate<String> hasCompound = e -> COMPOUND_COMMANDS.contains(e);
            Command command = Arrays.stream(args)
                                    .filter(hasSingle.or(hasCompound))
                                    .map(Command::new)
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException("Unknown command."));

            Argument argument = Arrays.stream(args)
                                      .filter(ARGS::contains)
                                      .map(Argument::new)
                                      .findFirst()
                                      .orElse(null);

            int fromIndex = argument != null ? 2 : 1;
            String words = String.join(" ", Arrays.asList(args).subList(fromIndex, args.length));

            if(COMPOUND_COMMANDS.contains(command.getValue()) && words.equals("")) {
                throw new IllegalArgumentException("Invalid commmand param.");
            }
            
            Hider hider = new HiderImpl();
            switch (command.getValue()) {
                case "help" -> hider.help();
                case "init" -> hider.init();
                case "enc" -> hider.enc(words, argument);
                case "dec" -> hider.dec(words, argument);
                default -> throw new IllegalArgumentException("Unknown command.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Usage: canis <command> <args> <message>");
            // e.printStackTrace();
            return 1;
        }

        return 0;
    }
}
