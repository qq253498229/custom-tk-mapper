package tk.mybatis.mapper.custom.insert;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.custom.insert.provider.InsertBatchProvider;

import java.util.List;

@RegisterMapper
public interface CustomInsertSelectiveMapper<T> {
    /**
     * 批量插入
     *
     * @param record 需要插入的数据List
     * @return 成功插入条数
     */
    @InsertProvider(type = InsertBatchProvider.class, method = "dynamicSQL")
    int insertBatch(List<T> record);

}
