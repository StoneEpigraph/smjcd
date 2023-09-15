package cn.sm.demo.extend;

/**
 * @ClassName Parent
 * @Description TODO
 * @Author WhatsUpeng!!!
 * @Date 4/3/23 7:31 PM
 * @Version 1.0
 **/
public class ExtendTest {

    public static void main(String[] args) {
        Parent p = new Sub();
        p.setVar_flag(true);
        System.out.println(p.flag);
        System.out.println(p.var_flag);
    }


}


abstract class Parent {
    public static final boolean flag = false;

    protected boolean var_flag = false;

    public static boolean isFlag() {
        return flag;
    }

    public boolean isVar_flag() {
        return var_flag;
    }

    public void setVar_flag(boolean var_flag) {
        this.var_flag = var_flag;
    }
}

class Sub extends Parent {
    public static final boolean flag = true;

    protected boolean var_flag = true;
}