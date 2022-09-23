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

import org.bukkit.World;

import java.lang.reflect.InvocationTargetException;

public class WrappedCraftWorld implements NMSObject {

    protected final Object NMSObject;

    /**
    * ...
    *
    * @param World ...
    */

    public WrappedCraftWorld(World World) {
        if(World == null)
            throw new IllegalArgumentException("WrappedWorld can't be null");

        this.NMSObject = WrappedClass.CRAFT_WORLD.getNMSClass().cast(World);
    }

    @Override
    public Object getNMSObject() {
        return this.NMSObject;
    }

    /**
    * @return ...
    */

    public WrappedWorldServer getHandle() {
        try {
            return new WrappedWorldServer(WrappedClass.CRAFT_WORLD.getNMSClass().getMethod("getHandle").invoke(this.NMSObject));
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }
}