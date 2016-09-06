package com.sujian.lines.base;

import java.io.Serializable;

/**
 * 实体的基类
 */
public interface BaseEntity {
    class BaseBean implements Serializable {
        public long id;
        public String objectId;
    }
}
