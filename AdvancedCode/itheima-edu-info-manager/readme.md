<h2>分类分包操作</h2>

### 客服接待 -- StudentController

将用户输入的信息封装为学生对象（传递给业务员StudentService）

### 业务员 -- StudentService

做业务处理，例如输入的ID是否存在，将接受到的学生对象传递给仓库管理员StudentDAO

### 库管 -- StudentDAO

库管需要创建Student学生数组长度为5，将接受到的学生对象添加到数组中，返回布尔值

### static关键字 --- 静态

可以修饰成员方法和变量，给类的成员和方法共享数据，可以通过类名吗，调用静态方法只能访问静态成员（带static的成员）；且没有this关键字，因为this需要创建关键字之后才能存在

## 主要的模块

**核心内容：**

**分包分类思想**

首先，将黑马管理系统的交互入口、接纳员、业务员、库管（仓库）分开，将每块只能和内容区分好
然后，进行业务流程设计，有两个系统：学生和老师，学生和老师的业务逻辑基本相识

### 对于学生信息的增删改查

#### **增流程**

1. 在入口处选择添加学生
2. 开始输入学生信息
3. 输入学生信息的时候，需要查重，是否库里面已经存在了这个唯一标识的学号
4. 将输入的信息封装到student的array数组中，封装起来
5. 传递给库管添加到数据库中

#### **删流程**

1. 在入口处选择删除学生
2. 用户输入想要删除学生的学号
3. 系统将学号传递给业务员 -> 库管
4. 看看找不找得到这个ID
5. 找不到就显示找不到；找到了就将这个ID的index变成null
6. 返回

#### **改流程**

1. 在入口处选择修改
2. 用户输入想要修改的学生学号
3. 系统将学号传递给业务员 -> 库管
4. 看看找不找得到这个ID
5. 找不到就显示找不到；找到了就将这个ID的index填充用户输入的内容

#### **查流程**

1. 入口处选择查看
2. 将需求传递给业务员和库管
3. 返回学生student元组信息
4. 展示信息
