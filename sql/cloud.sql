/*
 Navicat Premium Data Transfer

 Source Server         : 47.96.234.28_3303
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 47.96.234.28:3303
 Source Schema         : cloud

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 03/07/2020 10:27:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章标题',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '文章内容',
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '发表人',
  `publishDate` datetime(0) NOT NULL COMMENT '发表时间',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文章简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('59b3a391db9c482d8ce389b9894415f7', 'Hadoop的组成', '## HDFS\n### NameNode\n存储文件的元数据，如文件名，文件目录结构，文件属性（生成时间，副本数，文件权限），以及每个文件的块列表和块所在的DataNode等。\n\n### DataNode\n在本地文件系统存储文件块数据，以及块数据的校验和。\n\n### Secondary NameNode\n用来监控HDFS状态的辅助后台程序，每隔一段时间获取HDFS元数据的快照。\n\n## YRAN \n### ResourceManager\n1. 处理客户端请求\n2. 监控NodeManager\n3. 启动或监控ApplicationMaster\n4. 资源的分配和调度\n\n### NodeManager主要作用如下\n1. 管理单个节点上的资源\n2. 处理来自ResourceManager的命令\n3. 处理来自ApplicationMaster的命令\n\n### ApplicationMaster\n1. 负责数据的切分\n2. 为应用程序申请资源并分配给内部的任务\n3. 任务的监控和容错\n\n### Container\nContainer是YRAN中的资源抽象，它封装了某个节点上的多维度资源，如内存、CPU、磁盘、网络等。\n\n## MapReduce\n1. Map阶段并行处理输入数据\n2. Reduce阶段对Map结果进行汇总', '012a2865a3954548a23863109de9cdd6', '2020-06-05 10:37:52', 'HDFSNameNode存储文件的元数据，如文件名，文件目录结构，文件属性生成时间，副本数，文件权限，以及每个文件的块列表和块所在的DataNode等。DataNode在本地文件系统存储文件块数据，以及块数据的校验和。SecondaryNameNode用来监控HDFS状态的辅助后台程序，每隔一段时间');
INSERT INTO `blog` VALUES ('753913f749b24cfc87d05a863c6fd9e5', 'angular  非父子组件实时通讯', '###  BehaviorSubject 通过service实现\nBehaviorSubject 是 Subject 的变体之一。BehaviorSubject 的特性就是它会存储“当前”的值。这意味着你始终可以直接拿到 BehaviorSubject 最后一次发出的值。\n\n1. 创建service\n```js\nimport {Injectable} from \'@angular/core\';\nimport {BehaviorSubject} from \'rxjs\';\n\n@Injectable({\n  providedIn: \'root\'\n})\nexport class DataService {\n  public message = new BehaviorSubject<string>(\'a\');\n\n  constructor() {\n  }\n\n  changeMessage(message: string): void {\n    this.message.next(message);\n  }\n}\n```\n2. 订阅者\n```js\nexport class DingyuezheComponent implements OnInit {\n  mes;\n  constructor(\n    private dataService: DataService\n  ) {\n  }\n\n  ngOnInit() {\n    this.dataService.message.subscribe(message => {\n      this.mes = message;\n    });\n  }\n}\n```\n3. 发布者\n```js\nexport class FabuzheComponent implements OnInit {\n\n  constructor(\n    private dataService: DataService,\n  ) { }\n\n  ngOnInit() {\n  }\n  change() {\n    this.dataService.changeMessage(\'11111\');\n  }\n}\n```\n\n当发布者触发了change事件，订阅者就会**实时获取**BehaviorSubject当前的值。\n', '012a2865a3954548a23863109de9cdd6', '2020-06-05 10:39:35', 'BehaviorSubject通过service实现BehaviorSubject是Subject的变体之一。BehaviorSubject的特性就是它会存储“当前”的值。这意味着你始终可以直接拿到BehaviorSubject最后一次发出的值。1.创建servicejsimportInjectab');
INSERT INTO `blog` VALUES ('a9e28e390213429e8fafa6758e6d7b40', '父子组件通讯', '### 父子组件传值\n1. 首先在子组件中定义一个变量，并用@input进行接收\n```ts\n@Input() title: string;\n```\n\n2. 子组件的html进行展示\n```html\n{{title}}\n```\n3. 父组件ts文件定义变量\n```ts\ntitle = \'我是父组件的title\';\n```\n4. 在父组件html中引用子组件，并使用单向数据绑定传值。\n```\n<app-header [title]=\"title\"></app-header>\n```\n\n###  父子组件传递方法   \n场景模拟，子组件调用父组件的run方法\n\n1. 首先在子组件定义一个接收父组件方法的参数\n```ts\n @Input() run: any;\n```\n2. 子组件在html中写一个按钮，并给这个按钮加click事件，然后调用父组件的run方法\n```html\n<button (click)=\"getParentRun()\">子组件调用父组件的方法</button>\n```\n3. 在子组件的ts中定义好这个点击事件，并调用父组件的方法，因为run变量接收的是父组件传来的方法，所以直接加一个()就可以执行了。\n```ts\n  getParentRun() {\n    this.run();\n  }\n```\n4. 父组件定义方法，并传递给给子组件\n```ts\n  run() {\n    console.log(\'这是父组件的run方法\');\n  }\n```\nhtml\n```html\n<app-header [title]=\"title\" [run]=\"run\"></app-header>\n```\n注意这里的run不要加（），（）相当于执行函数\n\n**还有一种传值方法，可以把整个父组件都传递给子组件，上面的run改成this就可以了**\n\n\n###  子组件给父组件传值\n 实现方式主要有两种，第一种就是使用@Output进行传值，第二种就是使用@ViewChild()来获得整个子模块来进行取值。\n \n####  @Output\n1. 在子组件ts中生命一个@OutPut变量，并声明一个方法，用来触发点击事件来改变父组件的值。\n```ts\n @Output() testOut = new EventEmitter();\n \n output() {\n    this.testOut.emit(99);\n  }\n```\n\n2. 在父组件来接受子组件传递过来的变量。\n```html\n{{testoutnumber}}\n<br><br><br>\n<app-header [title]=\"title\" [run]=\"run\" (testOut)=\"change($event)\"></app-header>\n```\n这里的testOut要和子组件里面的变量一致。\n3. 父组件ts中声明change函数来改变前台的值\n\n```js\n  testoutnumber = 1;\n\n\n  change(numbertest: any) {\n    this.testoutnumber = numbertest;\n  }\n```\n\n\n\n', '012a2865a3954548a23863109de9cdd6', '2020-04-20 00:00:00', '父子组件传值1.首先在子组件中定义一个变量，并用input进行接收tsInputtitlestring2.子组件的html进行展示htmltitle3.父组件ts文件定义变量tstitle我是父组件的title4.在父组件html中引用子组件，并使用单向数据绑定传值。appheadertitleti');
INSERT INTO `blog` VALUES ('df93cd83484448fa82f0d66fc7424b6b', 'tk_mybatis', '##  tk_mybatis使用\n\n###  maven\n\n```xml\n		<dependency>\n            <groupId>tk.mybatis</groupId>\n            <artifactId>mapper</artifactId>\n            <version>4.0.3</version>\n        </dependency>\n        <!-- https://mvnrepository.com/artifact/tk.mybatis/mapper-spring-boot-starter -->\n        <dependency>\n            <groupId>tk.mybatis</groupId>\n            <artifactId>mapper-spring-boot-starter</artifactId>\n            <version>2.0.3</version>\n        </dependency>\n```\n\n###  详细实现\n\n1. JavaBean实现，加上@table，@id，@column注释让其和数据库表绑定。\n\n   ```java\n   @Table(name = \"student\")\n   public class Student implements Serializable {\n       private static final long serialVersionUID = 1L;\n       @Id\n       @Column(name = \"stu_id\")\n       private String stuId;\n   \n       @Column(name = \"stu_name\")\n       private String stuName;\n   \n       @Column(name = \"stu_sex\")\n       private String stuSex;\n   \n       @Column(name = \"stu_age\")\n       private Integer stuAge;\n   \n       @Column(name = \"stu_phone\")\n       private String stuPhone;\n   \n       @Column(name = \"stu_class\")\n       private String stuClass;\n   	get、set方法略\n   }\n   \n   ```\n\n2. dao层mapper代码，实现tk.mybatis.mapper.common.Mapper接口。\n\n   ```java\n   @Repository\n   public interface StudentMapper extends Mapper<Student> {\n   \n   }\n   ```\n\n3. 启动类配置@MapperScan注释，扫描Mapper接口。\n\n   <font style = \"color:red\">注意，此@MapperScan注释是tk.mybatis.spring.annotation.MapperScan</font>\n\n   ```\n   @MapperScan(\"com.inspur.**.dao\")\n   ```\n\n4.  在application.yml中配置数据源和mapper.yml的位置\n\n   ```yaml\n   spring:\n     datasource:\n       driver-class-name: com.mysql.cj.jdbc.Driver\n       url: jdbc:mysql://47.96.234.28:3303/student?useUnicode=true&characterEncoding=utf-8\n       username: root\n       password: 123456\n     devtools:\n       restart:\n         enabled: true  #设置开启热部署\n         additional-paths: src/main/java #重启目录\n         exclude: WEB-INF/**\n       freemarker:\n         cache: false    #页面不加载缓存，修改即时生效\n   mybatis:\n     mapper-locations: classpath:mapping/*Mapper.xml\n     type-aliases-package: com.inspur.tkmybatis.model\n   server:\n     port: 9000  \n   ```\n\n5. 测试\n\n   ```java\n   @RunWith(SpringJUnit4ClassRunner.class)\n   @SpringBootTest\n   class TkMybatisApplicationTests {\n       @Autowired\n       private StudentMapper studentMapper;\n   \n       @Test\n       void contextLoads() {\n           List<Student> students = studentMapper.selectAll();\n           System.out.println(students);\n       }\n   \n   }\n   ```\n\n   结果：\n\n   ```\n   [Student{stuId=\'1001\', stuName=\'qiao\', stuSex=\'男\', stuAge=20, stuPhone=\'15665776920\', stuClass=\'2\'},\n   Student{stuId=\'1002\', stuName=\'song\', stuSex=\'女\', stuAge=33, stuPhone=\'15556666\', stuClass=\'2\'}]\n   \n   ```\n\n6. 方法介绍\n\n   | 方法                                | 说明                                                         |\n   | ----------------------------------- | ------------------------------------------------------------ |\n   | select(T c)                         | 把c属性不为空的当作查询条件                                  |\n   | selectAll()                         | 查询所有                                                     |\n   | selectByPrimaryKey(Object c)        | 根据主键查询                                                 |\n   | selectCount(T c)                    | 根据查询统计个数                                             |\n   | insert(T c)                         | 使用所有的属性作为字段使用                                   |\n   | insertSelective(T c)                | 使用不为空的属性作为字段使用                                 |\n   | delete(T var1);                     | 使用var1中不为空的属性作为删除条件                           |\n   | deleteByPrimaryKey(Object var1)     | 按主键进行删除                                               |\n   | deleteByExample(Object var1)        | 按条件删除                                                   |\n   | updateByPrimaryKey(T var1)          | 按主键进行更新，所有字段进行更新，如果其中字段有null值，也会赋值null |\n   | updateByPrimaryKeySelective(T var1) | 按主键进行更新，字段不为null的值进行更新                     |\n\n7. Example  使用\n\n   ```java\n   Example example = new Example(Student.class);\n   Example.Criteria criteria = example.createCriteria();\n   ```\n\n   | 方法                                                      | 说明                            |\n   | --------------------------------------------------------- | ------------------------------- |\n   | setOrderByClause(String orderByClause)                    |                                 |\n   | setDistinct(boolean distinct)                             | 是否去重                        |\n   | andEqualTo(String property, Object value)                 | 添加查询条件，property为value   |\n   | andIn(String property, Iterable values)                   | 相当于sql的in操作               |\n   | andIsNull(String property)                                | 属性值为null                    |\n   | andIsNotNull(String property)                             | 属性值不为null                  |\n   | andBetween(String property, Object value1, Object value2) | 属性在value1和value2之间        |\n   | andNotBetween                                             | 属性不在value1和value2之间      |\n   | andLike(String property, String value)                    | 模糊查询，相当于sql的like       |\n   | andNotEqualTo(String property, Object value)              | 添加查询条件，property不为value |\n   | andNotIn(String property, Iterable values)                | 相当于not  in                   |\n   | andNotLike(String property, String value)                 | 相当于  not  like               |\n\n   or开头的方法，猜一下也能出来功能吧？', '012a2865a3954548a23863109de9cdd6', '2020-06-05 10:40:10', 'tkmybatis使用mavenxmldependencygroupIdtk.mybatisgroupIdartifactIdmapperartifactIdversion4.0.3versiondependencyhttpsmvnrepository.comartifacttk.mybatisma');
INSERT INTO `blog` VALUES ('ea2a7fb7d086415b908ab2fb2d97a71a', 'Angular环境搭建', '##  Angular环境搭建\n1. 安装node.js\n2. 安装cnpm\n```shell\nnpm install -g cnpm --registry=https://registry.npm.taobao.org\n```\n3. 安装angular脚手架\n```shell\nnpm install -g @angular/cli    或者\ncnpm  install -g @angular/cli\n```\n\n###  常用命令\n```shell\nng generate component xxx\n```\n\n###  目录说明\n- app  放组件和根模块\n- assets  静态资源文件\n- browserslist： 浏览器设置文件\n- package.json：定义项目的名称，版本，以及所需要的依赖。\n\n\n###  定义属性\n\n```ts\na1 = \'qiaochunxiang\';\na2: string = \'zhangsan\';  //指定类型\na3: any = 123;   //any可以接受任何类型的数据\na4 = [\'aaa\', \'bbb\']; //声明数组\na5: number[] = [111, 222];\na6: Array<number> = [222, 333];\na7: any = [111, \'aaa\', \'wawd\'];\n```\n\n属性修饰符有三个：\n- public : 公有（默认），可以在这个类里面使用，也可以在类外面使用\n- protected :  保护类型，可以在本类里面或者其子类里面使用\n- private : 私有，只能在当前类中使用\n```ts\nprivate a1: string = \'123\';\n```\n\n###  数据绑定 {{}}\n```html\n//如果在标签中可以使用[]进行数据绑定，比如\n<img [src] = \'pUrl\' alt=\"你好啊\">\n```\n\n###  数组循环 *ngFor\n\n```html\n<div *ngFor=\"let new of news\">\n  {{new}}\n</div>\n```\n**获取索引  index**\n```html\n<div *ngFor=\"let new of news; let key = index\">\n {{key}} ------- {{new}}\n</div>\n```\n\n###  条件判断  *ngIf\n```html\n<div *ngIf=\"test\">\n  {{test}}\n</div>\n```\n\n###  条件判断  *ngSwitch\n和Java差不多\n```html\n<div ngSwitch=\"{{score}}\">\n  <p *ngSwitchCase=\"1\">这是1</p>\n  <p *ngSwitchCase=\"2\">这是2</p>\n  <p *ngSwitchCase=\"3\">这是3</p>\n  <p *ngSwitchCase=\"4\">这是4</p>\n  <p *ngSwitchCase=\"5\">这是5</p>\n  <p *ngSwitchDefault>我不知道你写的是什么东西</p>\n</div>\n```\n###  ngClass 和  ngStyle\n```html\n<div [ngClass]=\"{\'blue\':true}\">\n  这是一个小测试\n</div>\n<div [ngStyle]=\"{\'color\':\'blue\'}\">\n  这是一个小测试\n</div>\n```\n\n###  管道\n使用  |  进行管道处理\n\n```html\n{{nowTime | date :\'yyyy-MM-dd  HH:mm:ss\'}}\n```\n\n\n###  双向数据绑定\n```html\n <input [(ngModel)]=\"name\">\n```\n', '012a2865a3954548a23863109de9cdd6', '2020-04-20 00:00:00', 'Angular环境搭建1.安装node.js2.安装cnpmshellnpminstallgcnpmregistryhttpsregistry.npm.taobao.org3.安装angular脚手架shellnpminstallgangularcli或者cnpminstallgangularcli');

-- ----------------------------
-- Table structure for cloud
-- ----------------------------
DROP TABLE IF EXISTS `cloud`;
CREATE TABLE `cloud`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '服务器id',
  `ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '服务器ip',
  `state` smallint(5) NOT NULL COMMENT '服务器状态',
  `memory` int(10) NOT NULL COMMENT '内存',
  `hardpan` int(2) NOT NULL COMMENT '硬盘',
  `core` smallint(2) NOT NULL COMMENT '内核',
  `bandWith` smallint(2) NOT NULL COMMENT '带宽',
  `startDate` datetime(0) NOT NULL COMMENT '申请时间',
  `endDate` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '申请人',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '123456' COMMENT '服务器密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cloud
-- ----------------------------
INSERT INTO `cloud` VALUES ('600bb12bcb0e426999d3690e0743ce24', '192.168.239.129', 1, 2, 40, 1, 1, '2020-06-04 21:23:24', NULL, '012a2865a3954548a23863109de9cdd6', '123456');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公司id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公司名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司地址',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公司电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('033d2b7d0e544853965d3e94aa93b0d0', 'sk', '山东济南', '110');
INSERT INTO `company` VALUES ('1', 'inspur', '111', '15665776920');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `cId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '计算机', '1');
INSERT INTO `department` VALUES ('9b2962ea98ed4a9aaeb7b93136d20db0', '会计', '033d2b7d0e544853965d3e94aa93b0d0');
INSERT INTO `department` VALUES ('b3b847dbc74241988e357ded5481d6c0', 'MOM', '1');
INSERT INTO `department` VALUES ('c078ec78c8864fe7aa81d9b6e744b539', '123', '033d2b7d0e544853965d3e94aa93b0d0');
INSERT INTO `department` VALUES ('ca75091514b94071af54c04d0e854c87', '123', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户id',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`, `username`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `login`(`username`, `password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('012a2865a3954548a23863109de9cdd6', '1', 'xMpCOKC5I4INzFCab3WEmw==');
INSERT INTO `user` VALUES ('8a611f7a18e140b1928ae4c02c421510', 'root', 'Y6nw6nu5gFB5a2SehUgYRQ==');

-- ----------------------------
-- Table structure for userinformation
-- ----------------------------
DROP TABLE IF EXISTS `userinformation`;
CREATE TABLE `userinformation`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '姓名',
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '性别',
  `age` smallint(6) NULL DEFAULT 0 COMMENT '年龄',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'default' COMMENT '头像地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '手机号',
  `qq` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'qq',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个人简介',
  `dId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinformation
-- ----------------------------
INSERT INTO `userinformation` VALUES ('012a2865a3954548a23863109de9cdd6', 'monkey  code', '1', 20, 'a80a18e19a4e4ee8a614d2c4412dc80d', '15665776920', '277465734', '1997-11-15', '山东省聊城市阳谷县金斗营镇子路堤村', '277465734@qq.com', '加油把!', 'b3b847dbc74241988e357ded5481d6c0');
INSERT INTO `userinformation` VALUES ('8a611f7a18e140b1928ae4c02c421510', '1', '1', 0, 'default', '', NULL, '2020-06-09', NULL, '', NULL, '9b2962ea98ed4a9aaeb7b93136d20db0');

SET FOREIGN_KEY_CHECKS = 1;
