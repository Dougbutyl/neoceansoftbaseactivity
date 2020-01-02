package com.app.mylibrary.tools;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtil {

    public static void send(Object object){
        EventBus.getDefault().post(object);
    }
}
