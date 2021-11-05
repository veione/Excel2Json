package com.think.tool.handler;

/**
 * 类型处理器工厂类
 *
 * @author veione
 */
public class TypeHandlerFactory {
    private static final String INT_TYPE = "int" ;
    private static final String STRING_TYPE = "string" ;
    private static final String BOOL_TYPE = "bool" ;
    private static final String FLOAT_TYPE = "float" ;
    private static final String DOUBLE_TYPE = "double" ;
    private static final String DATE_TYPE = "date" ;
    private static final String OBJECT_TYPE = "object" ;
    private static final String ARRAY_TYPE = "array" ;
    private static final String JSON_TYPE = "json" ;
    private static final String INT_ARRAY_TYPE = "int*" ;
    private static final String STRING_ARRAY_TYPE = "string*" ;
    private static final String BOOL_ARRAY_TYPE = "bool*" ;
    private static final String FLOAT_ARRAY_TYPE = "float*" ;
    private static final String DOUBLE_ARRAY_TYPE = "double*" ;
    private static final String DATE_ARRAY_TYPE = "date*" ;

    /**
     * 获取对应的处理器
     *
     * @param value
     * @return
     */
    public static TypeHandler getTypeHandler(String value) {
        if (value.trim().equalsIgnoreCase(INT_TYPE)) {
            return new IntTypeHandler();
        } else if (value.trim().equalsIgnoreCase(STRING_TYPE)) {
            return new StringTypeHandler();
        } else if (value.trim().equalsIgnoreCase(BOOL_TYPE)) {
            return new BoolTypeHandler();
        } else if (value.trim().equalsIgnoreCase(FLOAT_TYPE)) {
            return new FloatTypeHandler();
        } else if (value.trim().equalsIgnoreCase(DOUBLE_TYPE)) {
            return new DoubleTypeHandler();
        } else if (value.trim().equalsIgnoreCase(DATE_TYPE)) {
            return new DateTypeHandler();
        } else if (value.trim().equalsIgnoreCase(OBJECT_TYPE)) {
            return new ObjectTypeHandler();
        } else if (value.trim().equalsIgnoreCase(ARRAY_TYPE)) {
            return new ArrayTypeHandler();
        } else if (value.trim().equalsIgnoreCase(INT_ARRAY_TYPE)) {
            return new IntArrayTypeHandler();
        } else if (value.trim().equalsIgnoreCase(STRING_ARRAY_TYPE)) {
            return new StringArrayTypeHandler();
        } else if (value.trim().equalsIgnoreCase(BOOL_ARRAY_TYPE)) {
            return new BoolArrayTypeHandler();
        } else if (value.trim().equalsIgnoreCase(FLOAT_ARRAY_TYPE)) {
            return new FloatArrayTypeHandler();
        } else if (value.trim().equalsIgnoreCase(DOUBLE_ARRAY_TYPE)) {
            return new DoubleArrayTypeHandler();
        } else if (value.trim().equalsIgnoreCase(DATE_ARRAY_TYPE)) {
            return new DateArrayTypeHandler();
        } else if (value.trim().equalsIgnoreCase(JSON_TYPE)) {
            return new ObjectTypeHandler();
        }
        return new StringTypeHandler();
    }
}
