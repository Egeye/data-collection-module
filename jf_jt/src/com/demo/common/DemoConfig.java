package com.demo.common;

import com.demo.controller.IndexController;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        PropKit.use("config.properties");
        me.setDevMode(true);
    }

    @Override
    public void configRoute(Routes me) {
//        me.add("/", IndexController.class, "/index");
        me.add("/", IndexController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
