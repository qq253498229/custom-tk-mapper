package cn.codeforfun.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Page<T> {
    private int pageNo = 1, pageSize = 10;
    private long totalCount = -1;
    private List<T> result;
    private Map<String, Object> parameterMap = new HashMap<>();

    public static <T> Page<T> from(List<T> list) {
        Page<T> result = new Page<>();
        if (list instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page) list;
            result.setTotalCount(page.getTotal());
            result.setResult(page.getResult());
        } else {
            result.setTotalCount(list.size());
            result.setResult(list);
        }
        return result;
    }
}
