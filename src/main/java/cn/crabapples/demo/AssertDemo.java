package cn.crabapples.demo;

/**
 * TODO 断言演示,JVM启动时加上参数-ea可显式检查assert后面的表达式是否成立
 *
 * @author Mr.He
 * 2019/9/19 22:23
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class AssertDemo {
    public static void main(String[] args) {
        assert false : "结束";
    }
}
