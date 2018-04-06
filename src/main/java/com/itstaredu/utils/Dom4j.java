package com.itstaredu.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Dom4j的util类
 */
public class Dom4j {
    /**
     * DMO4J写入XML
     *
     * @param obj    泛型对象
     * @param Encode XML自定义编码类型(推荐使用GBK)
     */
    public static Document xml(Object obj, String Encode) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(Encode);// 设置XML文件的编码格式
            // 新建student.xml文件并新增内容
            Document document = DocumentHelper.createDocument();
            String rootname = obj.getClass().getSimpleName();//获得类名
            Element root = document.addElement(rootname + "s");//添加根节点
            Field[] properties = obj.getClass().getDeclaredFields();//获得实体类的所有属性
            Element secondRoot = root.addElement(rootname); //二级节点
            Object t = obj;
            for (int i = 0; i < properties.length; i++) {
                //反射get方法
                Method meth = t.getClass().getMethod(
                        "get"
                                + properties[i].getName().substring(0, 1)
                                .toUpperCase()
                                + properties[i].getName().substring(1));
                //为二级节点添加属性，属性值为对应属性的值
                secondRoot.addElement(properties[i].getName()).setText(
                        meth.invoke(t).toString());
            }
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Dom4j() {
    }


//    public static void main(String[] args) {
//        Dom4j d = new Dom4j();
//        User user = new User();
//        User user1 = new User("姓名1", "18", "男");
//        User user2 = new User("姓名2", "19", "女");
//        User user3 = new User("石头", "20", "女");
//
//
//        List<User> users = new ArrayList<User>();
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//
//        d.writeXmlDocument(user, users, "UTF-8", "student.xml");
//
//    }


    //    public static void main(String[] args) {
//        Dom4j d = new Dom4j();
//        User user = new User();
//
//        List<User> list = d.readXML("student.xml", user);
//        System.out.println("XML文件读取结果");
//        for (int i = 0; i < list.size(); i++) {
//            User usename = (User) list.get(i);
//            System.out.println("name" + usename.getName());
//            System.out.println("age" + usename.getAge());
//            System.out.println("sax" + usename.getGender());
//        }
//    }
    public static void main(String[] args) {

    }

    /**
     * @param t 泛型对象
     * @return
     */
    public static Object pojo(Document document, Object t) {
        try {
            Document doc = document;
            Element root = doc.getRootElement();//获得根节点
            Element foo;//二级节点
            Field[] properties = t.getClass().getDeclaredFields();//获得实例的属性
            //实例的set方法
            Method setmeth;
            foo = (Element) root.elementIterator(t.getClass().getSimpleName()).next();//下一个二级节点
            t = t.getClass().newInstance();//获得对象的新的实例
            for (int j = 0; j < properties.length; j++) {//遍历所有孙子节点
                //实例的set方法
                setmeth = t.getClass().getMethod(
                        "set"
                                + properties[j].getName().substring(0, 1)
                                .toUpperCase()
                                + properties[j].getName().substring(1), properties[j].getType());
                //properties[j].getType()为set方法入口参数的参数类型(Class类型)
                setmeth.invoke(t, foo.elementText(properties[j].getName()));//将对应节点的值存入
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
