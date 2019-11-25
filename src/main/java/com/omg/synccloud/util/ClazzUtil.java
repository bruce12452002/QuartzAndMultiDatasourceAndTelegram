package com.omg.synccloud.util;

import org.quartz.Job;

public class ClazzUtil {
    public static Job getClass(String classname) throws Exception {
        return (Job) Class.forName("com.og.ogsynccloud.job." + classname).getDeclaredConstructor().newInstance();
    }
}
