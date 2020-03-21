package cn.crabapples.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO 控制台彩色打印演示
 *
 * @author Mr.He
 * 2020/3/6 23:24
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class ColorFontDemo {
    public static void main(String[] args) {
        String str = "\033[1;31m hello world \033[0m";

        String startRex = "\\033.{1}\\d{1,2};\\d{1,2}m";
        String endRex = "\\033.{1}\\d{1,2}m";

        Pattern startPattern = Pattern.compile(startRex);
        Pattern endPattern = Pattern.compile(endRex);

        Matcher startMatcher = startPattern.matcher(str);
        Matcher endMatcher = endPattern.matcher(str);

        System.out.println(startMatcher.matches());
        System.out.println(endMatcher.matches());

        System.out.println("\n");

//        str = str.replaceAll(startRex,"");
//        str = str.replaceAll(endRex,"");

        System.out.println(str);
    }
}
