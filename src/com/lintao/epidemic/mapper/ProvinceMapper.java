package com.lintao.epidemic.mapper;

import com.lintao.epidemic.bean.ProvinceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作省份表的mapper
 */
@Mapper
public interface ProvinceMapper {
    /**
     * 根据日期查询还没有统计疫情信息的省份信息
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Select(value="SELECT p.province_id,p.province_name,p.province_py FROM provinces p " +
            "  WHERE (p.del_flag IS NULL OR p.del_flag = 0)" +
            "  AND p.province_id NOT IN(" +
            "     SELECT e.province_id FROM epidemics e" +
            "     WHERE e.data_year=#{arg0} AND e.data_month=#{arg1} AND e.data_day =#{arg2}  " +
            "  ) ORDER BY p.province_id LIMIT 0,6")
    List<ProvinceInfo> findNoDataProvinces(short year, short month, short day);
}
