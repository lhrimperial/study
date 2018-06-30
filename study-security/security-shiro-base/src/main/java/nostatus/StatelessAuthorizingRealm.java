package nostatus;

import com.ifarm.console.facade.service.IUserInfoService;
import com.ifarm.console.shared.domain.dto.PermissionVO;
import com.ifarm.console.shared.domain.dto.RoleInfoVO;
import com.ifarm.console.shared.domain.dto.UserInfoVO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class StatelessAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(StatelessAuthorizingRealm.class);

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("StatelessRealm.doGetAuthenticationInfo()");
        StatelessAuthenticationToken statelessToken = (StatelessAuthenticationToken) token;
        String userName = (String) statelessToken.getPrincipal();//不能为null,否则会报错的.
        //根据用户名获取密钥（和客户端的一样）
        String key = getKey(userName);
        //在服务器端生成客户端参数消息摘要
        String serverSignature = HmacSHA256Utils.digest(key, statelessToken.getParams());
        logger.info(serverSignature + "," + statelessToken.getCredentials());
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName,
                serverSignature,
                getName());
        return authenticationInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("StatelessRealm.doGetAuthorizationInfo()");
        //根据用户名查找角色，请根据需求实现
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfoVO userInfo = (UserInfoVO) principals.getPrimaryPrincipal();
        for (RoleInfoVO role : userInfo.getRoleInfoVOS()) {
            authorizationInfo.addRole(role.getRoleCode());
            for (PermissionVO p : role.getPermissionVOS()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }


    //得到密钥，此处硬编码一个.
    private String getKey(String username) {
        return "andy123456";
    }
}
