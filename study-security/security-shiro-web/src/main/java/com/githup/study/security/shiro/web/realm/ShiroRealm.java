package com.githup.study.security.shiro.web.realm;

import com.githup.study.security.shiro.web.domain.PermsPO;
import com.githup.study.security.shiro.web.domain.RolePO;
import com.githup.study.security.shiro.web.domain.UserPO;
import com.githup.study.security.shiro.web.service.IPermissionService;
import com.githup.study.security.shiro.web.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService permissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo+"+principalCollection.toString());
        UserPO userPO = userService.getByUserName((String) principalCollection.getPrimaryPrincipal());


        //把principals放session中 key=userId value=principals
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(userPO.getUserId()),SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //赋予角色
        for(RolePO userRole : userPO.getRoles()){
            info.addRole(userRole.getRoleName());
        }
        //赋予权限
        for(PermsPO permission : permissionService.getByUserId(userPO.getUserId())){
//            if(StringUtils.isNotBlank(permission.getPermCode()))
            info.addStringPermission(permission.getPerms());
        }

        //设置登录次数、时间
//        userService.updateUserLogin(user);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo +"  + authenticationToken.toString());

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName=token.getUsername();
        logger.info(userName+token.getPassword());

        UserPO user = userService.getByUserName(token.getUsername());
        if (user != null) {
//            byte[] salt = Encodes.decodeHex(user.getSalt());
//            ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
            //设置用户session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);
            return new SimpleAuthenticationInfo(userName,user.getPassword(),getName());
        } else {
            return null;
        }
    }
}
