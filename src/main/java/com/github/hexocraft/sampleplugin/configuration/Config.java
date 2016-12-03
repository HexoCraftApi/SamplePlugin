package com.github.hexocraft.sampleplugin.configuration;

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

import com.github.hexocraftapi.configuration.Configuration;
import com.github.hexocraftapi.configuration.annotation.ConfigFooter;
import com.github.hexocraftapi.configuration.annotation.ConfigHeader;
import com.github.hexocraftapi.configuration.annotation.ConfigPath;
import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import com.github.hexocraftapi.configuration.collection.ConfigurationList;
import com.github.hexocraftapi.configuration.collection.ConfigurationMap;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
@ConfigHeader(comment = {
		"# ===--- SamplePlugin ----------------------------------------------------------------------------------------------=== #",
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
	@ConfigValue(path = "plugin.useMetrics", comment ="Enable Metrics")			public boolean useMetrics = (boolean) true;
	@ConfigValue(path = "*.useUpdater", comment = "Enable Updater")				public boolean useUpdater = (boolean) true;

	@ConfigValue(path = "test", comment = {"This is a test of nested config class", "Test2", "Test3"})
																				public ConfigMore configMore = null;

	@ConfigValue(path = "items", comment = "List of items used with the /item command")
																				public ArrayList<ItemStack> items = new ArrayList<ItemStack>();


	@ConfigValue(path = "items2", comment = "An other list of items just fot testing purpose")
																				public HashMap<String, ItemStack> items2 = new HashMap<String, ItemStack>();

	@ConfigPath(path = "messages", 		comment = "List of messages used in commands")
	@ConfigValue(path = "commands.reload")										public List<String> cReload = Arrays.asList("Reload SoundEffect");
	@ConfigValue(path = "commands.test")										public String cTest = "This is a test";

	@ConfigValue(path = "object", comment = "A object of type MyMapObject")		public MyObject							object     = null;
	@ConfigValue(path = "objects", comment = "A list of MyMapObject")			public ConfigurationMap<MyMapObject>   objects     = null;
	@ConfigValue(path = "MyObjectList", comment = "A list of MyListObject")		public ConfigurationList<MyListObject> objectsList = null;
	@ConfigValue(path = "MyList2", comment = "A list of MyListObject")			public ConfigurationList<MyListObject> list2 = null;

	public Config(JavaPlugin plugin, String fileName, boolean load)
	{
		super(plugin, fileName);

		configMore = new ConfigMore(plugin);

		object = new MyObject(plugin, "SimpleObject", "this is a simple object", 0);

		objects = new ConfigurationMap<MyMapObject>(4);
		MyMapObject obj1 = new MyMapObject(plugin, "Obj1", "this is obj1", 1);
		MyMapObject obj2 = new MyMapObject(plugin, "Obj2", "this is obj2", 2);
		MyMapObject obj3 = new MyMapObject(plugin, "Obj3", "this is obj3", 3);
		MyMapObject obj4 = new MyMapObject(plugin, "Obj4", "this is obj4", 4);
		objects.put(obj1.getName(), obj1);
		objects.put(obj1.getName(), obj1);
		objects.put(obj2.getName(), obj2);
		objects.put(obj3.getName(), obj3);
		objects.put(obj4.getName(), obj4);

		objectsList = new ConfigurationList<MyListObject>(4);
		MyListObject obj5 = new MyListObject(plugin, "obj5", "this is obj5", 1);
		MyListObject obj6 = new MyListObject(plugin, "obj6", "this is obj6", 2);
		MyListObject obj7 = new MyListObject(plugin, "obj7", "this is obj7", 3);
		MyListObject obj8 = new MyListObject(plugin, "obj8", "this is obj8", 4);
		objectsList.add(obj5);
		objectsList.add(obj6);
		objectsList.add(obj7);
		objectsList.add(obj8);

		list2 = new ConfigurationList<MyListObject>();
		MyListObject main = new MyListObject(plugin, "Main", "this is Main", 0);
		MyListObject child1 = new MyListObject(plugin, "child1", "this is child1", 1);
		MyListObject child2 = new MyListObject(plugin, "child2", "this is child2", 2);
		MyListObject child3 = new MyListObject(plugin, "child3", "this is child3", 3);
		list2.add(main);
		main.add(child1);
		main.add(child2);
		main.add(child3);

		if(load) load();

		configMore = new ConfigMore(plugin);
		configMore.bool = false;

		save();
	}
}
