package com.demo.controller;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
    public void index() {
        render("/src/app/views/index.html");
//        render("/src/app/views/dataFile.html");
    }

    public void content() {
        render("/src/app/views/dataFile.html");
    }
}
