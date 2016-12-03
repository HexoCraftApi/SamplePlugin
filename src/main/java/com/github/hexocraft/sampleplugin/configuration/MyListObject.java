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
import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import com.github.hexocraftapi.configuration.collection.ConfigurationList;
import com.github.hexocraftapi.configuration.collection.ConfigurationObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class MyListObject extends ConfigurationObject
{
	@ConfigValue(path = "*.name", comment = "This is the name in MyListObject class.")				private String name;
	@ConfigValue(path = "*.description", comment = "This is a comment in MyListObject class.")		private String description;
	@ConfigValue(path = "*.value", comment = "")													private long value;

	@ConfigValue(path = "*.list")																	private ConfigurationList<MyListObject> list = null;

	protected MyListObject(JavaPlugin plugin)
	{
		super(plugin);
		this.name = null;
		this.description = null;
		this.value = 0;
	}

	public MyListObject(JavaPlugin plugin, String name, String description, long value)
	{
		super(plugin);
		this.name = name;
		this.description = description;
		this.value = value;
	}

	public String getName() { return name; }
	public String getDescription()
	{
		return description;
	}
	public long getValue()
	{
		return value;
	}

	void add(MyListObject obj)
	{
		if(list == null)
			list = new ConfigurationList<>();

		list.add(obj);
	}

	@Override
	public Object serialize(Configuration configuration, Object object)
	{
		return super.serialize(configuration, object);
	}

	@Override
	public Object deserialize(Configuration configuration, Class<?> oClass, Class<?>[] pClass, Object object)
	throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		return super.deserialize(configuration, oClass, pClass, object);
	}
}
