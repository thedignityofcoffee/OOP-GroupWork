# End of Semester Group Project  <br>学期末小组项目  

**Title: Integrated Management System (IMS) Group Project: A Hands-On Java OOP Application.**  
**综合管理系统（IMS）小组项目： Java OOP 实践应用。**  
## Project Overview  <br>项目概述  

The IMS project integrates four Java subsystems into one cohesive application:  
IMS 项目将四个 Java 子系统整合为一个统一的应用程序：  

- Zoo Management System  动物园管理系统  
- Banking Task Management  银行任务管理  
- Restaurant Management  餐厅管理  
- System Shape Parsing & Analysis  系统形状解析与分析  

This project provides practical experience with Java OOP concepts you've already practiced in the coursework, while introducing you to basic system integration and GUI development. Each domain system will serve as a modular subsystem, with group members responsible for integrating and enhancing individual modules, ensuring seamless interoperability within the unified framework (GUI).  
该项目将为您提供在课程中已经练习过的 Java OOP 概念的实践经验，同时向您介绍基本的系统集成和图形用户界面开发。每个领域系统都将作为一个模块化子系统，小组成员负责集成和增强各个模块，确保在统一框架（图形用户界面）内实现无缝互操作。 
## Learning Objectives  <br>学习目标  

- **Apply OOP fundamentals:** Classes, inheritance, polymorphism, interfaces, and collections   
- **应用 OOP 基础：** 类、继承、多态性、接口和集合  
- **Practice modular design:** Create clean APIs between components   
- **实践模块化设计：** 在组件之间创建简洁的应用程序接口  
- **Experience the software development lifecycle:** Design, implementation, testing, and integration   
- **体验软件开发生命周期：** 设计、实施、测试和集成  
- **Develop teamwork skills:** Collaborative coding, version control, and project management  
- **开发团队协作技能：** 协同编码、版本控制和项目管理  

## Project Structure  <br>项目结构  

### 1. Individual Modules  <br>1. 单个模块

Each team member owns one module, implementing the specific functionality described below. Your module should:  
每个团队成员拥有一个模块，实现下文所述的特定功能。你的模块应：  

	- Follow the class structure from your coursework practical descriptions   
	- 遵循课业实践描述中的类结构  
	- Implement all required functionality   
	- 实现所有必要功能  
	- Include thorough unit tests   
	- 包含全面的单元测试  
	- Expose a simple, well-defined API  
	- 提供简单、定义明确的应用程序接口  

### 2. Dashboard Integration  <br>2. 仪表盘集成  

As team members, you will create a Java Swing dashboard that:  
作为团队成员，你们将创建一个 Java Swing 仪表盘，它可以：  
	
	- Provides a unified entry point to all four modules  
	- 为所有四个模块提供统一的入口点  
	- Contains buttons to launch each module's interface  
	- 包含启动每个模块界面的按钮  
	- Facilitates simple cross-module communication where appropriate  
	- 在适当的地方促进简单的跨模块通信  

### 3. Deliver Comprehensive Documentation  <br>3. 提供全面的文档  

Provide UML diagrams and class diagrams to outline the system design and implementation.  
提供 UML 图表和类图，概述系统设计和实施。 

## Module Specifications  <br>模块规格  

### Module 1: Zoo Management<br>模块 1：动物园管理  

Based on your existing coursework design:  
基于您现有的课业设计：  

#### Key Classes:  <br>关键类：  

- `Zoo`: Manages a collection of animals   
- `动物园`：管理动物集合  
- `Animal`: Represents animals with properties like name, species, age   
- `动物`：代表具有名称、种类、年龄等属性的动物  
- `Item`: Equipment or supplies needed for animal care   
- `物品`：照顾动物所需的设备或用品  
- `Logistics`: Handles transportation between zoos  
- `物流`：处理动物园之间的运输  

#### Core Features:  <br>核心功能：  

- Add / remove / search for animals  
- 添加/删除/搜索动物  
- Move animals between zoos  
- 在动物园之间移动动物  
- Track transport costs  
- 跟踪运输成本  

#### Zoo Module API:  <br>动物园模块API：  

```Java
class ZooManager {
	void addAnimal(String zooName, Animal animal); 
	boolean moveAnimal(String fromZoo, String toZoo, String animalName); 
	ArrayList<Animal> listAnimals(String zooName); 
	double getTransportCost(String fromZoo, String toZoo); 
}  
```

### Module 2: Banking Task Management  <br>模块 2：银行业务任务管理  

Based on your banking practical:  
基于您的银行业务实践：  

#### Key Classes:  <br>关键类：  

