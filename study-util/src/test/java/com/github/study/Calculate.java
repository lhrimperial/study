package com.github.study;

/**
 * @author longhairen
 * @create 2017-11-19 10:20
 * @description
 **/
public class Calculate {

    public static void main(String[] args) {
        calculateIncome();
    }

    public static void calculateIncome() {
        double huayu = 879.18 + 3626.97 + 12473.22 + 3626.97 + 3626.97 + 3626.97 + 3626.97 + 3626.97 + 3637.64 + 3637.64 + 3241.90
                + 3312.70 + 2091.27 + 3312.70 + 5988.95 + 3312.70 + 3312.70 + 2900 + 2900 + 2900;
        double kaxing = 3262.97 + 10421.40 + 10359.56 + 10359.56 + 1655;
        double hongwang = 8257 + 12114.99 + 11914 + 13193 + 13593.53 + 13593.53 + 13593.53 + 13593.53 + 13590 + 2853;
        double lvmama = 6680 + 12102.5 + 12095 + 12443 + 16227 + 12837 + 14802;
        double hw2017 = 13593.53 + 13593.53 + 13593.53 + 13590 + 2853;
        double qb2017 = 7000 + 12488 + 11206 + 4644 - 2000;
        double xiangmu = 19700;
        double qianbao = 2480 + 4250 + 5811 + 3233 + 1297.5 + 4193 + 7000 + 12488 + 11206 + 4644 - 2000;
        System.out.println("sum2017：" + (hw2017 + qb2017 + lvmama) + "  salary: " + (hw2017 + lvmama) + "  qb2017:  " + qb2017);
        System.out.println("huayu：" + huayu);
        System.out.println("kaxing：" + kaxing);
        System.out.println("hongwang：" + hongwang);
        System.out.println("lvmama：" + lvmama);
        System.out.println("xiangmu：" + xiangmu);
        System.out.println("qianbao：" + qianbao);
        System.out.println("totalComeIn: " + (huayu + kaxing + hongwang + lvmama + xiangmu + qianbao));

        System.out.println("************************************");
        double other = 1420 + 609 + 669 + 850 + 2684 + 654 + 10000 + 6800 + 4800 + 298 + 5160 + 5000 + 10166 + 13600;
        System.out.println("other:" + other);
        double phone = 3699;
        double fangzu = 3600 + 3600 + 3600;
        double jiaozha = 6000;
        System.out.println("phone:" + phone);
        System.out.println("fangzu:" + fangzu);
        System.out.println("jiaozha:" + jiaozha);
        double totalCast = phone + fangzu + jiaozha + other;
        double lost = 65936 + 50000 + 1200;
        System.out.println("lost:" + lost);
        System.out.println("totalCast:" + totalCast);
        System.out.println("cast sum:" + (totalCast + lost));

        /**
         * 充值赛车：
         2017-03-12:400
         2017-03-12:300
         2017-03-15:3000
         2017-03-16:5000
         2017-03-19:15000
         2017-04-15:15000
         2017-04-16:8000
         2017-04-20:15000
         2017-04-30:4300
         2017-05-01:4500
         2017-06-14:50000
         sum：120500
         */
    }
}