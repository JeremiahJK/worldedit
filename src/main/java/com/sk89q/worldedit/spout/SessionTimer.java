/*
 * WorldEdit
 * Copyright (C) 2012 sk89q <http://www.sk89q.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

// $Id$


package com.sk89q.worldedit.spout;

import com.sk89q.worldedit.SessionCheck;
import com.sk89q.worldedit.WorldEdit;
import org.spout.api.Engine;
import org.spout.api.player.Player;

/**
 * Used to remove expired sessions in Bukkit.
 *
 * @author sk89q
 */
public class SessionTimer implements Runnable {

    private WorldEdit worldEdit;
    private SessionCheck checker;

    public SessionTimer(WorldEdit worldEdit, final Engine game) {
        this.worldEdit = worldEdit;
        this.checker = new SessionCheck() {
            public boolean isOnlinePlayer(String name) {
                Player player = game.getPlayer(name, true);
                return player != null && player.isOnline();
            }
        };
    }

    public void run() {
        worldEdit.flushExpiredSessions(checker);
    }

}
