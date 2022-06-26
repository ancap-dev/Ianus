package ru.ancap.ianus.plugin.player;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import ru.ancap.ianus.limit.Limit;
import ru.ancap.ianus.plugin.config.LimitSection;
import ru.ancap.ianus.limit.Limited;

public class IanusControlled implements Limited {

    private final Player player;
    private final ConfigurationSection section;

    public IanusControlled(Player player, ConfigurationSection limitsSection) {
        this.player = player;
        this.section = limitsSection;
    }

    @Override
    public void setupLimit() {
        Limit all = new LimitSection(section.getConfigurationSection("default"));
        for (String permKey : section.getKeys(false)) {
            if (player.hasPermission(permKey)) {
                new LimitSection(section.getConfigurationSection(permKey)).apply(player);
            }
        }
        all.apply(player);
    }
}
