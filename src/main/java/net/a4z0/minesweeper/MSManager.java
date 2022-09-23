package net.a4z0.minesweeper;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class MSManager {

    private Configuration Configuration;

    /**
    * @return ...
    */

    public MSManager.Configuration getConfiguration() {
        return this.Configuration;
    }

    /**
    * ...
    */

    public void reload() throws IOException, InvalidConfigurationException, ClassNotFoundException {
        File File = new File(
                Minesweeper.getInstance().getDataFolder(),
                "Configuration.yml"
        );

        if(!File.exists())
            FileUtils.copyInputStreamToFile(Minesweeper.getInstance().getResource("Configuration.yml"), File);

        YamlConfiguration Configuration = new YamlConfiguration();
        Configuration.load(File);

        if(Configuration.get("Chance") == null)
            throw new IllegalArgumentException("Chance in Configuration.yml can't be null");

        this.Configuration = () -> Configuration.getInt("Chance");
    }

    /**
    * {@link Minesweeper}'s Configuration.
    */

    public interface Configuration {

        /**
        * @return ...
        */

        int getChance();
    }
}
