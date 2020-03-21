package cn.crabapples.demo;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/3/7 19:30
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class A {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "x" + i + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }
}
