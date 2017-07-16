package com.xinda.system.sys.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Coundy.
 * @date 2017/5/26 17:32.
 */
public class TestEvent extends ApplicationEvent {

    private String msg;

    public TestEvent(String msg) {
        super(msg);
        this.msg = msg;
    }

    public void testEventMethod() {
        System.out.println("Msg : " + msg);
    }

}