- `BankAccount`: Manages account balance and operations  
- `银行账户`：管理账户余额和操作  
- `TaskManager`: Generic task prioritization system  
- `任务管理器`：通用任务优先级系统  
- `BankingTaskManager`: Banking-specific task management  
- `银行任务管理器`：银行特定任务管理  

#### Core Features:  <br>核心功能：  

- Create accounts, deposit and withdraw funds  
- 创建账户、存款和取款  
- Process monthly interest  
- 处理每月利息  
- Manage high and low priority banking tasks  
- 管理高优先级和低优先级银行任务  

#### Banking Task Module API:  <br>银行任务模块 API：  

```Java
class BankingService {
	void createAccount(String accountNumber, double initialBalance); 
	boolean deposit(String accountNumber, double amount); 
	boolean withdraw(String accountNumber, double amount); 
	double getBalance(String accountNumber); 
	ArrayList<String> listHighPriorityTasks(); 
}  
```

### Module 3: Restaurant Management  <br>模块 3： 餐厅管理  

Based on your restaurant coursework:  
基于你的餐厅课程作业：  

#### Key Classes:  <br>关键类：  

- `Priceable`: Interface for items with a price  
- `可标价`：带价格的项目接口  
- `Ingredient`: Food components with prices  
- `成分`：有价格的食物成分  
- `Meal`: Combination of ingredients  
- `食物`：食材组合  
- `RestaurantBillingSystem`: Abstract foundation class  
- `餐厅计费系统`：抽象基础类  
- `RestaurantBilling`: Concrete implementation  
- `餐厅计费`：具体实现  

#### Core Features:  <br>核心功能：  

- Manage ingredient inventory  
- 管理食材库存  
- Create and price meals  
- 创建餐点并定价  
- Generate customer bills  
- 生成客户账单  

#### Restaurant Module API:  <br>餐厅模块API：  

```Java
class RestaurantService { 
	void addIngredient(String name, double price); 
	void createMeal(String mealName, ArrayList<String> ingredientNames); 
	String placeOrder(HashMap<String, Integer> mealQuantities); 
	double calculateBill(String orderId);   
}  
```

### Module 4: Shape Parsing & Analysis  <br>模块 4：形状解析与分析  

Based on your week 8 practical:  
基于第 8 周的实践：  

#### Key Classes:  <br>关键类：

 - `Shape`: Interface with area and perimeter calculations  
 - `形状`：面积和周长计算界面  
 - `Concrete classes`: Circle, Square, Rectangle, Triangle  
 - `具体类`：圆形、正方形、长方形、三角形  
 - `ShapeParser`: Reads shape data from files  
 - `形状解析器`：从文件中读取形状数据  

#### Core Features:  <br>核心功能：  

- Parse shape definitions from files  
- 从文件中解析形状定义  
- Calculate areas and perimeters  
- 计算面积和周长  
- Count shapes by type  
- 按类型统计形状  
- Visualize shapes (optional stretch goal)  
- 可视化形状（可选扩展目标）  

#### Shape Module API:  <br>形状模块 API：  

```Java
class ShapeService { 
	ArrayList<Shape> parseFile(String filePath); 
	HashMap<String, Integer> countByType(ArrayList<Shape> shapes); 
	double totalArea(ArrayList <Shape> shapes); 
	void displayShapes(ArrayList<Shape> shapes);  
}  
```

## Simple Cross-Module Integration  <br>简单的跨模块集成  

Keep integration lightweight but meaningful:  
保持轻量级但有意义的集成：  

1. ${\bf Zoo}\rightarrow {\bf Banking}$ : 
	- Track zoo ticket sales in a bank account  
	- 跟踪银行账户中的动物园门票销售情况  
	- Example: `bankingService.deposit("zoo1", ticketSales);`  

2. ${\bf Restaurant}\rightarrow {\bf Banking}$ : 
	- Process restaurant bills through customer accounts  
	- 通过客户账户处理餐厅账单  
	- Example: `bankingService.withdraw(customerId, restaurantService.calculateBill(orderId));`  

 3. ${\bf Shapes}\rightarrow {\bf Visualization}$ :  
	- Use shapes to create simple visualizations of data from other modules  
	- 使用形状为其他模块的数据创建简单的可视化效果  
	- Example: Display animal counts as a bar chart using rectangles  
	- 举例说明：使用矩形以条形图显示动物数量  

Each subsystem exposes only the minimal module API above; your dashboard integrates them in response to button clicks.  
每个子系统只公开上述最基本的模块应用程序接口；您的仪表盘会根据按钮点击情况将它们整合在一起。  

