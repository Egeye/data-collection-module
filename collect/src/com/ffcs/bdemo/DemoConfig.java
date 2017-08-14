package com.ffcs.bdemo;

import com.jfinal.config.*;

public class DemoConfig {
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }

    public void configRoute(Routes me) {
        me.setBaseViewPath("/view");
//        me.addInterceptor(new FrontInterceptor);
        me.add("/hello", HelloController.class);
        me.add("/", IndexController.class, "index");
    }

    public void configPlugin(Plugins me) {

    }

    public void configInterceptor(Interceptors me) {
    }

    public void configHandler(Handlers me) {
    }
}
