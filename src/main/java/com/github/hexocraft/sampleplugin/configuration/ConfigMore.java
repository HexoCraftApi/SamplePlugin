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
import com.github.hexocraftapi.configuration.annotation.DelegateSerialization;
import com.github.hexocraftapi.configuration.collection.ConfigurationObject;
import com.github.hexocraftapi.sounds.Sounds;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class ConfigMore extends ConfigurationObject
{
	@ConfigValue(path = "*.bool", comment = "This is a boolean value")
	public boolean bool = true;

	@ConfigValue(path = "*.sound", comment = {"This is a sound","This sound can be from 1.7.10 to 1.10.2","it won't crash if you use a 1.10 sound on 1.7.10 server"})
	public Sound sound = Sounds.get("ENTITY_GHAST_SCREAM");

	@ConfigValue(path = "*.volume", comment = "Volume")
	public float volume = 0.8f;

	@ConfigValue(path = "*.internal", comment = {"Again a nested class.","However, this class does NOT inherit from Configuration so it's up to ConfigMore to serialize and deserialize the data."})
	public Internal internal = new Internal();

	@ConfigValue(path = "*.internalConf1", comment = {"Again a nested class.","However, this class INHERIT from Configuration and then handle the serialize and deserialize method."})
	public InternalConf1 internalConf1 = null;

	public ConfigMore(JavaPlugin plugin)
	{
		super(plugin);
		internalConf1 = new InternalConf1(plugin);
	}

	@Override
	public Object serialize(Configuration configuration, Object object)
	{
		// Serialize an object of class Internal
		// Internal do NOT inherit from Configuration
		if(object instanceof Internal)
		{
			final Internal internal = (Internal)object;

			final JSONObject json = new JSONObject();
			json.put("message", internal.message);
			json.put("aBoolean", internal.aBoolean);
			return json.toJSONString();
		}

		// Deserialize an object of class InternalConf1 which INHERIT of Configuration
		else if(InternalConf1.class.isAssignableFrom(configuration.getClass()))
		{
			return super.serialize(configuration, object);
		}

		else
			return super.serialize(configuration, object);
	}

	@Override
	public Object deserialize(Configuration configuration, Class<?> oClass, final Class<?>[] pClass, Object object) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		// Deserialize an object of class Internal
		// Internal do NOT inherit from Configuration
		if(Internal.class.isAssignableFrom(oClass))
		{
			String json = object.toString();
			final JSONObject array = (JSONObject) JSONValue.parse(json);

			internal.message = (String) array.get("message");
			internal.aBoolean = (Boolean) array.get("aBoolean");

			return internal;
		}

		// Deserialize an object of class InternalConf1 which INHERIT of Configuration
		else if(InternalConf1.class.isAssignableFrom(configuration.getClass()))
		{
			return super.deserialize(configuration, oClass, pClass, object);
		}

		else
			return super.deserialize(configuration, oClass, pClass, object);
	}



	//
	final public class Internal
	{
		private String message= "Internal message";
		private boolean aBoolean = false;

		// Constructor
		public Internal() { super(); }

		// Accessors
		public String getMessage() { return this.message == null ? "" : this.message; }
	}



	//
	final public class InternalConf1 extends Configuration
	{
		@DelegateSerialization(serialize = ConfigMore.class, deserialize = ConfigMore.class)
		@ConfigValue(path = "*.string", comment = "This is a string")	private String message = "InternalConf1 message";

		@DelegateSerialization(serialize = ConfigMore.class, deserialize = ConfigMore.class)
		@ConfigValue(path = "*.bool", comment = "This is a boolean")	private boolean aBoolean = false;

		// Constructor
		public InternalConf1(JavaPlugin plugin) { super(plugin, ""); }

		// Accessors
		public String getMessage() { return this.message == null ? "" : this.message; }

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
}
