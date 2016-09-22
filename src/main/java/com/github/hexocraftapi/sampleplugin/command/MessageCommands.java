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
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.predifined.CommandHelp;
import com.github.hexocraftapi.command.type.ArgTypeInteger;
import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.Util.FontUtil;
import com.github.hexocraftapi.message.predifined.message.*;
import com.github.hexocraftapi.message.predifined.prefix.colored.PrefixAqua;
import com.github.hexocraftapi.message.predifined.prefix.colored.PrefixFire;
import com.github.hexocraftapi.sampleplugin.SamplePlugin;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class MessageCommands extends Command<SamplePlugin>
{
	public MessageCommands(SamplePlugin plugin)
	{
		super("message", plugin);
		this.setAliases(Lists.newArrayList("m", "mess"));

		this.addSubCommand(new MessageCommandSend(plugin));
		this.addSubCommand(new MessageCommandHelp(plugin));
	}

	@Override
	public boolean onCommand(CommandInfo commandInfo)
	{
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "message help");

		return true;
	}




	public class MessageCommandHelp extends CommandHelp<SamplePlugin>
	{
		public MessageCommandHelp(SamplePlugin plugin)
		{
			super(plugin);
		}
	}




	public class MessageCommandSend extends Command<SamplePlugin>
	{
		public MessageCommandSend(SamplePlugin plugin)
		{
			super("send", plugin);
			this.setAliases(Lists.newArrayList("s"));

			this.addArgument(new CommandArgument<Integer>("index", ArgTypeInteger.get(), true, true));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			Integer index = Integer.parseInt(commandInfo.getNamedArg("index"));

			switch(index)
			{
				case 1:
				{
					PluginTitleMessage.toPlayer(commandInfo.getPlayer(), getPlugin(), "Enable", ChatColor.YELLOW,
						new Line(""),
						new Line("This is a plugin to test my brand new framework.", ChatColor.GREEN),
						new Line("It will try to cover all the possibilities that offer this framework.", ChatColor.GREEN),
						new Line(""),
						new Line("You can " + ChatColor.RED + ChatColor.BOLD + "create" + ChatColor.GREEN + " your own type of message, with different style. Customize the prefix shown at the beginning of " + ChatColor.RED + ChatColor.BOLD + "each" + ChatColor.GREEN + " line or just change the color in a simple message", ChatColor.GREEN),
						new Line(""),
						new Line("© 2016 Hexosse", ChatColor.RED));
				} break;

				case 2:
				{
					PluginMessage.toPlayer(commandInfo.getPlayer(), getPlugin(), "Enable", ChatColor.YELLOW,
						new Line("This is a message using PluginMessage", ChatColor.GREEN),
						new Line("You can add as much line as you want", ChatColor.GREEN),
						new Line("Each line will contain the plugin prefix", ChatColor.YELLOW),
						new Line("© 2016 Hexosse", ChatColor.RED));
				} break;

				case 3:
				{
					PrefixFire prefix = new PrefixFire("Fire");
					PrefixAqua aqua = new PrefixAqua("Aqua");

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					WarningMessage.toPlayer(commandInfo.getPlayer(), "This is a WarningPrefixedMessage.");

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					WarningPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, "This is a WarningPrefixedMessage.");

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					ErrorMessage.toPlayer(commandInfo.getPlayer(), "This is an ErrorMessage.");

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, "This is an ErrorPrefixedMessage.");

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), aqua, "This is an other ErrorPrefixedMessage.");

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					SimpleMessage.toPlayer(commandInfo.getPlayer(), "This is an SimpleMessage.");

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					SimpleMessage.toPlayer(commandInfo.getPlayer(), "This is an SimpleMessage with color.", ChatColor.LIGHT_PURPLE);

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					SimplePrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, "This is an SimplePrefixedMessage.", ChatColor.LIGHT_PURPLE);

					EmptyMessage.toPlayer(commandInfo.getPlayer());
					SimplePrefixedMessage.toPlayer(commandInfo.getPlayer(), aqua, "This is an SimplePrefixedMessage with color.", ChatColor.GREEN);
				} break;

				case 4:
				{
					commandInfo.getPlayer().sendMessage(FontUtil.center("" + ChatColor.GREEN + ChatColor.BOLD + "This is a plugin to test my brand new framework."));

					commandInfo.getPlayer().sendMessage("");

					commandInfo.getPlayer().sendMessage(FontUtil.center("" + ChatColor.GREEN + "This is a plugin to test my brand new framework."));
				} break;

				default: break;
			}

			return true;
		}
	}
}
