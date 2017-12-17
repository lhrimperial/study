package com.github.study;


public class Calculate {

    public static void main(String[] args) {
        fundMoney();
    }

    public static void fundMoney(){
        //total
        double huayu = 879.18+3626.97 +12473.22 +3626.97 +3626.97 +3626.97 +3626.97 +3626.97 +3637.64 +3637.64 +3241.90
                +3312.70 +2091.27 +3312.70 +5988.95 +3312.70   +3312.70 +2900 +2900 +2900;
        double kaxing = 3262.97+10421.40+10359.56+10359.56+1655;
        double hongwang = 8257+12114.99+11914+13193+13593.53+13593.53+13593.53+13593.53+13590+2853;
        double lvmama = 6680+12102.5+12095+12443+16227+ 12837+14802.5+12852;
        double qianbao = 2480+4250+5811+3233+1297.5+4193+7000+12488+11206+4644-2000;
        double xiangmu = 19700;
        System.out.println("total: " + (huayu+kaxing+hongwang+lvmama+xiangmu+qianbao));
        //2017
        double hw2017 = 13593.53+13593.53+13593.53+13590+2853;
        double qb2017 = 7000+12488+11206+4644-2000;
        System.out.println("sum2017：" + (hw2017+qb2017+lvmama) + "  salary: " + (hw2017+lvmama) + "  qb2017:  " + qb2017);
        System.out.println("huayu：" + huayu);
        System.out.println("kaxing：" + kaxing);
        System.out.println("hongwang：" + hongwang);
        System.out.println("lvmama：" + lvmama);
        System.out.println("xiangmu：" + xiangmu);
        System.out.println("qianbao：" + qianbao);

        cast();

    }

    /**
     *
     */
    public static void cast(){
        double other = 1420+609+669+850+2684+654+10000+6800+4800+298+5160+5000+10166+13600+15800;
        System.out.println("other:"+other);
        double phone = 3699;
        double fangzu = 3600+3600+3600;
        double jiaozha = 6000;
        System.out.println("phone:"+phone);
        System.out.println("fangzu:"+fangzu);
        System.out.println("jiaozha:"+jiaozha);
        double totalCast = phone+fangzu+jiaozha+other;

        double lost = 65936+50000+1200;
        System.out.println("lost:"+lost);
        System.out.println("totalCast:"+totalCast);
        System.out.println("cast sum:"+(totalCast+lost));

        int chongzhi = 400+300+3000+5000+15000+15000+8000+15000+4300+4500+50000;
        int tixian = 370+1279+3012;
        System.out.println("saichechongzhi:"+chongzhi);
        System.out.println("tixian:"+tixian);
        System.out.println(chongzhi-tixian);


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

    public static void calCount(double sum, int time){
        double count = 0;
        int income = 0;
        switch (time) {
            case 1:
                income = 35;
                break;
            case 2:
                income = 20;
                break;
            case 3:
                income = 60;
                break;
            case 4:
                income = 180;
                break;
            case 5:
                income = 540;
                break;
            case 6:
                income = 1620;
                break;
            default:
                income = 35;
        }
        count = Math.ceil(sum/income);
        System.out.println("sum: " + sum + "  第 " + time + " 次中总共需要 " + count + " 次");
    }

    public static void saiche(int start, int len){
        int sum = 0, per = start, income = 0;
        for (int i = 1; i <= len; i++) {
            if (i == 1) {
                per = start;
            } else if (i == 2) {
                per *= 2;
            } else {
                per *= 3;
            }
            sum += per;
            income = per / 10 * 17 - sum;
            System.out.println("i : " + i + "   per: " + per + "    sum: " + sum + "    income: " + income);
        }
    }


}