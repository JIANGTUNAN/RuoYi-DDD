# RuoYi-DDD

基于轻量化 DDD 思想改装的若依框架。

## 简介

这是一个 **DDD 风味项目**，不完全遵循 DDD 理论，实用大于设计。

核心思想：借鉴 DDD 的分层架构和领域建模理念，但不追求完美的领域模型，以解决实际问题为第一优先级。

## 技术栈

- Java 17
- Spring Boot 2.7.18
- MyBatis Plus 3.5.5
- Sa-Token（权限认证）

## 模块结构

```
ruoyi-ddd
├── ruoyi-ddd-api          # 接口层
├── ruoyi-ddd-app          # 应用层
├── ruoyi-ddd-domain       # 领域层
├── ruoyi-ddd-infra        # 基础设施层
│   ├── infra-adapter      # 框架适配器
│   └── infra-db           # 数据库持久化
└── ruoyi-ddd-start        # 启动模块
```

## 快速开始

```bash
# 构建
mvn clean install

# 运行
mvn spring-boot:run -pl ruoyi-ddd-start
```

## 参考项目

- [RuoYi-Vue](https://github.com/yangzongzhuan/RuoYi-Vue) - 原始若依框架
