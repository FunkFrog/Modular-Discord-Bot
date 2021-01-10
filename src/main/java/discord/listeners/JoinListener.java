package discord.listeners;

import database.UserData;
import log.SysLog;
import org.javacord.api.entity.user.User;
import util.Init;

import java.sql.SQLException;

class JoinListener {
    //todo make this toggleable per server
    JoinListener() {
        Init.api.addServerMemberJoinListener(join -> addUserData(join.getUser()));
    }

    private void addUserData(User user) {
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

    private void welcomeNew(User user) {
        //todo Send welcome message
    }

    private void welcomeBack(User user) {
        //todo Send welcome back message
    }
}