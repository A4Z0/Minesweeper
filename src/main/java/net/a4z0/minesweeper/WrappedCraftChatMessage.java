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
import java.util.Arrays;

public class WrappedCraftChatMessage {

    /**
    * @param Message ...
    *
    * @return ...
    */

    @Since(Version.V1_14_R1)
    public static WrappedIChatBaseComponent fromStringOrNull(String Message) {
        try {
            Object IChatBaseComponent = WrappedClass.CRAFT_CHAT_MESSAGE.getNMSClass().getDeclaredMethod("fromStringOrNull", String.class).invoke(WrappedClass.CRAFT_CHAT_MESSAGE, Message);

            return () -> IChatBaseComponent;
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + WrappedCraftChatMessage.class.getSimpleName());
        }
    }

    /**
    * @param Message ...
    *
    * @return ...
    */

    public static WrappedIChatBaseComponent[] fromString(String Message) {
        return WrappedCraftChatMessage.fromString(Message, false);
    }

    /**
    * @param Message ...
    * @param Keep ...
    *
    * @return ...
    */

    public static WrappedIChatBaseComponent[] fromString(String Message, boolean Keep) {
        try {
            return Arrays.stream((Object[]) WrappedClass.CRAFT_CHAT_MESSAGE.getNMSClass().getDeclaredMethod("fromString", String.class, boolean.class).invoke(WrappedClass.CRAFT_CHAT_MESSAGE, Message, Keep)).map((IChatBaseComponent) -> (WrappedIChatBaseComponent) () -> IChatBaseComponent).toArray(WrappedIChatBaseComponent[]::new);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Couldn't find method of " + WrappedCraftChatMessage.class.getSimpleName());
        }
    }
}