## Team Roles & Responsibilities  <br>团队角色与职责  

<html><body><table><tr><td>Role</td><td>Responsibilities</td></tr><tr><td>Project Manager</td><td>Coordinate meetings, manage version control, assign roles, lead presentation.</td></tr><tr><td>Zoo Specialist</td><td>Implement ZooManager, perform tests, create UML</td></tr><tr><td>Banking Specialist</td><td>Implement BankingService, perform tests, create UML</td></tr><tr><td>Restaurant Specialist</td><td>Implement RestaurantService, perform tests, create UML</td></tr><tr><td>Shape Specialist</td><td>Implement ShapeService, perform tests, create UML</td></tr><tr><td>UI Integrators</td><td>Create dashboard, connect modules, perform integration testing</td></tr></table></body></html>  

## Development Process  <br>开发流程

1. Project kickoff, role assignments, GitHub / Gitee setup ***(Week 10)***   <br>项目启动、角色分配、GitHub / Gitee 设置 ***（第 10 周）***   
2. Individual module development and unit testing   <br>个人模块开发和单元测试   
3. Module review and API finalization   <br>模块审查和 API 定稿   
4. Dashboard development and module integration   <br>仪表板开发和模块集成   
5. System testing and bug fixing   <br>系统测试和错误修复   
6. Final review, submission and presentation ***(Week 12)***   <br>最终审查、提交和演示 ***（第 12 周）***   

## Deliverables  <br>交付成果

1. **Source Code (Zipped project):**  <br>**源代码（压缩项目）：**  
	- Complete Java project with all modules  
	- 包含所有模块的完整 Java 项目  
	- UML class diagram (design files)  
	- UML 类图（设计文件）  
	- All unit tests passing  
	- 所有通过的单元测试  
	- GitHub / Gitee repository with commit history  
	- 包含提交历史的 GitHub / Gitee 代码库  
2. **Documentation:**  <br>**文档：**
	- UML class diagrams for each module  
	- 每个模块的 UML 类图  
	- README with build and run instructions  
	- 包含构建和运行说明的 README  
	- Brief user guide  
	- 简要用户指南  
 3. **Presentation:**  <br>**演示文稿：**  
	- 7 / 8 slides presentation highlighting:  
	- 7 / 8 张幻灯片演示，重点介绍  
		- System architecture  
		- 系统架构  
		- Key features  
		- 主要特点  
		- Integration points  
		- 集成点  
		- Live demonstration  
		- 现场演示  
		- Lessons learned  
		- 经验教训  

## Evaluation Rubrics  <br>评分标准

<html><body><table><tr><td><strong>Criteria</strong></td><td><strong>Score</strong></td><td><strong>Description</strong></td></tr><tr><td>Functionality</td><td>40%</td><td>Modules work as specified and pass unit tests</td></tr><tr><td>Integration</td><td>25%</td><td>Modules communicate properly through the dashboard</td></tr><tr><td>Design & Documentation</td><td>20%</td><td>Clean code structure, proper OOP principles, complete UML</td></tr><tr><td>Teamwork & Process</td><td>15%</td><td>Consistent Git commits, issue tracking, meeting participation</td></tr></table></body></html>  

## Tips for Success  <br>成功秘诀

1. **Start with what you know**: Begin with your existing practical code  <br>**从你所知道的开始**：从现有的实用代码开始   
2. **Keep it simple**: Focus on core functionality first  <br>**保持简单**：首先关注核心功能   
3. **Meet regularly**: As a team, meeting regularly help identify issues early  <br>**定期开会**：作为一个团队，定期开会有助于及早发现问题   
4. **Separate concerns**: Each module should function independently  <br>**分开关注**：每个模块都应独立运作   
5. **Test thoroughly**: Perform tests before or alongside your implementation  <br>**全面测试**：在实施前或实施过程中进行测试   
6. **Document as you go**: Update UML diagrams as your code evolves  <br>**边做边记录**： 随着代码的发展更新 UML 图表  

This group project builds directly on your coursework practicals, focusing on integration rather than creating entirely new systems.  
本小组项目直接建立在课程实践的基础上，重点是集成而不是创建全新的系统。 

Good luck and have fun building a truly integrated Java application!  
祝你好运，祝你在构建真正集成的 Java 应用程序过程中获得乐趣！ 

Special thank to @Anka-Victorique-de-Blois for converting and translating this file from PDF to markdown.  <br>特别感谢@Anka-Victorique-de-Blois转化文件格式并翻译。

Copyright © 2025 @Anka-Victorique-de-Blois All rights reserved.  <br>保留所有权利。  
