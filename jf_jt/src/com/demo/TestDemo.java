package com.demo;

import com.demo.common.CompactFile;

import java.io.File;

public class TestDemo {
    public static void main(String[] args) throws Exception {
        // System.out.println("------start------");
        // new FileMonitorTest(Paths.get("E:\\Download\\BGD\\TEST")).handleEvents();
        // System.out.println("------");
        // System.out.println(Paths.get("E:\\Download\\BGD\\TEST"));
        // System.out.println("------");
        // System.out.println("------end------");


        File f = new File("E:/Download/BGD/TEST/212.log");
        System.out.println(f.getName());
        String name = f.getName().substring(0,f.getName().length()-4);
        System.out.println(name+"-"+f);
        System.out.println(f.toString());
        new CompactFile(new File("E:/Download/BGD/TEST", name + ".zip")).zipFiles(f);
    }
}
