# Training

个人 Java 技术栈学习与练习项目，涵盖算法与数据结构、并发编程、分布式系统组件、设计模式等。

---

## 项目信息

| 属性 | 内容 |
|------|------|
| GroupId | `org.glamey` |
| 版本 | `1.0-SNAPSHOT` |
| JDK | 1.8 |
| 构建工具 | Maven（多模块聚合） |
| 作者 | zhouyang.zhou / glamey |

---

## 模块概览

```
training/
├── pom.xml                  # 根 POM，统一依赖管理
├── codes/                   # 模块1：算法与数据结构
├── distribute/              # 模块2：分布式系统 & Java 进阶
└── grpc/                    # 独立 gRPC 演示项目
```

启动构建：`mvn clean compile -pl codes,distribute`

---

## 模块一：codes — 算法与数据结构

**坐标**: `org.glamey:training:codes`

专注于 LeetCode 算法题解及经典数据结构实现，每个类均可独立运行。

### 包结构与内容

| 包 | 内容 |
|----|------|
| `codes.leetcode` | 63 道 LeetCode 题解：字符串（`AddBinary`、`AddStrings`、`MultiplyStrings`）、数组（`TwoSum`、`ThreeSum`、`MoveZeroes`）、链表、栈（`MinStack`）、哈希（`ValidAnagram`）、排序频率统计等 |
| `codes.leetcode.dp` | 动态规划：编辑距离、最长回文子串、最大子数组、最小路径和、不同路径、阶乘 |
| `codes.leetcode.greedy` | 贪心算法：买卖股票、判断子序列 |
| `codes.leetcode.stack_queue` | 用队列实现栈、用栈实现队列 |
| `codes.listnode` | 链表操作大全：反转、合并、删除重复、环形检测、K 个一组翻转、排序、奇偶链表等 |
| `codes.arrays` | 数组专项：三数和、二维查找、滑动窗口最大值、合并有序数组、缺失数字、奇偶交换 |
| `codes.binarysearch` | 二分查找：循环/递归实现、模糊边界查找、旋转有序数组搜索 |
| `codes.sort` | 排序算法：冒泡、选择、快速排序（双指针 + 挖坑法）、归并、洗牌 |
| `codes.hash` | 哈希算法：`Hashing` 接口（含 MD5）、CRC16 |
| `codes.loadbalance` | 负载均衡接口定义 |
| `codes.demo` | 中文拼音转换（`nlp-lang` / `pinyin4j`） |

### 依赖

- Guava、Lombok、fastjson、nlp-lang、pinyin4j
- 依赖 `distribute` 模块

---

## 模块二：distribute — 分布式系统 & Java 进阶

**坐标**: `org.glamey:training:distribute`

### 技术栈

| 类别 | 技术 |
|------|------|
| 框架 | Spring Boot 2.1.6、MyBatis 3.2.4 |
| 中间件 | ZooKeeper（Curator 2.6.0）、Redis（Jedis） |
| 熔断 | Netflix Hystrix 1.5.18 |
| 序列化 | Kryo 3.0.3、Protobuf |
| 字节码 | ByteBuddy 1.10.8、Javassist 3.12.1 |
| 函数式 | Vavr 0.9.3 |
| 异步 HTTP | Apache HttpAsyncClient 4.1.3 |
| 工具库 | Guava 18.0、Commons Lang3/Collections4/IO |

### 包结构与内容

#### 并发编程 (`concurrent`)

| 子包/类 | 说明 |
|---------|------|
| `concurrent` | 核心并发：ABA 问题、`ThreadPoolExecutor` 源码分析（含状态机）、`CopyOnWriteArrayList`、`HashMap` 位运算、`RateLimiter` 限流、`ReadWriteLock`、`Semaphore` |
| `concurrent.thread` | 线程专题：`Callable`、`CountDownLatch`、`CyclicBarrier`、`Join`、拒绝策略、`Volatile`、`Wait/Notify`、`Yield` |
| `concurrent.thread.forkjoin` | Fork/Join 框架示例 |
| `concurrent.id` | **SnowFlake 雪花算法**（含时钟回拨处理） |
| `concurrent.lock` | `BoundedBuffer` 有界缓冲区（条件锁应用） |
| `concurrent.scheduler` | 时间轮调度器演示 |
| `concurrent.timewheel` | **完整时间轮实现**：环形缓冲区 + 任务调度 |
| `concurrent.concurrentArt` | 《Java 并发编程的艺术》笔记：CPU 上下文切换、死锁分析 |

#### ZooKeeper 客户端 (`zookeeper`)

- 完整的 `ZKClient` 接口与实现
- 支持持久/临时/顺序节点
- 子节点监听（`PathChildrenCache`）
- Leader 选举（基于 `LeaderSelector`）
- 连接状态监听

#### Hystrix 熔断 (`hystrix`)

- Spring Boot 应用，`@HystrixCommand` 注解配置
- 线程隔离、超时控制、熔断器、线程池
- Fallback 降级方法

#### 设计模式 (`designmodel`)

| 模式 | 实现 |
|------|------|
| 单例 | 5 种写法（饿汉、懒汉、DCL、静态内部类、枚举） |
| 观察者 | 2 种实现 |
| 生产者-消费者 | 3 种实现 |
| 代理 | JDK 动态代理 |
| 其他 | 迭代器、责任链、策略、SPI |

#### 其他包

| 包 | 说明 |
|----|------|
| `basic` | Java 基础：抽象类、中位数查找、栈、字符串 |
| `collection` | 集合框架：`SkipList` 跳表、Vavr 函数式集合、`Unsafe` |
| `collection.queue` | 自定义队列：数组队列、链表队列 |
| `event` | 事件驱动框架：`EventContext` + `EventObserver`（支持异步） |
| `guava` | Guava 使用：`EventBus`、`Supplier` |
| `good_habit` | 编程好习惯：Builder 模式、序列化、字符串拼接、`SubList` 陷阱等 |
| `jvm` | JVM：类加载器、反射、死循环类加载 |
| `vavr` | Vavr 函数式编程：Tuple、模式匹配、`Try` |
| `http` | 异步 HTTP 请求录制与回放 |
| `json` | JSON 比较工具 |
| `mybatis` | MyBatis + Spring Boot 启动类 |

---

## gRPC 独立项目

`grpc/` 目录为独立的 gRPC HelloWorld 演示，包含：

- `helloworld.proto` — 服务定义
- 通过 protoc 生成的客户端/服务端 stub
- `HelloWorldServer` 服务端实现

---

## 快速开始

```bash
# 编译全部模块
mvn clean compile

# 仅编译算法模块
mvn clean compile -pl codes

# 仅编译分布式模块
mvn clean compile -pl distribute
```

每个类通常包含 `main` 方法，可直接在 IDE 中运行。

---

## 项目定位

本项目是 **Java 技术栈系统学习仓库**，不是生产项目。覆盖以下学习领域：

- **算法与数据结构**：LeetCode 题解、经典算法实现
- **Java 并发编程**：从基础 API 到源码级分析
- **分布式系统**：ZooKeeper、Hystrix、SnowFlake、时间轮
- **设计模式**：常用模式的多种实现方式
- **JVM 原理**：类加载、反射、字节码操作
- **编程规范**：好习惯与常见陷阱

---

*最后更新：2024 年春季*
