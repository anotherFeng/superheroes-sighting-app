/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.mapper;

import com.sg.superherosighting.model.Members;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author feng
 */
public class MembersMapper implements RowMapper<Members> {
    @Override
    public Members mapRow(ResultSet rs, int i) throws SQLException{
        Members m = new Members();
        m.setMemberId(rs.getInt("member_id"));
        Timestamp checkStartDate = rs.getTimestamp("start_date");
        Timestamp checkEndDate = rs.getTimestamp("end_date");
        if(checkStartDate != null){
            m.setStartDate(rs.getTimestamp("start_date").toLocalDateTime().toLocalDate());
        }
        if(checkEndDate != null){
            m.setEndDate(rs.getTimestamp("end_date").toLocalDateTime().toLocalDate());
        }
        return m;
    }
}
