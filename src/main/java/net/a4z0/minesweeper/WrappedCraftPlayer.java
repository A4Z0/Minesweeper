/*
 *     Minesweeper
 *     Copyright (C) 2022.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.a4z0.minesweeper;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class WrappedCraftPlayer extends WrappedCraftHumanEntity {

    /**
    * ...
    *
    * @param Player ...
    */

    public WrappedCraftPlayer(Player Player) {
        this.NMSObject = WrappedClass.CRAFT_PLAYER.getNMSClass().cast(Player);
    }

    @Override
    public WrappedEntityPlayer getHandle() {
        try {
            return new WrappedEntityPlayer(WrappedClass.CRAFT_PLAYER.getNMSClass().getMethod("getHandle").invoke(this.NMSObject));
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }
}
