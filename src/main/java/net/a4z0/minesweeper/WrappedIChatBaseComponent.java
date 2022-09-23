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

public interface WrappedIChatBaseComponent extends NMSObject {

    /**
    * @param Text ...
    *
    * @return ...
    */

    @Since(Version.V1_17_R1)
    static WrappedIChatBaseComponent a(String Text) {
        try {
            Object IChatBaseComponent = WrappedClass.I_CHAT_BASE_COMPONENT.getNMSClass().getDeclaredMethod("a", String.class).invoke(WrappedClass.I_CHAT_BASE_COMPONENT, Text);

            return () -> IChatBaseComponent;
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + WrappedIChatBaseComponent.class.getSimpleName());
        }
    }

    class WrappedChatSerializer implements NMSObject {

        protected final Object NMSObject;

        {
            try {
                this.NMSObject = WrappedClass.I_CHAT_BASE_COMPONENT$CHAT_SERIALIZER.getNMSClass().getConstructor().newInstance();
            }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new NullPointerException("Couldn't construct a " + this.getClass().getSimpleName());
            }
        }

        @Override
        public Object getNMSObject() {
            return this.NMSObject;
        }

        /**
        * @param Text ...
        *
        * @return ...
        */

        @Until(Version.V1_16_R3)
        public static WrappedIChatBaseComponent a(String Text) {
            try {
                Object IChatBaseComponent = WrappedClass.I_CHAT_BASE_COMPONENT$CHAT_SERIALIZER.getNMSClass().getDeclaredMethod("a", String.class).invoke(WrappedClass.I_CHAT_BASE_COMPONENT$CHAT_SERIALIZER, Text);

                return () -> IChatBaseComponent;
            }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new NullPointerException("Couldn't find method of " + WrappedChatSerializer.class.getSimpleName());
            }
        }
    }
}