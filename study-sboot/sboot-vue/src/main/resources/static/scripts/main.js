
//vue_router
(function(require, define) {
    require.config({
        paths: {
            'vue': 'https://cdn.bootcss.com/vue/2.0.5/vue',
            'vue-router': 'https://cdn.bootcss.com/vue-router/2.0.1/vue-router'
        }
    });

    define(function(require, exports, module) {
        var Vue = require('vue');
        var VueRouter = require('vue-router');
        Vue.use(VueRouter);
        var router = new VueRouter({
            routes: [{
                path: '/component1',
                component: function(resolve) {
                    resolve(require('scripts/component1.js'));
                }
            },
            {
                path: '/component2',
                component: function(resolve) {
                    resolve(require('scripts/component2.js'));
                }
            },
            ]
        })
        var app = new Vue({
            el: '#app',
            router: router,
            template: '<div class="content">\
            <h1>Hello Content!</h1>\
            <p>\
             <router-link to="/component1">Go to Component1</router-link>\
             <router-link to="/component2">Go to Component2</router-link>\
            </p>\
            <router-view></router-view>\
            </div>'
        });
    });
})(require, define);


//hello_requirejs
/*
(function(require, define){
    require.config({
        paths: {
            'vue': 'https://cdn.bootcss.com/vue/2.0.3/vue',
            // 使用cdn无法加载找了一下是因为define("ELEMENT",["vue"],t)多了一个"ELEMENT"参数
            'element': 'element'
        }
    });
    define(function (require, exports, module) {
        var element_ui = require('element');
        var Vue = require('vue');
        element_ui.install(Vue);
        new Vue({
            el: '#app',
            data: {
                mes: 'hello_el-button'
            },
            template: '<el-button>{{ mes }}</el-button>'
        })
    });
})(require, define);*/
