package discord;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import java.util.Optional;

public class Util {
    public static User getMentionedUser(Message m, User u, String[] args) {
        if (args.length == 0) {
            return u;
        } else {
            return Util.tryGetMentionedUser(m, args[0]).orElse(null);
        }
    }

    public static Optional<User> tryGetMentionedUser(Message m, String arg) {
        return m.getMentionedUsers().stream()
                .findFirst()
                .map(Optional::of)
                .orElse(util.Init.api.getCachedUserById(arg));
    }

    private static Optional<User> tryGetMentionedUserID(Long arg) {
        return util.Init.api.getCachedUserById(arg);
    }
}