package discord.listeners;

import database.UserData;
import log.SysLog;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import util.Init;

import java.sql.SQLException;

public class MessageListener {
        public static void startLevelListener() {
            Init.api.addMessageCreateListener(message -> {
                User user = message.getMessageAuthor().asUser().orElseThrow(NullPointerException::new);
                try {
                    if (!UserData.userExists(user.getId())) {
                        UserData.addUser(user.getId());
                        SysLog.out(user + "'s data does not exist. Adding now...");
                    }
                } catch (SQLException e) {
                    SysLog.err("DATABASE ERROR: Could not get entry for " + user);
                }
                if (!user.isBot() && !(message.isPrivateMessage())) {
                    addXp(message.getMessage());
                }
            });
        }

        private static void addXp(Message m) {
            try {
                String message = m.getReadableContent();
                Long id = m.getAuthor().getId();
                User user = m.getUserAuthor().orElseThrow(NullPointerException::new);
                if (message.length() < 300) {
                    int levelChange = 0;
                    long xp = (long) message.length() / 5;
                    long credits = (long) message.length() / 10;
                    int level = UserData.getLevel(id);
                    if (UserData.getXp(id) + xp > nextLevelXp(UserData.getLevel(id))) {
                        levelChange = 1;
                        credits += bonusCredits(level);
                        m.getServerTextChannel().ifPresent(channel -> {
                            if (level == 0) {
                                //todo add first level up message
                                //user.sendMessage();
                            } else {
                                //todo add level up banner
                                //channel.sendMessage();
                            }
                        });
                        SysLog.out(user + " leveled up to level " + (level + 1));
                    }
                    SysLog.out("[DISCORD] " + user + ": " + message + " [MESSAGE INFO] +" + xp + " XP, +" + credits + " credits");
                    UserData.setXp(id, UserData.getXp(id) + xp);
                    UserData.setLevel(id, level + levelChange);
                    UserData.setCredits(id, UserData.getCredits(id) + credits);
                } else {
                    SysLog.out(user + ": " + message);
                }
            } catch (SQLException e) {
                SysLog.err("DATABASE ERROR: Could not access XP for " + m.getUserAuthor());
                SysLog.err(e.getMessage());
            }
        }

        public static double nextLevelXp(int level) {
            if (level == 0) return 0;
            return (Math.pow(level, 1.50) * (level * 25)) + 350;
        }

        public static long bonusCredits(int level) {
            return level * 100L + 100;
        }
    }
