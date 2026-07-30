# CODEBUDDY.md This file provides guidance to CodeBuddy when working with code in this repository.

## 构建与运行命令

```bash
# 编译全部模块（需要 JDK 8）
mvn clean compile

# 仅编译指定模块
mvn clean compile -pl codes
mvn clean compile -pl distribute

# 运行全部测试（distribute 模块有 7 个 JUnit 测试用例）
mvn test

# 运行单个测试类
mvn test -pl distribute -Dtest=SingletonTest

# 跳过测试编译
mvn clean compile -Dmaven.test.skip=true
```

**注意**：`codes` 模块无测试文件；大多数类直接包含 `main()` 方法，可在 IDE 中右键运行。

## 项目架构概览

### 整体结构

本项目是一个 **Java 技术栈训练仓库**，非生产项目。采用 **Maven 多模块** 结构，JDK 1.8，包含两个 Maven 模块和一个独立的 gRPC 目录。

```
training (org.glamey:training:1.0-SNAPSHOT, pom 聚合)
├── codes/       ─── 算法与数据结构（依赖 distribute 模块）
├── distribute/  ─── 分布式系统 & Java 进阶（Spring Boot 2.1.6）
└── grpc/        ─── 独立 gRPC HelloWorld 演示（非 Maven 模块，需 protoc 构建）
```

### 模块职责

#### codes — 算法与数据结构（约 188 个 Java 文件）

纯 Java SE，无 Spring / 数据库依赖。覆盖：

- **leetcode/** — 约 63 道 LeetCode 题解（TwoSum、AddBinary、MinStack、ValidParentheses 等）
- **leetcode/dp/** — 动态规划（编辑距离、最长回文、最大子数组、最小路径和、不同路径）
- **leetcode/greedy/** — 贪心算法（买卖股票、判断子序列）
- **listnode/** — 27 个链表操作类（反转、合并、K 组翻转、环形检测、随机节点复制等）
- **arrays/** — 数组专项（三数和、滑动窗口最大值、有序数组合并、缺失数字）
- **binarysearch/** — 二分查找（循环/递归实现、边界搜索、旋转数组搜索）
- **sort/** — 排序算法实现（冒泡、选择、快速排序、归并、洗牌）
- **tree/** — 20 个树操作类（BFS/DFS 遍历、验证 BST、最近公共祖先、路径和、翻转二叉树等）
- **search/** — 通用搜索算法 + 布隆过滤器
- **hash/** — 哈希算法接口（MD5/CRC16） + 一致性哈希/槽位分片实现
- **loadbalance/** — 负载均衡算法（随机、加权随机、轮询、平滑加权轮询）
- **strings/** — 字符串专项（最长无重复子串、最长回文子串、句子反转、字符串相乘）
- **demo/** — 中文拼音转换（基于 nlp-lang + pinyin4j）

关键设计：每个类通常可独立运行（含 `main()`），无测试框架依赖，适合快速验证和调试。

#### distribute — 分布式系统 & Java 进阶（约 234 个 Java 文件，7 个测试类）

Spring Boot 2.1.6 应用，深度覆盖以下领域：

**并发编程 (concurrent/)** — 核心路径：
- `AnalyzerThreadPoolExecutor.java` — ThreadPoolExecutor 源码级分析（含状态机详解）
- `SnowFlake.java` — 雪花算法分布式 ID 生成（含时钟回拨处理）
- `TimingWheel.java` — 完整时间轮调度实现（环形缓冲区 + 任务调度）
- `thread/` 专题 — CountDownLatch、CyclicBarrier、Volatile、Wait/Notify 等 11 个类
- `concurrentArt/` — 《Java 并发编程的艺术》学习笔记（死锁分析、CPU 上下文切换）

**ZooKeeper (zookeeper/)** — 共 12 个类：
- `ZKClient` 完整接口 + 实现（基于 Curator），支持持久/临时/顺序节点
- `PathChildrenCache` 子节点监听、Leader 选举（`LeaderSelector`）、连接状态监听
- 测试：`ZKClientTest`、`ZKMultiClientTest`、`ZKLeaderTest`

**Hystrix 熔断 (hystrix/)** — Spring Boot + `@HystrixCommand` 注解：
- 线程隔离 / 超时控制 / 熔断器 / 降级 fallback

**设计模式 (designmodel/)** — 约 53 个文件，覆盖 12+ 种模式：
- 单例（5 种写法：饿汉、懒汉、DCL、静态内部类、枚举）
- 观察者（2 种实现）、生产者-消费者（3 种实现）、JDK 动态代理
- 责任链、策略、SPI（含 `META-INF/services/` 注册文件）、迭代器

**JVM (jvm/)** — 类加载器、反射、死循环类加载、`Unsafe` 操作

**其他关键包**：
- `basic/` — 抽象类、中位数查找、栈、字符串基础
- `collection/queue/` — 自定义队列（数组/链表实现）
- `event/` — 事件驱动框架（`EventContext` + `EventObserver`，支持异步发布）
- `good_habit/` — Builder 模式、序列化、SubList 陷阱、TreeSet 等 9 个最佳实践类
- `http/` — 异步 HTTP 录制与回放（基于 HttpAsyncClient）
- `vavr/` — 函数式编程（Tuple、模式匹配、Try 异常处理）
- `mybatis/` — MyBatis + Spring Boot 启动入口 + DAO 测试
- `json/` — JSON 比对工具

**测试分布（共 7 个）**：
- `designmodel/SingletonTest` — 单例模式测试
- `zookeeper/ZKClientTest`、`ZKMultiClientTest`、`election/ZKLeaderTest` — ZooKeeper 集成测试
- `io/cache/FifoCacheTest`、`LruCacheTest` — 缓存策略测试
- `db/mybatis/dao/UserEntityDaoTest` — MyBatis DAO 层测试

**资源文件**：
- `logback.xml` — 控制台 + 按天滚动文件日志策略
- `mybatis/mybatis-config-idea.xml` — MyBatis 完整配置（含数据源）
- `spring/` — Spring XML 配置
- `services/org.glamey.training.designmodel.spi.SpiOperation` — SPI 注册文件

### gRPC 独立项目

`grpc/` 目录内含 `helloworld.proto`，通过 protoc 生成 `GreeterGrpc` 客户端/服务端 stub，及 `HelloWorldServer` 实现。非 Maven 模块，需单独使用 protoc 编译。

## 模块间依赖关系

```
distribute (Spring Boot 2.1.6)
    ↑ 依赖
codes (算法 + 数据结构)
```

根 POM 通过 `<dependencyManagement>` 统一管理所有依赖版本。`distribute` 是基础模块，`codes` 依赖 `distribute`。

## 关键配置

- **JDK**: 1.8
- **Maven 源编码**: UTF-8
- **根 pom.xml** 中统一定义了：Guava 18.0、Curator 2.6.0、Kryo 3.0.3、Vavr 0.9.3、MyBatis 3.2.4、Hystrix 1.5.18 等依赖版本
