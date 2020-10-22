package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO GTB-综合: * 严重的功能问题，无法创建 education，自己都没有测试？
//TODO GTB-综合: * Java 基础编码实践不熟，Java 8 相关 API 的最佳实践不熟。
//TODO GTB-综合: * 构建及版本管理这块的工程实践还需要加强。

//TODO GTB-测试: * 没有实现任何测试。

//TODO GTB-完成度: * 有重要功能彻底不能用
//TODO GTB-完成度: * 此外很多小细节导致部分功能不是很完善

//TODO GTB-完成度: - 用户id不存在时，查询 educations 应该返回 404 而不是空数组
//TODO GTB-完成度: - 无法创建 education
//TODO GTB-完成度: - 查询教育经历时，返回的应该是 userId，不是 id，是看得不仔细，还是觉得无所谓？
//TODO GTB-完成度: - 用户 id 不存在时不能允许创建 education
//TODO GTB-完成度: - 创建 user 时，错误提示信息有时只有 “must not be null”，并不清楚是哪个字段出了问题

//TODO GTB-知识点: * 大部分知识点使用正确

//TODO GTB-工程实践: * 没有提交 gradle/ 文件夹，导致无法在命令行进行构建
//TODO GTB-工程实践: * 没有 .gitignore 文件，通过 Gradle 或 IDEA 进行构建后会造成大量无谓的变更
//TODO GTB-工程实践: * 对 Optional API 的最佳实践掌握不熟练
//TODO GTB-工程实践: * 对尽量使用 equals 判等这一类基本的 Java 编码实践不了解
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
