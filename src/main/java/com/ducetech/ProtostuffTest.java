package com.ducetech;

import java.io.*;
import java.util.Random;

/**
 * Created by lenzhao on 17-2-20.
 */
public class ProtostuffTest {

    /** 产生一个随机的字符串*/
    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int strlen = str.length();
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(strlen);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    private static Person getObj(String name, String pwd, int contentSize) {
        Person obj = new Person();
        obj.setName(name);
        obj.setPwd(pwd);
        obj.setPhone(randomString(contentSize));
        return obj;
    }

    private static long speedTest(int contentSize, int times) {
        Person obj = getObj("lenzhao", "/home/admin/conf/lb", contentSize);
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            byte[] bytes = ProtostuffUtil.serialize(obj);
            ProtostuffUtil.deserialize(bytes, Person.class);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long speedTestOrg(int contentSize, int times) throws IOException, ClassNotFoundException {
        Person obj = getObj("lenzhao", "/home/admin/conf/lb", contentSize);
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] bytes = baos.toByteArray();

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            ois.readObject();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(speedTest(99999999, 10));
        System.out.println(speedTestOrg(9999999, 10));

    }

    private static void test() {
        Person obj = getObj("lenzhao", "/home/admin/conf/lb", 88888);
        byte[] bytes = ProtostuffUtil.serialize(obj);

        Person obj2 = ProtostuffUtil.deserialize(bytes, Person.class);
        System.out.println(obj2.getName());
        System.out.println(obj2.getPwd());
        System.out.println(obj.getPhone().equals(obj2.getPhone()));
    }
}
