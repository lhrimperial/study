package com.github.study.thread.base.basic;

/**
 * @author longhr
 * @version 2017/11/22 0022
 */
public class StopThreadUnSafe {

    public static User user = new User();

    public static class ChangeObjectThread extends Thread {
        volatile boolean stopMe = false;

        public void stopMe() {
            stopMe = true;
        }

        @Override
        public void run() {
            while (true) {
                /*if (stopMe) {
                    System.out.println("exit by stopMe");
                    break;
                }*/

                synchronized (user) {
                    int v = (int) (System.currentTimeMillis()/1000);
                    user.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
                System.out.println(Thread.currentThread().getName()+" free the CPU");
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.getId() != Integer.parseInt(user.getName())) {
                        System.out.println(user);
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args){
        System.out.println(user);
        new ReadObjectThread().start();
        while (true) {
            ChangeObjectThread thread = new ChangeObjectThread();
            thread.start();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.stop();
//            thread.stopMe();
        }
    }

    public static class User {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
