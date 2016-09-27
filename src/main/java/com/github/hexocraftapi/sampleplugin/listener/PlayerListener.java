package com.github.hexocraftapi.sampleplugin.listener;

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

import com.github.hexocraftapi.message.predifined.message.SimpleMessage;
import com.github.hexocraftapi.reflection.minecraft.Minecraft;
import com.github.hexocraftapi.sampleplugin.SamplePlugin;
import com.github.hexocraftapi.sounds.PlaySounds;
import com.github.hexocraftapi.sounds.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class PlayerListener implements Listener
{
	// Play the event sound when player is joining and event is running
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent joinEvent)
	{
		Player player = joinEvent.getPlayer();

		// Welcome message
		new SimpleMessage("").send(player);
		new SimpleMessage("Welcome on board. This is SamplePlugin", ChatColor.GOLD).send(player);
		new SimpleMessage("").send(player);

		// Check for update
		if(player.isOp())
			SamplePlugin.instance.runUpdater(player, 20);

		// Play a sound using Sounds library
		System.out.println(Minecraft.Version.getVersion().toString());
		Sound cave1 = Sounds.get("AMBIENT_CAVE");
		PlaySounds.broadcast(SamplePlugin.instance, 20 * 3, cave1, 1, 0.8f);
	}

	// Play the event sound when player is joining and event is running
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent moveEvent)
	{
	}
}
