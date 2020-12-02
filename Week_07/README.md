# 学习笔记

> 本周完成了必做题：
> 
> 1. 按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率
> 2. 读写分离-动态切换数据源版本1.0
> 3. 读写分离-数据库框架版本2.0

---

##### 第一题 插入100万订单模拟数据，测试不同方式的插入效率

代码可见 **sql-training/src/main/java/com/task/java/week07/sqltraining/controller/SqlTrainingController.java**


```
@RequestMapping(value = "preparedStatement")
	@ResponseBody
	public String preparedStatement(){

		long startTime = System.currentTimeMillis();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "insert into db.tb_user(id, user_name, passport, nickname, id_card, email, created_at, updated_at) values(?,?,?,?,?,?,now(), now());";
			preparedStatement = connection.prepareStatement(sql);

			for (int i=1; i<1000000; i++){
				preparedStatement.setLong(1,i);
				preparedStatement.setString(2,"name_example");
				preparedStatement.setString(3, "12345");
				preparedStatement.setString(4,"nickname_example");
				preparedStatement.setString(5, i+"");
				preparedStatement.setString(6, "abc@bbc.com");
				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		return "preparedStatement 方式花费的时间共计"+(endTime-startTime)+"ms";
	}

	@RequestMapping(value = "statement")
	@ResponseBody
	public String statement(){
		long startTime = System.currentTimeMillis();
		Statement statement = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();

			for (int i=1; i<1000000; i++){
				String sql = "insert into db.tb_user(id, user_name, passport, nickname, id_card, email, created_at, updated_at) values("+ i +
						",\'name_example\',\'12345\',\'nickname_example\',"+i+",\'abc@bbc.com\',now(), now());";
				statement.execute(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		return "statement方式花费的时间共计"+(endTime-startTime)+"ms";
	}
```


> 定义了两个方法，分别测试了preparedStatement和statement两种方式插入100万数据所花费的时间，结果如下：
> 
> **使用statement花费时间：300273ms**
> 
> **使用preparedStatement花费时间：216552ms**

##### 第二题 读写分离-动态切换数据源版本1.0

代码**SqlTrainingController.java** 中的方法作为入口，使用AOP增强，通过注解再service方法中指定是读取主库还是从库


```
	@Override
	@DataSourceSelector(value = DynamicDataSourceEnum.MASTER)
	public void insert(User user) {
		userMapper.insert(user);
	}

	@Override
	@DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
	public List<User> listAllUsers() {

		UserExample userExample = new UserExample();
		userExample.createCriteria();
		List<User>  listUsers = userMapper.selectByExample(userExample);
		return listUsers;
	}
```




##### 第三题 读写分离-数据库框架版本2.0
这次没有做
有时间再完成
