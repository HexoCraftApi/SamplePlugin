package com.github.hexocraftapi.sampleplugin.configuration;

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

import com.github.hexocraftapi.configuration.Configuration;
import com.github.hexocraftapi.configuration.annotation.ConfigFooter;
import com.github.hexocraftapi.configuration.annotation.ConfigHeader;
import com.github.hexocraftapi.configuration.annotation.ConfigPath;
import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
@ConfigHeader(comment = {
		"# ===--- TestPlugin ----------------------------------------------------------------------------------------------=== #",
		"#                                                                                                                      ",
		"# This is a plugin to test my brand new framework                                                                      ",
		"# It will try to cover all the possibilities that offer this framework                                                 ",
		"#                                                                                                                      ",
		"# ===------------------------------------------------------------------------------------------ © 2016 Hexosse ---=== #"
})
@ConfigFooter(comment = {
		" ",
		"# ===--- Enjoy -------------------------------------------------------------------------------- © 2016 Hexosse ---=== #"
})
public class Config extends Configuration
{
	/* Plugin */
	@ConfigPath(path = "plugin", comment = "Plugin options")
	@ConfigValue(path = "plugin.useMetrics", comment ="Allow metrics")		public boolean useMetrics = (boolean) true;
	@ConfigValue(path = "*.useUpdater", comment = "Auto update plugin")		public boolean useUpdater = (boolean) true;

	@ConfigValue(path = "test", comment = {"Test1", "Test2", "Test3"})		public ConfigMore configMore;

	@ConfigValue(path = "items")											public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	@ConfigValue(path = "items2")											public HashMap<String, ItemStack> items2 = new HashMap<String, ItemStack>();


	public Config(JavaPlugin plugin, String fileName, boolean load)
	{
		super(plugin, fileName);

		configMore = new ConfigMore(plugin);

		if(load) load();
	}
}
