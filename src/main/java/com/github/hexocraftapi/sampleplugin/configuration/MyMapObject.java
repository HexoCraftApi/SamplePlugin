package com.github.hexocraftapi.sampleplugin.configuration;

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
import com.github.hexocraftapi.configuration.collection.ConfigurationMapObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class MyMapObject extends ConfigurationMapObject
{
																									private String name;
	@ConfigValue(path = "*.description", comment = "This is a comment in MyMapObject class.")		private String description;
	@ConfigValue(path = "*.value", comment = "This is a value in MyMapObject class.")				private long value;

	protected MyMapObject(JavaPlugin plugin)
	{
		super(plugin);
		this.name = null;
		this.description = null;
		this.value = 0;
	}

	public MyMapObject(JavaPlugin plugin, String name, String description, long value)
	{
		super(plugin);
		this.name = name;
		this.description = description;
		this.value = value;
	}



	@Override
	public String getName() { return name; }
	@Override
	public void setName(String name) { this.name = name; }
	public String getDescription()
	{
		return description;
	}
	public long getValue()
	{
		return value;
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
