package io.tiago;

import java.util.Arrays;
import java.util.function.Predicate;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.tiago.encoders.Encoder;
import io.tiago.encoders.MemoryEnconder;
import io.tiago.enums.CompoundCommand;
import io.tiago.enums.Option;
import io.tiago.enums.SingleCommand;
import io.tiago.pojos.Argument;
import io.tiago.pojos.Command;
import io.tiago.pojos.Payload;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Override
    public int run(String... args) {

        try {
            Predicate<String> hasSingle = e -> SingleCommand.getValues().contains(e);
            Predicate<String> hasCompound = e -> CompoundCommand.getValues().contains(e);
            Command command = Arrays.stream(args)
                                    .filter(hasSingle.or(hasCompound))
                                    .map(Command::new)
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException("Unknown command."));

            Argument argument = Arrays.stream(args)
                                    .filter(Option.getValues()::contains)
                                    .map(Argument::new)
                                    .findFirst()
                                    .orElse(null);

            int fromIndex = argument != null ? 2 : 1;
            String content = String.join(" ", Arrays.asList(args).subList(fromIndex, args.length));

            if(CompoundCommand.getValues().contains(command.getValue()) && content.equals("")) {
                throw new IllegalArgumentException("Invalid commmand param.");
            }
            
            Encoder encoder = new MemoryEnconder();
            switch (command.getValue()) {
                case "help" -> encoder.help();
                case "init" -> encoder.init();
                case "enc" -> encoder.enc(new Payload(content));
                case "dec" -> encoder.dec(new Payload(content));
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
