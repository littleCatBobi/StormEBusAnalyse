package com.itstaredu.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
/**
 * KryoUtil序列化工具类
 * 负责对数据读入和写出
 */
public class KryoUtil {
    // 声明Kryo变量
    static Kryo kryo = new Kryo();

    /**
     * 数据写入
     * @param object
     * @return
     */
    public static byte[] in(Object object) {
        Output output = new Output(1, 4096);
        kryo.writeObject(output, object);
        byte[] b = output.toBytes();
        output.flush();
        output.close();
        return b;
    }

    /**
     * 数据写出
     * @param b
     * @param object
     * @return
     */
    public static Object out(byte[] b,Object object) {
        Input input = new Input(b);
        Object res = kryo.readObject(input,object.getClass());
        return res;
    }
}
