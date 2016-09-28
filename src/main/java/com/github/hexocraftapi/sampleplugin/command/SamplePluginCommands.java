package com.github.hexocraftapi.sampleplugin.command;

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

import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.predifined.CommandHelp;
import com.github.hexocraftapi.command.predifined.CommandReload;
import com.github.hexocraftapi.message.locale.Locale;
import com.github.hexocraftapi.message.predifined.message.PluginMessage;
import com.github.hexocraftapi.sampleplugin.SamplePlugin;
import com.github.hexocraftapi.sampleplugin.configuration.Config;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class SamplePluginCommands extends Command<SamplePlugin>
{
	public SamplePluginCommands(SamplePlugin plugin)
	{
		super("SamplePlugin", plugin);

		this.addSubCommand(new SamplePluginCommandReload(plugin));
		this.addSubCommand(new SamplePluginCommandHelp(plugin));
	}

	@Override
	public boolean onCommand(CommandInfo commandInfo)
	{
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "SamplePlugin help");

		return true;
	}




	public class SamplePluginCommandReload extends CommandReload<SamplePlugin>
	{
		public SamplePluginCommandReload(SamplePlugin plugin)
		{
			super(plugin, "");
			this.setAliases(Lists.newArrayList("r"));
			this.setDescription(StringUtils.join(plugin.config.cReload,"\n"));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			final Player player = commandInfo.getPlayer();

			super.onCommand(commandInfo);

			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					// reload the config file
					plugin.config = new Config(getPlugin(), "config.yml", true);

					//plugin.messages = new Messages(plugin, plugin.getDataFolder(), plugin.config.messages);

					// Message
					String text = Locale.FR_fr?"SamplePlugin recharge":"SamplePlugin reloaded";
					new PluginMessage(plugin, text, ChatColor.RED).send(new CommandSender[]{player, getPlugin().getServer().getConsoleSender()});
				}

			}.runTask(plugin);

			return true;
		}
	}




	public class SamplePluginCommandHelp extends CommandHelp<SamplePlugin>
	{
		public SamplePluginCommandHelp(SamplePlugin plugin)
		{
			super(plugin);
		}
	}
}
