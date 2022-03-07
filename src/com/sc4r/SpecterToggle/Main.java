package com.sc4r.SpecterToggle;

import java.util.HashMap;

public class Main extends JavaPlugin {
    public static Main instance;
    public HashMap<String, Toggle> cache = new HashMap<>();
    public HubConfig data = new HubConfig(this, "data.yml");

    @Override
    public void onEnable() {
        instance = this;
        getCommand("toggle").setExecutor(new ToggleCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new ToggleListeners(), this);
        data.getConfig().getConfigurationSection("Toggle").getKeys(false)
                .forEach(a -> cache.put(a,
                        new Toggle(a, data.getBoolean("Toggle." + a + ".Tell"),
                                data.getBoolean("Toggle." + a + ".Coins"), data.getBoolean("Toggle." + a + ".Tpa"),
                                data.getBoolean("Toggle." + a + ".Global"))));
    }

    @Override
    public void onDisable() {
        Toggle.getAll().forEach(a -> a.save());
    }

}
