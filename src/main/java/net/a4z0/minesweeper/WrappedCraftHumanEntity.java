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

import java.lang.reflect.InvocationTargetException;

public class WrappedCraftHumanEntity implements NMSObject {

    protected Object NMSObject;

    /**
    * ...
    */

    protected WrappedCraftHumanEntity() {
        this.NMSObject = null;
    }

    /**
    * ...
    *
    * @param Object ...
    */

    public WrappedCraftHumanEntity(Object Object) {
        if(Object == null)
            throw new IllegalArgumentException("Object can't be null");

        if(!Object.getClass().isAssignableFrom(WrappedClass.CRAFT_HUMAN_ENTITY.getNMSClass()))
            throw new IllegalArgumentException("Object isn't assignable from " + WrappedClass.CRAFT_HUMAN_ENTITY.getNMSClass().getSimpleName());

        this.NMSObject = Object;
    }

    @Override
    public Object getNMSObject() {
        return this.NMSObject;
    }

    /**
    * @return ...
    */

    public WrappedEntityHuman getHandle() {
        try {
            return (WrappedEntityHuman) WrappedClass.CRAFT_HUMAN_ENTITY.getNMSClass().getMethod("getHandle").invoke(this.NMSObject);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }
}