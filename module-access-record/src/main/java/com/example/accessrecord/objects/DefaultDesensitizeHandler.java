package com.example.accessrecord.objects;

import com.example.devutils.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Objects;

/**
 * Created by AMe on 2020-07-21 20:42.
 */
public class DefaultDesensitizeHandler implements DesensitizeHandler {

    @Override
    public String desensitize(DesensitizeDataType type, Object obj) {
        try {
            if (type == DesensitizeDataType.REQ_PARAMS) return Objects.toString(obj, null);
            return obj == null ? null : JsonUtils.toJsonStr(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }
}
