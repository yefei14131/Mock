package org.yefei.qa.mock.controller;

/**
 * @author yefei
 * @date: 2019/11/26
 */
public class IndexControllerTest {

    public static void main(String[] args) {
        String input = "/ps_meituan//order/createByShop";

        input = input.replaceAll("/+", "/");
        System.out.println(input);
    }
}
