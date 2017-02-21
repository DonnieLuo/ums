package com.util;

import com.google.gson.Gson;

/**
 * Created by Donnie on 2017/2/21.
 */
public class GsonUtil {
    private static class GsonHolder{
        private static final Gson INSTANCE = new Gson();
    }

    public static Gson getInstance()
    {
        return GsonHolder.INSTANCE;
    }
}
