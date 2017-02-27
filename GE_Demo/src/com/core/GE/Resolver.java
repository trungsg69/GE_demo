package com.core.GE;

/**
 * Created by TrungNT47 on 2/23/2017.
 */
public class Resolver {

        public static String[] ResolveName(String element){
            final String[] parts = element.split("=", 2);
            return parts;
        /*return (
                (parts.length == 2)? parts : parts
        );*/
        }
    }

