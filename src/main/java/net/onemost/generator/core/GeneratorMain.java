package net.onemost.generator.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import lombok.var;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author  onemost
 */
public class GeneratorMain {

    private String username;

    private String password;

    private String url;

    private String jdbcDriver;

    private DruidDataSource dataSource;

    public GeneratorMain(String username, String password, String url, String jdbcDriver){
        this.username = username;
        this.password = password;
        this.url = url;
        this.jdbcDriver = jdbcDriver;
        initDataSource();
    }

    public void initDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.jdbcDriver);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setUrl(this.url);
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public void start(List<Bucket> buckets) throws Exception{
        for (Bucket bucket: buckets){
            generator(bucket);
        }
    }

    public void start(Bucket bucket) throws Exception{
        generator(bucket);
    }

    protected void generator(Bucket bucket) throws Exception{
        long num = bucket.getNum();
        //获取插入sql
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        String sql = createInsertSql(bucket.getTableName(), bucket.getStrategy());
        Map<String, List<String>> otherColumn = new HashMap<>(bucket.getStrategy().size());
        for(int i = 0;i < num;i++){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            List<Bucket.Strategy> strategies = bucket.getStrategy();
            for(int j = 1;j <= strategies.size(); j++){
                Bucket.Strategy strategy = strategies.get(j - 1);
                if(StringUtils.isNotBlank(strategy.otherTable)){
                    String key = strategy.filed+strategy.otherTable;
                    var otherColumnsData = otherColumn.get(key);
                    if(otherColumnsData == null){
                        otherColumnsData = searchOtherTableData(strategy.otherTable, Lists.newArrayList(strategy.otherColumn), connection);
                        otherColumn.put(key, otherColumnsData);
                    }
                    preparedStatement.setObject(j, strategy.otherColumnHandler.execute(otherColumnsData.get(RandomUtils.nextInt(0, otherColumnsData.size()))));
                }else{
                    preparedStatement.setObject(j, strategy.baseStrategy.generator());
                }
            }
            try{
                if(preparedStatement.executeUpdate() == 0){
                    connection.rollback();
                    throw  new IllegalAccessException("数据插入失败");
                }
            }catch (SQLIntegrityConstraintViolationException e){
                i--;
            }

        }
        connection.commit();
        connection.close();
    }

    private String createInsertSql(String tableName, List<Bucket.Strategy> strategies){
             StringBuffer stringBuffer = new StringBuffer();
             stringBuffer.append("insert into ")
                     .append(tableName)
                     .append("(");
             String attrNames = strategies.stream().map(Bucket.Strategy::getFiled).collect(Collectors.joining(","));
             stringBuffer.append(attrNames).append(") values(");
             String values = strategies.stream().map(node -> "?").collect(Collectors.joining(","));
             stringBuffer.append(values).append(");");
             return stringBuffer.toString();
    }

    private String createQuerySql(String tableName, List<String> column){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select ");
        String attrNames = column.stream().collect(Collectors.joining(","));
        stringBuffer.append(attrNames);
        stringBuffer.append(" from ")
                .append(tableName)
                .append(";");
        return stringBuffer.toString();
    }

    private List<String> searchOtherTableData(String tableName, List<String> column, Connection connection) throws Exception {
        String sql = createQuerySql(tableName, column);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        var result = new ArrayList<String>();
        while(resultSet.next()){
            result.add(resultSet.getString(1));
        }
        return result;
    }

}
