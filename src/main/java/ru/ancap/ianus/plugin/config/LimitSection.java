package ru.ancap.ianus.plugin.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.ancap.ianus.limit.Limit;

public class LimitSection implements Limit {

    private final ConfigurationSection section;

    public LimitSection(@NotNull ConfigurationSection section) {
        this.section = section;
    }

    @Override
    public void apply(Player player) {
        if (this.isPerWorld()) {
            String worldName = player.getWorld().getName();
            Limit newLimit;
            if (this.section.isSet(worldName)) {
                newLimit = new LimitSection(section.getConfigurationSection(worldName));
            } else {
                newLimit = new LimitSection(section.getConfigurationSection("default"));
            }
            newLimit.apply(player);
        }
        player.setViewDistance(section.getInt("view"));
        player.setSendViewDistance(section.getInt("view"));
        player.setSimulationDistance(section.getInt("simulation"));
    }

    private boolean isPerWorld() {
        return this.section.getBoolean("per-world", false);
    }
}
