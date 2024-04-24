package util;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import java.awt.*;

public class EmbedBuilders {
    public static EmbedBuilder welcomeEmbed(User user) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setAuthor("Welcome, " + user.getName() + "!", "", Init.api.getYourself().getAvatar());
        builder.setDescription("**" + user.getMentionTag() +
                ", please check <#1227068978644520962> for rules and role selection!**");
        builder.setColor(Color.YELLOW);
        builder.setFooter("Warframe Bot");
        builder.setThumbnail(user.getAvatar());

        return builder;
    }
}
