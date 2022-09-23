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

public class WrappedArmorStand extends WrappedEntityLiving {

    protected final Object NMSObject;

    /**
    * ...
    *
    * @param World ...
    * @param X ...
    * @param Y ...
    * @param Z ...
    */

    public WrappedArmorStand(World World, double X, double Y, double Z) {
        if(World == null)
            throw new IllegalArgumentException("WrappedWorld can't be null");

        try {
            this.NMSObject = WrappedClass.ENTITY_ARMOR_STAND.getNMSClass().getConstructor(WrappedClass.WORLD.getNMSClass(), double.class, double.class, double.class).newInstance((new WrappedCraftWorld(World)).getHandle().getNMSObject(), X, Y, Z);
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't construct a " + this.getClass().getSimpleName());
        }
    }

    @Override
    public Object getNMSObject() {
        return this.NMSObject;
    }

    /**
    * ...
    *
    * @param Marker ...
    */

    public void setMarker(boolean Marker) {
        try {
            if(Version.V1_18_R1.N(Version.V)) {
                WrappedClass.ENTITY_ARMOR_STAND.getNMSClass().getMethod("t", boolean.class).invoke(this.getNMSObject(), Marker);
            }else if(Version.V1_9_R1.N(Version.V)) {
                WrappedClass.ENTITY_ARMOR_STAND.getNMSClass().getMethod("setMarker", boolean.class).invoke(this.getNMSObject(), Marker);
            }else{
                WrappedClass.ENTITY_ARMOR_STAND.getNMSClass().getMethod("n", boolean.class).invoke(this.getNMSObject(), Marker);
            }
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }
}