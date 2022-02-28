package com.test.neil.libjavatest;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

    public static final String REGULAR_SPLIT_STR = "#-@-#";
    public static void main(String[] args) {

        testNumberFormat();
        testDateFormat();
        testXXX();
//        testArrayList();
//        getIpUrl();
        testSubString();
//        testRandom();
//        testThread();

        int report = 0;
        int room = report & (1 << 1);
        if (room == 0) {
            report = report | (1 << 1);
        }

        System.out.println(Integer.toHexString(report));
        int mic = report & (1 << 2);
        if (mic == 0) {
            report = report | (1 << 2);
        }

        System.out.println(Integer.toHexString(report));
        int gift = report & (1 << 3);
        if (gift == 0) {
            report = report | (1 << 3);
        }
        System.out.println(Integer.toHexString(report));


        int roomx = report & (1 << 1);

        int roomxx = report & (1 << 2);

        int roomxxx = report & (1 << 3);

        System.out.println(roomx);
        System.out.println(roomxx);
        System.out.println(roomxxx);

        int key = 0xa;
        int tt = 1<<3;
        System.out.println(key | tt);

        System.out.println(formatTime((long) (1000 * 60 * 60 * 8.3)));
        System.out.println("" + (((float)40267 / 1024) > 39));

    }


    public static String formatTime(long durationInMillis) {
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d:%02d", hour, minute);

        return time;
    }

    private static void testThread() {
        for (int i = 0; i < 20; i++) {
            ParseIn.getInstance().parse(i, new Object());
        }
    }

    public static class ParseIn {
        public static ParseIn mInstance = new ParseIn();
        static ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        public static ParseIn getInstance() {
            if (mInstance == null) {
                mInstance = new ParseIn();
            }
            return mInstance;
        }

        public static void parse(final int i, final Object object) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500 * i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("==========thread = " + i + object.toString() + "==" + executorService.toString());
                }
            });
        }
    }


    private static void testXXX() {
        System.out.print("/-------------start--------------/");

      /*  getCurrentDayInWeek();
        getWhichDay(System.currentTimeMillis() - 1000 * 60 * 60 *24);
        getWhichDay(0);
        testAddAll();*/

        String xx = "";
//      long xxx = Long.valueOf(xx.trim());

        System.out.println("=========xxx = " + xx.isEmpty());

//      System.out.println("=========xxx = " + xxx);
        testName();
//        String history = "#4dlfsgo#-@-#lsdg43lkj#-@-#fergfdfg5#-@-#gfs34";
        String history = "#-@-#4dlfsgo#-@-#";
//        history = history.substring(history.indexOf(REGULAR_SPLIT_STR) + REGULAR_SPLIT_STR.length());
        String[] array = history.split(REGULAR_SPLIT_STR);
        for (int i = 0; i < array.length; i++) {
            System.out.print("\n[" + array[i] +"]");
        }

        long expire = 1567890;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        long hour = expire / nh;
        long min = expire % nh / nm;
        long sec = expire % nh % nm / 1000;

        System.out.println("\n\nhours = " + String.format("%02d", hour));
        System.out.println("minutes = " + String.format("%02d", min));
        System.out.println("seconds = " + String.format("%02d", sec));
    }

    private static void testRandom() {
        Random random = new Random();
        int percent = random.nextInt(2);
        int a = 2;
        a -= 1;
        System.out.println("=========random = " + percent + "  a = " + a);
    }

    private static void testDateFormat() {
        int month = 03;
        int dayOfMonth = 03;
        String mon = String.format("%1$02d", month + 1);
        String day = String.format("%1$02d", dayOfMonth);
        System.out.println(mon + day);
    }

    public static void testNumberFormat() {
        Double d= Double.parseDouble("0.3333");
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("============price xxx = " + df.format(d));


        DecimalFormat decimalFormat = new DecimalFormat(".00");
        long a = 1990000L;
        long b = 1000000L;

        if (a % b > 0) {
            String p = decimalFormat.format((float) a / b);
            System.out.println("price xxx = " + p);
        } else {
            String p = String.valueOf(a / b);
            System.out.println("price xxx aaa = " + a / b);
        }

    }

    public static void testSubString() {
        String phone = "+91";
        System.out.println("xxx:" + phone.substring(3));
    }


    public static String getIpUrl() {
        String ip = "1.1.1.1";
        String mUrl = "https://dssds.clouds.com/lsdiusd?lsdllllldje=xxxxx&expired=18273849";
        //http://dsdfsd.cloud.com/
        if(mUrl.indexOf("//") != -1 ){
            String[] split_str = mUrl.split("//");
            String end = split_str[1];
            if (end.indexOf("/") != -1) {
                String suffix = end.substring(end.indexOf("/"));
                String new_url = split_str[0] + "//" + ip + suffix;
                System.out.println(new_url);
            }
        }
        return mUrl;
    }

    public static void testArrayList() {
        ArrayList<String> list = new ArrayList();
        list.add("abc");
        list.add("123");
        list.add(2, "xxxx");
    }

    public static void testName() {
        String cdnHost = "";
       String mUrl = "http://35.244.151.105/805c4aed-2a9a-4c7a-b376-8c28a8e56c54_original.mp4";
            int start = mUrl.indexOf("://");
            int end = mUrl.indexOf("/");
            cdnHost = mUrl.substring(start, end);
        try {
            URL url = new URL(mUrl);
            System.out.print("=======start = " + start + "  end = " + end);
            System.out.print("=======url = " + url.getHost());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    public static void testAddAll() {
        ArrayList<String> org = new ArrayList<>();
        String aaa = "aaa";
        org.add(aaa);
        org.add("bbb");
        ArrayList<String> src = new ArrayList<>();
        src.add("ccc");
        src.addAll(org);
        for (String item : src) {
            System.out.print("\n/-----item ☹☹☹☹\uD83D\uDE2F= " + item);
        }
        System.out.print("\n/-----size= " + org.size());
        org.remove(aaa);

        System.out.print("\n/---1--size= " + org.size());

        String sCoinsCount = "";
        String[] xx = sCoinsCount.split("\\|");
        System.out.print("\n/-----item = " + xx[0] + " | " + xx[1]);
    }

    public static String getCurrentDayInWeek() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("E", Locale.ENGLISH);
        format = null;
        Anim anim = new Anim();
        anim.fly = "1";
        anim = null;
        if (!(format instanceof SimpleDateFormat)) {
            System.out.print("\n=========xxxxxxx = \n" + anim.fly);
        } else {
            System.out.print("===111======xxxxxxx = ");
        }
        Calendar calendar = Calendar.getInstance();
        System.out.print("=========WEEK = " + calendar.get(Calendar.DAY_OF_WEEK));
        return format.format(date);
    }

    public static int getWhichDay(long time) {
        Calendar calendar = Calendar.getInstance();
        if (time > 0) {
            calendar.setTimeInMillis(time);
        }
        System.out.print("=========DAY = " + calendar.get(Calendar.DAY_OF_YEAR));
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static class Anim {
        public Object fly;
    }
}
