package nostatus;


import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 **/
@Configuration
public class ShiroConfiguration {
    private static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.getFilters().put("statelessAuthc", statelessAuthcFilter());
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/**", "statelessAuthc");

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/logout", "logout");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /**
     * shiro安全管理器:
     * 主要是身份认证的管理，缓存管理，cookie管理，
     * 所以在实际开发中我们主要是和SecurityManager进行打交道的
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //subject工厂管理器
        securityManager.setSubjectFactory(subjectFactory());
        //session管理器：
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(statelessRealm());

        /**
         * 禁用使用Sessions 作为存储策略的实现，但它没有完全地禁用Sessions
         * 所以需要配合context.setSessionCreationEnabled(false);
         */
        ((DefaultSessionStorageEvaluator)((DefaultSubjectDAO)securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);

        return securityManager;
    }

    /**
     * subject工厂管理器.
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory(){
        StatelessDefaultSubjectFactory subjectFactory = new StatelessDefaultSubjectFactory();
        return subjectFactory;
    }



    /**
     * session管理器：
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * 因为我们禁用掉了会话，所以没必要再定期过期会话了。
     * @return
     */
    @Bean
    public DefaultSessionManager sessionManager(){
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    /**
     * 自己定义的realm.
     * @return
     */
    @Bean
    public  StatelessAuthorizingRealm statelessRealm(){
        StatelessAuthorizingRealm realm = new StatelessAuthorizingRealm();
        return realm;
    }

    /**
     * 访问控制器.
     */
    @Bean
    public StatelessAccessControlFilter statelessAuthcFilter(){
        StatelessAccessControlFilter statelessAuthcFilter = new StatelessAccessControlFilter();
        return statelessAuthcFilter;
    }

    /**
     * Shiro生命周期处理
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        logger.info("ShiroConfig.getLifecycleBeanPostProcessor()");
        return new LifecycleBeanPostProcessor();
    }
}
