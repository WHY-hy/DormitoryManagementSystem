package com.Bean;

/**
 * 计数器
 */

public class Count {

    private Integer sum;

    //构造函数
    public Count()
    {

    }

    public Count(Integer count)
    {
        this.sum = count;
    }

    //setter和getter
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    //toString
    @Override
    public String toString() {
        return "Count{" +
                "count=" + sum +
                '}';
    }
}
