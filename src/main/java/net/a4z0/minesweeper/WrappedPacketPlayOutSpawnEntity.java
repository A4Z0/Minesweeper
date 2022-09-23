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

public class WrappedPacketPlayOutSpawnEntity extends WrappedPacket {

    /**
    * ...
    *
    * @param WrappedEntity ...
    * @param Data ...
    */

    public WrappedPacketPlayOutSpawnEntity(WrappedEntity WrappedEntity, int Data) {
        try {
            this.NMSObject = WrappedClass.PACKET_PLAY_OUT_SPAWN_ENTITY.getNMSClass().getConstructor(WrappedClass.ENTITY.getNMSClass(), int.class).newInstance(WrappedEntity.getNMSObject(), Data);
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't construct a " + this.getClass().getSimpleName());
        }
    }

    /**
    * ...
    *
    * @param WrappedEntityLiving ...
    */

    @Since(Version.V1_19_R1)
    public WrappedPacketPlayOutSpawnEntity(WrappedEntityLiving WrappedEntityLiving) {
        try {
            this.NMSObject = WrappedClass.PACKET_PLAY_OUT_SPAWN_ENTITY.getNMSClass().getConstructor(WrappedClass.ENTITY_LIVING.getNMSClass()).newInstance(WrappedEntityLiving.getNMSObject());
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't construct a " + this.getClass().getSimpleName());
        }
    }
}