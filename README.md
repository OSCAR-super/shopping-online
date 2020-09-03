# Anonymous-question-box 
author：Oscar  
后台网址入口：https://fzulyt.fun  
希望你能喜欢  
# 简介 
本项目包括websocket部分和demo部分  
websocket用于单独实现用户订单的处理  
demo部分用于支持项目的基本需求  
## 1.项目框架 
本项目使用了springboot+springclude+websocket+rabbitmq+springsecurity+redis框架  
框架中内嵌了ws的websocket实现项目内的实时通讯  
嵌入了rabbitmq队列，解决对mysql高强度读写时的高并发需求问题  
redis在这个高即时性的项目中负责读写即时性的信息，提高性能  

