package com.github.hexocraftapi.sampleplugin;

/*
 * Copyright 2016 hexosse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.predifined.message.PluginMessage;
import com.github.hexocraftapi.message.predifined.message.PluginTitleMessage;
import com.github.hexocraftapi.plugin.Plugin;
import com.github.hexocraftapi.sampleplugin.command.ItemCommands;
import com.github.hexocraftapi.sampleplugin.command.MessageCommands;
import com.github.hexocraftapi.sampleplugin.command.SamplePluginCommands;
import com.github.hexocraftapi.sampleplugin.configuration.Config;
import com.github.hexocraftapi.sampleplugin.listener.PlayerListener;
import com.github.hexocraftapi.updater.GitHubUpdater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.File;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class SamplePlugin extends Plugin
{
	public static SamplePlugin instance = null;

	public Config config = null;


	@Override
	public void onEnable()
	{
		/* Instance du plugin */
		instance = this;

		/* Chargement de la configuration */
		//(new File(this.getDataFolder(), "config.yml")).delete();
		config = new Config(this, "config.yml", true);
		//config.save();

		/* Enregistrement des commandes */
		registerCommands(new SamplePluginCommands(this));
		registerCommands(new MessageCommands(this));
		registerCommands(new ItemCommands(this));

        /* Enregistrement des listener */
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

		/* Enable message */
		PluginTitleMessage.toConsole(this, "Enable", ChatColor.YELLOW,
			new Line("This is a plugin to test my API.", ChatColor.GREEN),
			new Line("It will try to cover all the possibilities that offer my API", ChatColor.GREEN),
			new Line("© 2016 Hexosse", ChatColor.RED));

		/* Updater */
		if(config.useUpdater)
			runUpdater(getServer().getConsoleSender(), 20 * 40);

		/* Metrics */
		if(config.useMetrics)
			runMetrics( 20);
	}

	@Override
	public void onDisable()
	{
		super.onDisable();

		PluginMessage.toConsole(this, "Disabled", ChatColor.RED, new Line("SamplePlugin is now disabled", ChatColor.DARK_RED));
	}

	public void runUpdater(final CommandSender sender, int delay)
	{
		super.runUpdater(new GitHubUpdater(this, "HexoCraftApi/SamplePlugin"), sender, delay);
	}

	private void runMetrics(int delay)
	{
		super.RunMetrics(delay);
	}
}
