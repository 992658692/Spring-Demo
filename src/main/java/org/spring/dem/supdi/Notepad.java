package org.spring.dem.supdi;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
//Scope用来定义创建的bean的作用域
//singleton：单利
//prototype：非单利
//session:web应用中会话创建bean
//request:web应用中请求创建bean
//会话与请求bean的创建理解：
//网上最多的例子就是购物车系统，当用单利才创建购物车的bean的时候，那么所有的用户访问的都是同一个购物车，这样不行
//采用非单利的模式来创建购物车的bean的话，那么可能可能在一个应用中会创建好几个不同的购物车，
//这样一个用户可能就会有好几个购物车
//所以这个时候采用会话来创建bean是最好的方案，一个回话对应的是一个bean，那么一个用户从访问应用开始到推出都会只有一个会话
//而且会话的bean不是在项目启动的时候创建的，而是在用户发起会话之后创建的
//如果在项目启动的时候有别的bean依赖到这个bean的话 那么系统会给这个会话bean创建一个代理
//所有依赖的bean都是依赖到这个代理上。
//如果这个类是接口的话可以用ScopedProxyMode.INTERFACES这个参数来表明该接口是代理
//如果这个类不是接口而是实体类的话那么必须要用ScopedProxyMode.TARGET_CLASS来创建代理了
@Scope(value=WebApplicationContext.SCOPE_SESSION,
		proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Notepad {

}
