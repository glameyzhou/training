package org.glamey.training.mybatis.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@MappedTypes(CryptType.class)
public class CryptHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement,
                                    int index, String s, JdbcType jdbcType) throws SQLException {
        byte[] bytes = s.getBytes(Charset.defaultCharset());
        //数据加密、设置值
        String encrypt = Base64.encodeBase64String(bytes);
        preparedStatement.setString(index, encrypt);

    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String ret = resultSet.getString(columnName);
        byte[] bytes = Base64.decodeBase64(ret);
        return new String(bytes, Charset.defaultCharset());
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int index) throws SQLException {
        String ret = resultSet.getString(index);
        byte[] bytes = Base64.decodeBase64(ret);
        return new String(bytes, Charset.defaultCharset());
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
        return null;
    }
}
