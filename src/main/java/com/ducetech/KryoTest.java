package com.ducetech;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by lenzhao on 17-2-20.
 */
public class KryoTest {

    public static void main(String[] args) throws Exception {
        //序列化
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));
        Person p = new Person();
        p.setName("赵世令");
        p.setPhone("18612700346");
        p.setPwd("123123");
        kryo.writeObject(output, p);
        output.close();
        //反序列化
        /*Input input = new Input(new FileInputStream("file.bin"));
        Person p = kryo.readObject(input, Person.class);
        System.out.println(p.getName());*/
    }
}
