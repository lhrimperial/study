package nostatus;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * 用于授权的Token对象：
 *
 * 用户身份即用户名；
 * 凭证即客户端传入的消息摘要。
 */
public class StatelessAuthenticationToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String userName;//用户身份即用户名；
    private Map<String,?> params;//参数.
    private String signature;//凭证即客户端传入的消息摘要。

    public StatelessAuthenticationToken() {
    }

    public StatelessAuthenticationToken(String userName, Map<String, ?> params, String signature) {
        super();
        this.userName = userName;
        this.params = params;
        this.signature = signature;
    }

    public StatelessAuthenticationToken(String userName, String signature) {
        super();
        this.userName = userName;
        this.signature = signature;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public Object getCredentials() {
        return signature;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
