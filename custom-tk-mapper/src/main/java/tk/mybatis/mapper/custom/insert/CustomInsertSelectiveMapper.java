package tk.mybatis.mapper.custom.insert;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.custom.insert.provider.InsertBatchProvider;

import java.util.List;

@RegisterMapper
public interface CustomInsertSelectiveMapper<T> {
    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    @InsertProvider(type = InsertBatchProvider.class, method = "dynamicSQL")
    int insertBatch(List<T> record);

}
