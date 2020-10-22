### 完成度：
* 有重要功能彻底不能用
* 此外很多小细节导致部分功能不是很完善

__Details:__

- \- EducationRequestDTO.java:17 这一句导致无法创建 education
- \- 用户id不存在时，查询 educations 应该返回 404 而不是空数组
- \- 无法创建 education
- \- 查询教育经历时，返回的应该是 userId，不是 id，是看得不仔细，还是觉得无所谓？
- \- 用户 id 不存在时不能允许创建 education
- \- 创建 user 时，错误提示信息有时只有 “must not be null”，并不清楚是哪个字段出了问题

### 测试：
* 没有实现任何测试。

__Details:__



### 知识点：
* 大部分知识点使用正确

__Details:__

- \- 校验年份格式的实现有问题
- \- 如果多个方法的 path 有一样的前缀，可以提到 class level 去统一设置
- \- 如果多个方法的 path 一样，可以提到 class level 去统一设置

### 工程实践：
* 没有提交 gradle/ 文件夹，导致无法在命令行进行构建
* 没有 .gitignore 文件，通过 Gradle 或 IDEA 进行构建后会造成大量无谓的变更
* 对 Optional API 的最佳实践掌握不熟练
* 对尽量使用 equals 判等这一类基本的 Java 编码实践不了解

__Details:__

- \- UserRepository.java:15 可以直接返回 Optional，调用方也会更好写，可以尝试改进一下
- \- 尽量使用 equals()
- \- 未删除的无用代码：InvalidUserException 构造函数

### 综合：
* 严重的功能问题，无法创建 education，自己都没有测试？
* Java 基础编码实践不熟，Java 8 相关 API 的最佳实践不熟。
* 构建及版本管理这块的工程实践还需要加强。

__Details:__

- \- UserService.java:36 userId 是刚刚在外面自行生成，这里判断是否存在的意义是什么？防止并发时不同的请求生成了相同的 id 吗？还是什么原因？否则，这里用 id 进行判重，不是很有意义。

