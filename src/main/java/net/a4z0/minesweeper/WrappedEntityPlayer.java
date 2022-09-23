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

public class WrappedEntityPlayer extends WrappedEntityHuman {

    protected final Object NMSObject;

    public final WrappedPlayerConnection C;

    /**
    * ...
    *
    * @param Object ...
    */

    public WrappedEntityPlayer(Object Object) {
        if(Object == null)
            throw new IllegalArgumentException("Object can't be null");

        if(!Object.getClass().isAssignableFrom(WrappedClass.ENTITY_PLAYER.getNMSClass()))
            throw new IllegalArgumentException("Object isn't assignable from " + WrappedClass.ENTITY_PLAYER.getNMSClass().getSimpleName());

        this.NMSObject = Object;

        try {
            this.C = new WrappedPlayerConnection(WrappedClass.ENTITY_PLAYER.getNMSClass().getField(Version.V1_16_R1.N(Version.V) ? "b" : "playerConnection").get(this.NMSObject));
        }catch (NoSuchFieldException | IllegalAccessException e) {
            throw new NullPointerException("Couldn't construct a " + this.getClass().getSimpleName());
        }
    }

    @Override
    public Object getNMSObject() {
        return this.NMSObject;
    }
}