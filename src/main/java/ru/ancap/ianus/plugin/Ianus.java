package ru.ancap.ianus.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Ianus extends JavaPlugin {

    @Override
    public void onEnable() {
        this.loadConfig();
        this.loadOculus();
    }

    private void loadOculus() {
        new Oculus(
                this.getConfig().getConfigurationSection("view-settings")
        ).register(this);
    }

    private void loadConfig() {
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
