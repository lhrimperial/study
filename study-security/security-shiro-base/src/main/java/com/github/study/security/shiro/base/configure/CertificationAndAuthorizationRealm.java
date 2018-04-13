package com.github.study.security.shiro.base.configure;

import com.ifarm.console.facade.service.IUserInfoService;
import com.ifarm.console.shared.domain.define.IFarmConstants;
import com.ifarm.console.shared.domain.dto.PermissionVO;
import com.ifarm.console.shared.domain.dto.RoleInfoVO;
import com.ifarm.console.shared.domain.dto.UserInfoVO;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义权限匹配和账号密码匹配
 */
public class CertificationAndAuthorizationRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(CertificationAndAuthorizationRealm.class);

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置-->CertificationAndAuthorizationRealm.doGetAuthorizationInfo()");
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

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        logger.info("CertificationAndAuthorizationRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfoVO userInfo = userInfoService.findByUserName(userName);
        if (userInfo == null) {
           throw new AuthenticationException();
        }
        if (IFarmConstants.INACTIVE.equals(userInfo.getActive())) { //账户冻结
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUserName(), //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }

}