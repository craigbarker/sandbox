package org.sgodden.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: simon
 * Date: 18/04/12
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class RegExtest {

    public static void main(String[] args) {
        Pattern p = Pattern.compile(".*(\\+|-)([a-zA-Z]+).*");
        Matcher m = p.matcher("sort(+foo)");
        if (m.find()) {
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }
        m = p.matcher("sort(-foo)");
        if (m.find()) {
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }
    }
}
