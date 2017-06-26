package com.github.Util.Event;

import com.squareup.otto.Bus;

/**
 * Created by axnshy on 2017/6/23.
 */

public class OttoBus{
    private OttoBus(){

    }

    public static Bus getInstance(){
        return Singleton.INSTANCE;
    }

    private static class Singleton

    {
        private static Bus INSTANCE = new Bus();
    }


}
