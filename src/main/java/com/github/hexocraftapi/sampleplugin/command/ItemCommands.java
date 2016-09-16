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

import com.github.hexocraftapi.command.predifined.CommandHelp;
import com.github.hexocraftapi.sampleplugin.TestPlugin;
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.type.ArgTypeInteger;
import com.github.hexocraftapi.command.type.ArgTypeString;
import com.google.common.collect.Lists;
import org.bukkit.inventory.ItemStack;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class ItemCommands extends Command<TestPlugin>
{
	public ItemCommands(TestPlugin plugin)
	{
		super("Item", plugin);
		this.setAliases(Lists.newArrayList("si"));

		this.addSubCommand(new ItemCommandSave(plugin));
		this.addSubCommand(new ItemCommandLoad(plugin));
		this.addSubCommand(new ItemCommandSaveName(plugin));
		this.addSubCommand(new ItemCommandLoadName(plugin));
		this.addSubCommand(new ItemCommandHelp(plugin));
	}

	@Override
	public boolean onCommand(CommandInfo commandInfo)
	{
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "Item help");

		return true;
	}




	public class ItemCommandSave extends Command<TestPlugin>
	{
		public ItemCommandSave(TestPlugin plugin)
		{
			super("save", plugin);
			this.setAliases(Lists.newArrayList("s"));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			ItemStack itemStack = commandInfo.getPlayer().getItemInHand();

			TestPlugin.instance.config.items.add(itemStack);
			TestPlugin.instance.config.save();

			return true;
		}
	}




	public class ItemCommandLoad extends Command<TestPlugin>
	{
		public ItemCommandLoad(TestPlugin plugin)
		{
			super("load", plugin);
			this.setAliases(Lists.newArrayList("l"));

			this.addArgument(new CommandArgument<Integer>("index", ArgTypeInteger.get(), true, true));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			Integer index = Integer.parseInt(commandInfo.getNamedArg("index"));

			if(index >= 0 && index < TestPlugin.instance.config.items.size())
			{
				ItemStack itemStack = TestPlugin.instance.config.items.get(index);
				commandInfo.getPlayer().getWorld().dropItem(commandInfo.getPlayer().getLocation(), itemStack);
			}

			return true;
		}
	}




	public class ItemCommandSaveName extends Command<TestPlugin>
	{
		public ItemCommandSaveName(TestPlugin plugin)
		{
			super("saveName", plugin);
			this.setAliases(Lists.newArrayList("sn"));

			this.addArgument(new CommandArgument<String>("name", ArgTypeString.get(), true, true));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			String name = commandInfo.getNamedArg("name");

			ItemStack itemStack = commandInfo.getPlayer().getItemInHand();

			TestPlugin.instance.config.items2.put(name,itemStack);
			TestPlugin.instance.config.save();

			return true;
		}
	}




	public class ItemCommandLoadName extends Command<TestPlugin>
	{
		public ItemCommandLoadName(TestPlugin plugin)
		{
			super("loadName", plugin);
			this.setAliases(Lists.newArrayList("ln"));

			this.addArgument(new CommandArgument<String>("name", ArgTypeString.get(), true, true));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			String name = commandInfo.getNamedArg("name");

			ItemStack itemStack = TestPlugin.instance.config.items2.get(name);
			commandInfo.getPlayer().getWorld().dropItem(commandInfo.getPlayer().getLocation(), itemStack);

			return true;
		}
	}




	public class ItemCommandHelp extends CommandHelp<TestPlugin>
	{
		public ItemCommandHelp(TestPlugin plugin)
		{
			super(plugin);
		}
	}
}
