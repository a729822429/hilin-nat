# hilin-nat端口转发

[1]: https://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=plastic

[2]: /LICENSE

[3]: https://img.shields.io/badge/hilin-welcome-brightgreen.svg?style=plastic

[4]: https://hilin.icu

[5]: https://img.shields.io/badge/release-1.0.0-blue.svg?style=plastic

[6]: https://hilin.icu
[![license][1]][2]
[![Issue Welcome][3]][4]
[![Release Version][5]][6]  
端口转发项目，可通过web页面管理

## 特点

- 协议支持全面，兼容TCP/UDP和所有基于TCP、UDP的协议，例如http(s)、socks5、http...
- 纯JAVA开发，理论上全平台兼容(linux、windows、macos等)
- 通过管理页面配置和重启客户端

## 技术栈

- **JDK21** ： 当前最新的LTS版本
- **maven** ： 项目管理及自动构建工具
- **vertx-core** ： 网络框架，本项目用来创建网络客户端、服务器，并全局使用该框架提供的EventBus
- **vertx-web** ： 提供对外api，为什么不用springboot?用springboot还怎么吹nb......
- **spring-boot** ：这个只要是javaer应该都知道吧。本项目仅作为中间件的客户端工具使用，包括但不限于mysql、redis等
- **mysql** ： 最流行的关系型数据库
- **redis** ： 最流行的基于内存的kv存储系统


### 进度

- [x] tcp端口转发
- [ ] udp端口转发
- [ ] http域名绑定
- [ ] 管理页面

### mod介绍

mod-feature中都是实验性功能，未在项目中使用，稳定后才会考虑使用到项目中

- **hilin-tick-mod-event** 自研事件框架
