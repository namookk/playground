package com.playground.user.payload.domain;

import com.playground.user.payload.enumtype.TableSequenceType;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class IdGenerator implements IdentifierGenerator, Configurable {

  private String type;

  @Override
  public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
    this.type = parameters.getProperty("type");
    IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) {
    int year = LocalDate.now().getYear();
    String yearValue = String.valueOf(year).substring(2, 4);

    TableSequenceType sequenceType = TableSequenceType.valueOf(type);
    Long sequence;
    try {
      sequence = getNextSequence(session, Integer.parseInt(yearValue), sequenceType);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    StringBuilder sb = new StringBuilder(type);
    sb.append(yearValue);
    sb.append(String.format("%07d", sequence));

    return sb.toString();
  }

  private Long getNextSequence(SharedSessionContractImplementor session, int year,
      TableSequenceType type) throws SQLException {
    String sql = """
        SELECT sequence
        FROM table_sequence
        WHERE year = ?
        AND sequence_type = ?
        """;

    try (Connection con = session.getJdbcConnectionAccess().obtainConnection()) {
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setInt(1, year);
      preparedStatement.setString(2, type.toString());
      preparedStatement.executeQuery(); // SQL를 실행
      ResultSet rs = preparedStatement.getResultSet();

      if (rs.next()) {
        Long currSequence = Long.valueOf(rs.getString(1));
        Long nextSequence = currSequence + 1;
        updateSequence(con, year, type, nextSequence);
        return nextSequence;
      }

      return createSequence(con, year, type);

    } catch (SQLException sqlException) {
      throw new HibernateException(sqlException);
    }
  }

  private void updateSequence(Connection con, int year, TableSequenceType type, Long nextSequence)
      throws SQLException {
    String sql = """
        UPDATE table_sequence
        SET sequence = ?
        WHERE year = ? 
        AND sequence_type = ? 
        """;

    PreparedStatement preparedStatement = con.prepareStatement(sql);
    preparedStatement.setLong(1, nextSequence);
    preparedStatement.setInt(2, year);
    preparedStatement.setString(3, type.toString());
    preparedStatement.executeUpdate(); // SQL를 실행
  }

  private Long createSequence(Connection con, int year, TableSequenceType type)
      throws SQLException {
    String sql = """
        INSERT INTO table_sequence(year, sequence_type, sequence)
        VALUES(?, ?, ?)
        """;

    final Long defaultSequence = 1L;
    PreparedStatement preparedStatement = con.prepareStatement(sql);
    preparedStatement.setInt(1, year);
    preparedStatement.setString(2, type.toString());
    preparedStatement.setLong(3, defaultSequence);
    preparedStatement.executeUpdate(); // SQL를 실행

    return defaultSequence;
  }
}
