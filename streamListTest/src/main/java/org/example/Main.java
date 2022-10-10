package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long listResult = 0;
        long streamResult = 0;
        final long iterCount = 100;
        final long testCount = 10000000;

        for (int j = 0; j < iterCount; j++) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < testCount; i++) {
                list.add("test" + i);
            }
            long listBeforeTime = System.currentTimeMillis();
            list.forEach(String::getBytes);
            long listAfterTime = System.currentTimeMillis();

            listResult = listResult + (listAfterTime - listBeforeTime);

            long streamBeforeTime = System.currentTimeMillis();
            list.stream().forEach(String::getBytes);
            long streamAfterTime = System.currentTimeMillis();
            streamResult += (streamAfterTime - streamBeforeTime);
        }

        System.out.println("When \"" + testCount + "\" data is repeatedly processed \"" + iterCount + "\" times, the average value (ms) is as follows");
        System.out.println("list = " + (listResult / iterCount));
        System.out.println("stream = " + (streamResult / iterCount));
        System.out.println("total \"" + (iterCount * testCount) + "\" times");
    }
}