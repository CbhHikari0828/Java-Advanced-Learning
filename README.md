# Java Advanced Learning

这是一个面向 Java 并发编程学习的代码仓库，目标不是“只会写代码”，而是通过一系列小案例，帮助你逐步理解并发编程的底层原理、常见问题和排查思路。

## 1. 项目目标

这个仓库将按以下方式推进学习：

1. 先掌握 Java 并发编程的基础概念
2. 再通过小案例理解并发背后的底层原理
3. 最后结合工具排查常见并发问题

## 2. 学习路线

### 第一阶段：线程基础
- Thread / Runnable / Callable
- Future / FutureTask
- 线程生命周期与状态

### 第二阶段：并发基础机制
- synchronized
- volatile
- wait / notify / notifyAll

### 第三阶段：锁与底层机制
- ReentrantLock
- Condition
- AQS 原理

### 第四阶段：并发容器与原子类
- ConcurrentHashMap
- CopyOnWriteArrayList
- AtomicInteger / LongAdder

### 第五阶段：线程池与任务调度
- Executor / ExecutorService
- ThreadPoolExecutor
- 线程池参数与调优思路

### 第六阶段：并发问题与排查
- 死锁
- 竞态条件
- 线程安全问题
- jstack / JFR / JConsole / JVisualVM

## 3. 仓库目录规划

```text
Java-Advanced-Learning/
├── README.md
├── 01-thread-foundation/
├── 02-synchronization/
├── 03-lock-and-aqs/
├── 04-concurrent-collections/
├── 05-atomic-and-volatile/
├── 06-thread-pool/
├── 07-producer-consumer/
├── 08-jmm-and-happens-before/
├── 09-performance-and-debug/
└── 10-practice-projects/
```

## 4. 每个模块的标准格式

每个模块建议包含以下内容：

- 原理说明：这个知识点解决了什么问题
- 最小 Demo：用尽可能简单的代码复现现象
- 结果分析：为什么会这样
- 关键总结：这个知识点的本质是什么

## 5. 执行清单（后续按此推进）

### Phase 1：项目搭建
- [x] 创建仓库说明文件
- [ ] 创建基础目录结构
- [ ] 初始化 Java 工程结构
- [ ] 增加 .gitignore

### Phase 2：基础并发模块
- [ ] 完成线程基础示例
- [ ] 完成 synchronized 与 volatile 示例
- [ ] 完成线程池示例

### Phase 3：核心原理模块
- [ ] 完成 ReentrantLock 与 Condition 示例
- [ ] 完成 AQS 原理说明与代码演示
- [ ] 完成 ConcurrentHashMap / Atomic 类示例

### Phase 4：问题排查与实战
- [ ] 完成死锁案例
- [ ] 完成生产者消费者案例
- [ ] 完成 JFR / jstack 排查示例

### Phase 5：整理总结
- [ ] 补充每个模块的学习笔记
- [ ] 统一代码命名风格
- [ ] 完成最终 README 总结

## 6. 学习方法建议

为了更高效地掌握并发编程，建议每次学习一个点时都回答以下 4 个问题：

1. 它解决了什么问题？
2. 它的内部机制是什么？
3. 它可能在哪些情况下出错？
4. 它和其他方案有什么区别？

## 7. 后续推进原则

- 代码尽量小，但要能复现现象
- 不只写“能运行”的代码，更要写“能说明原理”的代码
- 每个案例都尽量配上简短说明
- 后续提交到仓库时，尽量保持模块清晰、结构可读

---

这份 README 会作为后续开发的主清单，后面所有模块都会按照这里的顺序和格式来补齐。
