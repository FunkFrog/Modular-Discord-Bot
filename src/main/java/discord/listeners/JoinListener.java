package discord.listeners;

import database.UserData;
import log.SysLog;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import util.EmbedBuilders;
import util.Init;

import java.sql.SQLException;

public class JoinListener {
    public static void init() {
        Init.api.addServerMemberJoinListener(join -> addUserData(join.getUser()));
//        Init.api.addMessageCreateListener(messageCreateEvent -> {
//           if (messageCreateEvent.getChannel() == Init.botChannel && !messageCreateEvent.getMessageAuthor().isBotUser()) {
//               System.out.println("Message sent");
//               messageCreateEvent.getChannel().sendMessage("oog");
//           }
//        });
    }

    private static void addUserData(User user) {
        try {
            if (!UserData.userExists(user.getId())) {
                UserData.addUser(user.getId());

                welcomeNew(user);
                SysLog.out(user + " joined the server for the first known time.");
            } else {
                welcomeBack(user);
                SysLog.out(user + " rejoined the server.");
            }
        } catch (SQLException e) {
            SysLog.err("DATABASE ERROR: Could not access userdata for " + user);
        }
    }

    private static void welcomeNew(User user) {
        Init.welcomeChannel.sendMessage(EmbedBuilders.welcomeEmbed(user));
    }

    private static void welcomeBack(User user) {
        //todo Send welcome back message
    }
}