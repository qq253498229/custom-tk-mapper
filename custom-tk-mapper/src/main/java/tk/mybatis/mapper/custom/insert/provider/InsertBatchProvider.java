package tk.mybatis.mapper.custom.insert.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

public class InsertBatchProvider extends MapperTemplate {
    public InsertBatchProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 插入
     *
     * @param ms
     */
    public String insertBatch(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        String sql = SqlHelper.insertIntoTable(entityClass, tableName(entityClass)) +
                SqlHelper.insertColumns(entityClass, true, false, false) +
                insertValuesColumns(entityClass, true);

        // 反射把MappedStatement中的设置主键名
        EntityHelper.setKeyProperties(EntityHelper.getPKColumns(entityClass), ms);

        return sql;
    }

    public static String insertValuesColumns(Class<?> entityClass, boolean skipId) {
        StringBuilder sql = new StringBuilder();
        sql.append("<trim prefix=\"VALUES \">");
        sql.append("<foreach collection=\"list\" item=\"item\" separator=\",\">");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        //获取全部列
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnSet) {
            if (!column.isInsertable()) {
                continue;
            }
            if (skipId && column.isId()) {
                continue;
            }
            sql.append(column.getColumnHolder("item")).append(",");
        }
        sql.append("</trim>");
        sql.append("</foreach>");
        sql.append("</trim>");
        return sql.toString();
    }

}