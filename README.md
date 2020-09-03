# Anonymous-question-box 
author：Oscar  
后台网址入口：https://fzulyt.fun  
小程序码：  
![image](https://raw.githubusercontent.com/OSCAR-super/shopping-online/master/gh_ebc40a4875f8_258.jpg)  
希望你能喜欢  
# 简介 
本项目包括websocket部分和demo部分  
websocket用于单独实现用户订单的处理  
demo部分用于支持项目的基本需求  
## 项目框架 
本项目使用了springboot+springclude+websocket+rabbitmq+springsecurity+redis框架  
框架中内嵌了ws的websocket实现项目内的实时通讯  
嵌入了rabbitmq队列，解决对mysql高强度读写时的高并发需求问题  
redis在这个高即时性的项目中负责读写即时性的信息，提高性能  
项目中使用了springsecurity来进行安全保护 
## 功能  
 * ### 实时订单
 项目把websocket单独分开，增加房号和个人序号方便监控和区分  
 内嵌在demo中的websocket负责实时改变内部数据  
 * ### 高并发
 项目中的订单信息和卖家动向都是高并发请求和读写对象，在项目中嵌入了rabbitmq保证读写的先后防止错读脏读  
 * ### 缓存
 项目中有购物车，个人信息等即取即用的缓存类数据，采用redis进行缓存可以缓解mysql的压力  
 * ### 关于安全
 引入springsecurity对项目安全进行保护，包括对接口的验证放行，对流入流出关键字的识别和替换转义  
 并且项目中增加了filter模块，并对filter进行分层过滤  
 * ### 用户分类
   游客|用户|管理员
   ---- | ----- | ------ 
   进入主页浏览（小程序）|操作订单（只能在小程序中登录）|处理订单，对商品进行操作（pc页）
 

