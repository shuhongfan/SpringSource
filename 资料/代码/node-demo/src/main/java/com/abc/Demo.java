package com.abc;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class Demo {
    public void m() {
        // 创建一个来自于appA访问的Context，
        // entranceOne为Context的name
        ContextUtil.enter("entranceOne", "appA");
        // Entry就是一个资源操作对象
        Entry resource1 = null;
        Entry resource2 = null;
        try {
            // 获取资源resource1的entry
            resource1 = SphU.entry("resource1");
            // 代码能走到这里，说明当前对资源resource1的请求通过了流控
            // 对资源resource1的相关业务处理。。。

            // 获取资源resource2的entry
            resource2 = SphU.entry("resource2");
            // 代码能走到这里，说明当前对资源resource2的请求通过了流控
            // 对资源resource2的相关业务处理。。。
        } catch (BlockException e) {
            // 代码能走到这里，说明请求被限流，
            // 这里执行降级处理
        } finally {
            if (resource1 != null) {
                resource1.exit();
            }
            if (resource2 != null) {
                resource2.exit();
            }
        }
        // 释放Context
        ContextUtil.exit();

        // --------------------------------------------------------

        // 创建另一个来自于appA访问的Context，
        // entranceTwo为Context的name
        ContextUtil.enter("entranceTwo", "appA");
        // Entry就是一个资源操作对象
        Entry resource3 = null;
        try {
            // 获取资源resource2的entry
            resource2 = SphU.entry("resource2");
            // 代码能走到这里，说明当前对资源resource2的请求通过了流控
            // 对资源resource2的相关业务处理。。。


            // 获取资源resource3的entry
            resource3 = SphU.entry("resource3");
            // 代码能走到这里，说明当前对资源resource3的请求通过了流控
            // 对资源resource3的相关业务处理。。。

        } catch (BlockException e) {
            // 代码能走到这里，说明请求被限流，
            // 这里执行降级处理
        } finally {
            if (resource2 != null) {
                resource2.exit();
            }
            if (resource3 != null) {
                resource3.exit();
            }
        }
        // 释放Context
        ContextUtil.exit();
    }
}
