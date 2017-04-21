/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Location;
import com.sg.superherosighting.model.Members;
import com.sg.superherosighting.model.Organization;
import com.sg.superherosighting.model.Power;
import com.sg.superherosighting.service.ServiceLayer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author feng
 */
@Controller
@RequestMapping({"/heroesPower"})
public class CreationController {

    ServiceLayer service;

    public CreationController(ServiceLayer service) {
        this.service = service;
    }
    //==========================================================================
    //==========================================================================
    //==========================================================================

    @RequestMapping(value = "/displayCreateHeroPage", method = RequestMethod.GET)
    public String displayCreateHeroPowerPage(Model model) {
        Hero hero = new Hero();
        List<Power> powers = service.getAllPowers();
        List<Organization> orgs = service.getAllOrgs();
        model.addAttribute("hero", hero);
        model.addAttribute("powers", powers);
        model.addAttribute("orgs", orgs);
        return "addHero";
    }

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request) {
        Hero hero = new Hero();
        Members member = new Members();
        hero.setAlias(request.getParameter("alias"));
        hero.setFirstName(request.getParameter("firstName"));
        hero.setLastName(request.getParameter("lastName"));
        String orgString = request.getParameter("organization");

        hero.setDescription(request.getParameter("description"));
        
        if(orgString != null){
            int orgId = Integer.parseInt(orgString);
            Organization org = service.getOrg(orgId);
            member.setOrg(org);
            member.setHero(hero);
        }
//        int orgId = Integer.parseInt(request.getParameter("organization"));
//        Organization org = service.getOrg(orgId);
        

        
        String endDateString = request.getParameter("endDate");
        if(!endDateString.isEmpty()){
            LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_DATE);
            member.setEndDate(endDate);
        }
        
        
        

        String[] powers = request.getParameterValues("power");
        service.assembleHero(hero, powers, member);
        return "redirect:displayCreateHeroPage";
    }
    //==========================================================================
    //==========================================================================
    //==========================================================================

    @RequestMapping(value = "/displayCreatePowerPage", method = RequestMethod.GET)
    public String displayCreatePowerPage(Model model) {
        Power power = new Power();
        model.addAttribute("power", power);
        return "addPower";
    }

    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(@ModelAttribute("power") Power power) {
        service.addPower(power);
        return "redirect:displayCreatePowerPage";
    }
    //==========================================================================
    //==========================================================================
    //==========================================================================

    @RequestMapping(value = "displayCreateOrgPage", method = RequestMethod.GET)
    public String displayCreateOgPage(Model model) {
        Organization org = new Organization();
        Address add = new Address();
        model.addAttribute("org", org);
        model.addAttribute("add", add);
        return "addOrganization";
    }

    @RequestMapping(value = "/createOrg", method = RequestMethod.POST)
    public String createOrg(HttpServletRequest rq) {
        Organization org = new Organization();
        Address add = new Address();
        add.setAddress(rq.getParameter("address"));
        add.setCity(rq.getParameter("city"));
        add.setState(rq.getParameter("state"));
        add.setCountry(rq.getParameter("country"));
        add.setZip(rq.getParameter("zip"));
        add.setWorld(rq.getParameter("world"));

        org.setOrgName(rq.getParameter("orgName"));
        org.setDescription(rq.getParameter("description"));

        service.assembleOrganization(org, add);
        return "redirect:displayCreateOrgPage";
    }

    //==========================================================================
    //==========================================================================
    //==========================================================================
    @RequestMapping(value = "displayCreateLocationPage", method = RequestMethod.GET)
    public String displayCreateLocationPage(Model model) {
        return "addLocation";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest rq) {
        Location location = new Location();
        Address add = new Address();
        add.setAddress(rq.getParameter("address"));
        add.setCity(rq.getParameter("city"));
        add.setState(rq.getParameter("state"));
        add.setCountry(rq.getParameter("country"));
        add.setZip(rq.getParameter("zip"));
        add.setWorld(rq.getParameter("world"));
        
        location.setLocationName(rq.getParameter("locationName"));
        location.setDescription(rq.getParameter("description"));
        
        String latitudeString = rq.getParameter("latitude");
        String longitudeString = rq.getParameter("longitude");
        BigDecimal latitude = new BigDecimal(latitudeString);
        BigDecimal longitude = new BigDecimal(longitudeString);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        
        service.assembleLocation(location, add);
        return "redirect:displayCreateLocationPage";
    }
}
