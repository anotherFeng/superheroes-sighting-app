/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Location;
import com.sg.superherosighting.model.Organization;
import com.sg.superherosighting.model.Power;
import com.sg.superherosighting.model.Sighting;
import com.sg.superherosighting.service.ServiceLayer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author feng
 */
@Controller
@RequestMapping({"/retrieval"})
public class RetrievalController {
    
    ServiceLayer service;
    
    public RetrievalController(ServiceLayer service){
        this.service = service;
    }
    
    
    @RequestMapping(value = "/displayAllHeroesPage", method= RequestMethod.GET)
    public String displayAllHeroesPage(Model model){
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);
        return "showHeroes";
    }
    
    @RequestMapping(value = "/displayHeroDetails", method= RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("heroId"));
        List<Power> powers = service.getPowersByHero(id);
        List<Organization> orgs = service.getOrgsByHero(id);
        List<Sighting> sightings = service.getSightingsByHero(id);
        Hero hero = service.getHero(id);
        model.addAttribute("hero", hero);
        model.addAttribute("powerList", powers);
        model.addAttribute("orgs", orgs);
        model.addAttribute("sightings", sightings);
        return "showHeroDetails";
    }
    
    @RequestMapping(value = "/displayAllPowersPage", method= RequestMethod.GET)
    public String displayAllPowersPage(Model model){
        List<Power> powers = service.getAllPowers();
        model.addAttribute("powers", powers);
        return "showPowers";
    }
    
    @RequestMapping(value = "/displayPowerDetails", method= RequestMethod.GET)
    public String displayPowerDetails(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("powerId"));
        List<Hero> heroes = service.getHeroesByPower(id);
        Power power = service.getPower(id);
        model.addAttribute("heroes", heroes);
        model.addAttribute("power", power);
        return "showPowerDetails";
    }
        
    @RequestMapping(value = "/displayAllOrganizationsPage", method= RequestMethod.GET)
    public String displayAllOrganizationsPage(Model model){
        List<Organization> orgs = service.getAllOrgs();
        model.addAttribute("orgs", orgs);
        return "showOrganizations";
    }
    
    @RequestMapping(value = "/displayOrgDetails", method= RequestMethod.GET)
    public String displayOrgDetails(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("orgId"));
        Organization org = service.getOrg(id);
        List<Hero> heroes = service.getHeroesByOrg(id);
        Address address = service.getAddress(org.getAddress().getAddressId());
        model.addAttribute("org", org);
        model.addAttribute("heroes", heroes);
        model.addAttribute("address", address);
        return "showOrganizationDetails";
    }
    
    @RequestMapping(value = "/displayAllSightingsPage", method= RequestMethod.GET)
    public String displayAllSightingsPage(Model model){
        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightings", sightings);
        return "showSightings";
    }
    
    @RequestMapping(value = "/displaySightingDetails", method= RequestMethod.GET)
    public String displaySigtingDetails(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("sightingId"));
        Sighting sighting = service.getHeroLocationRelationship(id);
        model.addAttribute("sighting", sighting);
        return "showSightingDetails";
    }
    
    @RequestMapping(value = "/displayAllLocationsPage", method= RequestMethod.GET)
    public String displayAllLocationsPage(Model model){
        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);
        return "showLocations";
    }
    
    @RequestMapping(value = "/displayLocationDetails", method= RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("locationId"));
        Location location = service.getLocation(id);
        List<Sighting> sightings = service.getSightingsByLocation(id);
        Address address = service.getAddress(location.getAddress().getAddressId());
        model.addAttribute("location", location);
        model.addAttribute("sightings", sightings);
        model.addAttribute("address", address);
        return "showLocationDetails";
    }
            
    
}
