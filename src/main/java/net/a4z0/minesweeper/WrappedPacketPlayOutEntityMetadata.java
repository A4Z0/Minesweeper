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

public class WrappedPacketPlayOutEntityMetadata extends WrappedPacket {

    /**
    * ...
    *
    * @param ID ...
    * @param WrappedDataWatcher ...
    * @param Update ...
    */

    public WrappedPacketPlayOutEntityMetadata(int ID, WrappedDataWatcher WrappedDataWatcher, boolean Update) {
        try {
            this.NMSObject = WrappedClass.PACKET_PLAY_OUT_ENTITY_METADATA.getNMSClass().getConstructor(int.class, WrappedClass.DATA_WATCHER.getNMSClass(), boolean.class).newInstance(ID, WrappedDataWatcher.getNMSObject(), Update);
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't construct a " + this.getClass().getSimpleName());
        }
    }
}