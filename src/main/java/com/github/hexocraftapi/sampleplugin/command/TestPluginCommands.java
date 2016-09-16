package com.github.hexocraftapi.sampleplugin.command;

/*
 * Copyright 2015 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.predifined.CommandHelp;
import com.github.hexocraftapi.command.predifined.CommandReload;
import com.github.hexocraftapi.message.locale.Locale;
import com.github.hexocraftapi.message.predifined.message.PluginMessage;
import com.github.hexocraftapi.sampleplugin.TestPlugin;
import com.github.hexocraftapi.sampleplugin.configuration.Config;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class TestPluginCommands extends Command<TestPlugin>
{
	public TestPluginCommands(TestPlugin plugin)
	{
		super("TestPlugin", plugin);

		this.addSubCommand(new TestPluginCommandReload(plugin));
		this.addSubCommand(new TestPluginCommandHelp(plugin));
	}

	@Override
	public boolean onCommand(CommandInfo commandInfo)
	{
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "TestPlugin help");

		return true;
	}




	public class TestPluginCommandReload extends CommandReload<TestPlugin>
	{
		public TestPluginCommandReload(TestPlugin plugin)
		{
			super(plugin, "");
			this.setAliases(Lists.newArrayList("r"));
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
					String text = Locale.FR_fr?"TestPlugin recharge":"TestPlugin reloaded";
					new PluginMessage(plugin, text, ChatColor.RED).send(new CommandSender[]{player, getPlugin().getServer().getConsoleSender()});
				}

			}.runTask(plugin);

			return true;
		}
	}




	public class TestPluginCommandHelp extends CommandHelp<TestPlugin>
	{
		public TestPluginCommandHelp(TestPlugin plugin)
		{
			super(plugin);
		}
	}
}
