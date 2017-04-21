/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.AddressMapper;
import com.sg.superherosighting.mapper.HeroMapper;
import com.sg.superherosighting.mapper.MembersMapper;
import com.sg.superherosighting.mapper.OrganizationMapper;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Members;
import com.sg.superherosighting.model.Organization;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author feng
 */
public class HeroOrganizationBridgeDaoJdbcTemplateImpl implements HeroOrganizationBridgeDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_MEMBER
            = "insert into members (organization_id, hero_id, start_date, end_date) "
            + "values(?,?,?,?)";
    
    private static final String SQL_DELETE_MEMBER
            = "delete from members where member_id = ?";
    
    private static final String SQL_DELETE_MEMBER_BY_HERO_AND_ORG
            = "delete from members where (hero_id = ? and organization_id = ?)";
    
    private static final String SQL_SELECT_MEMBER
            = "select * from members where member_id = ?";
    
    private static final String SQL_SELECT_HEROES_BY_ORG
            = "select h.hero_id, h.alias, h.first_name, h.last_name, h.description "
            + "from heroes h join members m on organization_id "
            + "where h.hero_id = m.hero_id and m.organization_id =?;";
    
//    private static final String SQL_SELECT_ORGS_BY_HERO
//            = "select o.organization_id, o.organization_name, o.description, o.address_id "
//            + "from organization o join members m on m.hero_id "
//            + "where o.organization_id = m.organization_id = m.hero_id = ?;";
    
    private static final String SQL_SELECT_ORGS_BY_HERO  
            = "select o.organization_id, o.organization_name, o.description, o.address_id "
            + "from organization o join members m on m.organization_id = o.organization_id "
            + "where m.hero_id = ?";
    
    private static final String SQL_SELECT_SPECIFIC_HERO
            = "select h.hero_id, h.alias, h.first_name, h.last_name, h.description "
            + "from heroes h join members on h.hero_id = members.hero_id "
            + "where members.member_id = ?";
            
    private static final String SQL_SELECT_SPECIFIC_ORG
            = "select o.organization_id, organization_name, description, address_id "
            + "from organization o join members on o.organization_id = members.organization_id "
            + "where members.member_id = ?";
    
    private static final String SQL_SELECT_ADDRESS_BY_ORG
            = "select ad.address_id, ad.street, ad.city, ad.state, ad.country, ad.zip, ad.world " 
            + "from address ad join organization on ad.address_id = organization.address_id "
            + "where organization.organization_id = ?";
    
    private static final String SQL_SELECT_MEMBERS_BY_HERO
            = "select * "
            + "from members where hero_id = ?";
    
    private static final String SQL_SELECT_MEMBERS_BY_ORG
            = "select * "
            + "from members where organization_id = ?";
    
    private static final String SQL_SELECT_MEMBER_BY_HERO_ORG
            = "select * "
            + "from members where (hero_id = ? and organization_id = ?) ";
    
    @Override
    public List<Members> getMembersByHero(int heroId){
        List<Members> members = jdbcTemplate.query(SQL_SELECT_MEMBERS_BY_HERO, new MembersMapper(), heroId);
        return assembleMembers(members);
    }
    
    @Override
    public List<Members> getMembersByOrg(int orgId){
        List<Members> members = jdbcTemplate.query(SQL_SELECT_MEMBERS_BY_ORG, new MembersMapper(), orgId);
        return assembleMembers(members);
    }
    
    @Override
    public Members getHeroOrgRelation(int heroId, int orgId){
        return jdbcTemplate.queryForObject(SQL_SELECT_MEMBER_BY_HERO_ORG, new MembersMapper(), heroId, orgId);
    }
    
    @Override
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false)
    public Members addMembersBridge(Members member) {
        String startDateString = null;
        String endDateString = null;
        if(member.getStartDate() != null){
            startDateString = member.getStartDate().toString();
        }
        if(member.getEndDate() != null){
            endDateString = member.getEndDate().toString();
        }
        jdbcTemplate.update(SQL_INSERT_MEMBER,
                member.getOrg().getOrgId(),
                member.getHero().getHeroId(),
                startDateString,
                endDateString
                );
        int memberId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        member.setMemberId(memberId);
        return member;
    }

    @Override
    public void delMembersBridge(int memberId) {
        jdbcTemplate.update(SQL_DELETE_MEMBER, memberId);
    }
    
    @Override
    public void delMembersBridge(int heroId, int orgId){
        jdbcTemplate.update(SQL_DELETE_MEMBER_BY_HERO_AND_ORG, heroId, orgId);
    }

    @Override
    public List<Hero> getHeroesByOrg(int orgId) {
        try{
            return jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORG, new HeroMapper(), orgId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Organization> getOrgsByHero(int heroId) {
        try{
            return jdbcTemplate.query(SQL_SELECT_ORGS_BY_HERO, new OrganizationMapper(), heroId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public Members getHeroOrgRelation(int memberId) {
        try{
            Members member = new Members();
            Organization org = new Organization();
            member = jdbcTemplate.queryForObject(SQL_SELECT_MEMBER, new MembersMapper(), memberId);
            org = (jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_ORG, new OrganizationMapper(), memberId));
            org.setAddress(jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_ORG, new AddressMapper(), org.getOrgId()));
            member.setOrg(org);
            member.setHero(jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_HERO, new HeroMapper(), memberId));
            return member;
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public void delAllMemberBridges() {
        jdbcTemplate.update("truncate members");
    }
    
    private List<Members> assembleMembers(List<Members> members){
        List<Members> newMembers = new ArrayList<>();
        for(Members member : members){
            Organization org = new Organization();
            member = jdbcTemplate.queryForObject(SQL_SELECT_MEMBER, new MembersMapper(), member.getMemberId());
            org = (jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_ORG, new OrganizationMapper(), member.getMemberId()));
            org.setAddress(jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_ORG, new AddressMapper(), org.getOrgId()));
            member.setOrg(org);
            member.setHero(jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_HERO, new HeroMapper(), member.getMemberId())); 
            newMembers.add(member);
        }
        return newMembers;
    }
}
