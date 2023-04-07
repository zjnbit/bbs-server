好的，以下是 "甬IT论坛-服务端" 的 README.md 文件内容：

# 甬IT论坛-服务端

这是甬IT论坛的服务端代码仓库，使用 Java 和 Spring Boot 开发。该服务端提供了甬IT论坛的核心业务逻辑，包括用户注册、登录、发表帖子、评论等功能。

## 技术栈

- Java 1.8
- Spring Boot 2.6.14
- MySQL
- MyBatis
- Redis
- RabbitMQ
- sa-token
- hu-tool
## 功能列表

- [ ] 用户注册、登录、退出登录
- [ ] 发表、编辑、删除帖子
- [ ] 评论、回复评论、点赞
- [ ] 个人资料修改
- [ ] 管理员后台管理

## 部署说明
- 待完善

- `doc/db/` 目录下有数据库脚本，可直接导入 MySQL 中
- 修改其中`bbs_sys_conf`表中的`conf_value`字段的值，以适配自己的环境
- 二次开发时，修改根目录`pom.xml`中`<distributionManagement>`节点内容，以适配自己的环境

## 联系方式

如有任何问题或建议，请联系以下开发者：

- owner：邮箱 zjnbit@gmail.com

## 许可证

该项目基于 MIT 许可证进行开源，详细信息请参阅 LICENSE 文件。