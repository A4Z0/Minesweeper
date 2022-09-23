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

public abstract class WrappedEntity implements NMSObject {

    /**
    * @return ...
    */

    public int getEntityID() {
        try {
            return (int) WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_16_R1.N(Version.V) ? "ae" : "getId").invoke(this.getNMSObject());
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }

    /**
    * @return ...
    */

    public WrappedDataWatcher getEntityData() {
        try {
            return new WrappedDataWatcher(WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_16_R1.N(Version.V) ? "ai" : "getDataWatcher").invoke(this.getNMSObject()));
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }

    /**
    * ...
    *
    * @param Name ...
    */

    public void setCustomName(String Name) {
        if(Name == null)
            throw new IllegalArgumentException("Name can't be null");

        try {
            WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_18_R1.N(Version.V) ? "a" : "setCustomName", String.class).invoke(this.getNMSObject(), Name);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }

    /**
    * ...
    *
    * @param WrappedIChatBaseComponent ...
    */

    @Since(Version.V1_14_R1)
    public void setCustomName(WrappedIChatBaseComponent WrappedIChatBaseComponent) {
        try {
            WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_18_R1.N(Version.V) ? "b" : "setCustomName", WrappedClass.I_CHAT_BASE_COMPONENT.getNMSClass()).invoke(this.getNMSObject(), WrappedIChatBaseComponent.getNMSObject());
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }

    /**
    * ...
    *
    * @param Visible ...
    */

    public void setCustomNameVisible(boolean Visible) {
        try {
            WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_16_R1.N(Version.V) ? "n" : "setCustomNameVisible", boolean.class).invoke(this.getNMSObject(), Visible);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }

    /**
    * ...
    *
    * @param Invisible ...
    */

    public void setInvisible(boolean Invisible) {
        try {
            WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_16_R1.N(Version.V) ? "j" : "setInvisible", boolean.class).invoke(this.getNMSObject(), Invisible);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    */

    public void setPosition(double X, double Y, double Z) {
        try {
            WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_16_R1.N(Version.V) ? "g" : "setPosition", double.class, double.class, double.class).invoke(this.getNMSObject(), X, Y, Z);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Yaw ...
    * @param Pitch ...
    */

    public void setLocation(double X, double Y, double Z, float Yaw, float Pitch) {
        try {
            WrappedClass.ENTITY.getNMSClass().getMethod(Version.V1_16_R1.N(Version.V) ? "a" : "setLocation", double.class, double.class, double.class, float.class, float.class).invoke(this.getNMSObject(), X, Y, Z, Yaw, Pitch);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + this.getClass().getSimpleName());
        }
    }
}