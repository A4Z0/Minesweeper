package net.a4z0.minesweeper;

import org.bukkit.Bukkit;

import java.lang.reflect.AccessibleObject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public enum Version {
    UNKNOWN,

    V1_8_R3,

    V1_9_R1,
    V1_9_R2,
    V1_10_R1,
    V1_11_R1,
    V1_12_R1,
    V1_13_R1,
    V1_13_R2,
    V1_14_R1,
    V1_15_R1,

    V1_16_R1,
    V1_16_R2,
    V1_16_R3,
    V1_17_R1,
    V1_18_R1,
    V1_18_R2,
    V1_19_R1;

    /**
    * @param Version ...
    *
    * @return ...
    */

    public boolean N(Version Version) {
        if(Version == null)
            throw new IllegalArgumentException("Version can't be null");

        return Version.ordinal() >= this.ordinal();
    }

    /**
    * @param Version ...
    *
    * @return ...
    */

    public boolean O(Version Version) {
        if(Version == null)
            throw new IllegalArgumentException("Version can't be null");

        return Version.ordinal() <= this.ordinal();
    }

    /**
    * ...
    */

    public static final String P;

    /**
    * ...
    */

    public static final Version V;

    static {
        P =  Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        V = Arrays.stream(Version.values()).sorted(Comparator.reverseOrder()).filter(V -> P.contains(V.name().substring(1)) || V.equals(Version.UNKNOWN)).collect(Collectors.toList()).get(0);
    }

    /**
    * @param Object ...
    *
    * @return ...
    */

    public static boolean C(Object Object) {
        return C(Object.getClass());
    }

    /**
    * @param Class ...
    *
    * @return ...
    */

    public static boolean C(Class<?> Class) {
        return ((!Class.isAnnotationPresent(Since.class) || Class.getAnnotation(Since.class).value().N(V))) && ((!Class.isAnnotationPresent(Until.class) || Class.getAnnotation(Until.class).value().O(V)));
    }

    /**
    * @param AccessibleObject ...
    *
    * @return ...
    */

    public static boolean C(AccessibleObject AccessibleObject) {
        return ((!AccessibleObject.isAnnotationPresent(Since.class) || AccessibleObject.getAnnotation(Since.class).value().N(V))) && ((!AccessibleObject.isAnnotationPresent(Until.class) || AccessibleObject.getAnnotation(Until.class).value().O(V)));
    }

    /**
    * @param Enum ...
    *
    * @return ...
    */

    public static boolean C(Enum<?> Enum) {
        try {
            return C(Enum.getDeclaringClass().getDeclaredField(Enum.name()));
        }catch (NoSuchFieldException e) {
            throw new NullPointerException("Unable to find " + Enum.name());
        }
    }
}