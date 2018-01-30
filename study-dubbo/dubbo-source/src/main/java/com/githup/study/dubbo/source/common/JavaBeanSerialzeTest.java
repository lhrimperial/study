package com.githup.study.dubbo.source.common;

/**
 * @author longhr
 * @version 2017/12/4 0004
 */
public class JavaBeanSerialzeTest {

    public static void main(String[] args){
       /* User user = new User("275688","123456");
        JavaBeanDescriptor descriptor = JavaBeanSerializeUtil.serialize(user);

        User user1 = (User) JavaBeanSerializeUtil.deserialize(descriptor);
        System.out.println(user1.getUserName());
        System.out.println(user.getPassword());*/
    }

    public static class User {

        public User(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        private String userName;
        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
