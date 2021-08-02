package entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class PageVo<T> {
    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 当前第几页
     */
    private Integer pageNum;

    private Collection<T> list;

    public PageVo(Integer pageNum, Integer pageSize) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            //记录每页显示的记录数
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
        }

        if (this.pageSize > 1000) {
            this.pageSize = 1000;
        }
    }

    public void setPageNum(Integer pageNum) {
        //计算当前页
        if (pageNum == null || pageNum < 0) {
            this.pageNum = 1;
        } else {
            //当前页
            this.pageNum = pageNum;
        }
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
