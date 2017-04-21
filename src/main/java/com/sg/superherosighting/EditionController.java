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
import com.sg.superherosighting.model.Sighting;
import com.sg.superherosighting.service.ServiceLayer;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author feng
 */
@Controller
@RequestMapping({"/edit"})
public class EditionController {

    ServiceLayer service;

    public EditionController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayEditHero", method = RequestMethod.GET)
    public String displayEditHero(HttpServletRequest request, Model model) {
        String id = request.getParameter("heroId");
        int heroId = Integer.parseInt(id);
        Hero hero = service.getHero(heroId);
        List<Power> powers = service.getAllPowers();
        List<Power> hasPowers = service.getPowersByHero(heroId);
        List<Integer> hasPowerIds = new ArrayList<>();
        for (Power power : hasPowers) {
            hasPowerIds.add(power.getPowerId());
        }
        powers = powers.stream().filter(p -> !hasPowerIds.contains(p.getPowerId())).collect(Collectors.toList());

        List<Organization> orgs = service.getAllOrgs();
        List<Organization> inOrg = service.getOrgsByHero(heroId);
        List<Integer> hasOrgIds = new ArrayList<>();
        for (Organization org : inOrg) {
            hasOrgIds.add(org.getOrgId());
        }
        orgs = orgs.stream().filter(o -> !hasOrgIds.contains(o.getOrgId())).collect(Collectors.toList());

        Organization org = inOrg.get(0);

        model.addAttribute("hasPowers", hasPowers);
        model.addAttribute("inOrg", org);
        model.addAttribute("hero", hero);
        model.addAttribute("powers", powers);
        model.addAttribute("orgs", orgs);
        return "editHero";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public ModelAndView editHero(HttpServletRequest request) {
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        int orgId = Integer.parseInt(request.getParameter("organization"));
        int oldOrgId = Integer.parseInt(request.getParameter("oldOrg"));
        Hero hero = service.getHero(heroId);
        Organization newOrg = service.getOrg(orgId);
        Organization oldOrg = service.getOrg(oldOrgId);

        if (newOrg != oldOrg) {
            Members memberToDelete = service.getHeroOrgRelation(heroId, oldOrgId);
            service.delMembersBridge(memberToDelete.getMemberId());
            Members memberToAdd = new Members();
            memberToAdd.setHero(hero);
            memberToAdd.setOrg(newOrg);

            String joinDateString = request.getParameter("joinDate");
            if (!joinDateString.isEmpty()) {
                LocalDate startDate = LocalDate.parse(joinDateString, DateTimeFormatter.ISO_DATE);
                memberToAdd.setStartDate(startDate);
            }

            String endDateString = request.getParameter("endDate");
            if (!endDateString.isEmpty()) {
                LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_DATE);
                memberToAdd.setEndDate(endDate);
            }
            service.addMembersBridge(memberToAdd);
        }

        service.updateHero(hero);
        return new ModelAndView("redirect:/retrieval/displayAllHeroesPage");
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {
        int powerId = Integer.parseInt(request.getParameter("powerId"));
        int hero = Integer.parseInt(request.getParameter("heroId"));
        service.delHeroPowerBridge(hero, powerId);
        return "redirect:displayEditHero?heroId=" + hero;
    }

    @RequestMapping(value = "/addPower", method = RequestMethod.GET)
    public String addPower(HttpServletRequest request) {
        int powerId = Integer.parseInt(request.getParameter("powerId"));
        int hero = Integer.parseInt(request.getParameter("heroId"));
        service.addHeroPowerBridge(hero, powerId);
        return "redirect:displayEditHero?heroId=" + hero;
    }

    //==========================================================================
    //==========================================================================
    //==========================================================================
    @RequestMapping(value = "/displayEditSighting", method = RequestMethod.GET)
    public String displayEditSighting(HttpServletRequest request, Model model) {
        int sightingId = Integer.parseInt(request.getParameter("sightingId"));
        Sighting sighting = service.getHeroLocationRelationship(sightingId);
        List<Location> locations = service.getAllLocations();
        Location currentLocation = service.getLocation(sighting.getLocation().getLocationId());
        Location loc = new Location();

        List<Hero> heroes = service.getAllHeroes();
        Hero currentHero = service.getHero(sighting.getHero().getHeroId());
        Hero h = new Hero();

        for (Location location : locations) {
            if (location.getLocationId() == currentLocation.getLocationId()) {
                loc = service.getLocation(location.getLocationId());
            }
        }
        locations.remove(loc);
        for (Hero hero : heroes) {
            if (hero.getHeroId() == currentHero.getHeroId()) {
                h = service.getHero(hero.getHeroId());
            }
        }
        heroes.remove(h);
        model.addAttribute("sighting", sighting);
        model.addAttribute("locations", locations);
        model.addAttribute("currentLocation", currentLocation);
        model.addAttribute("heroes", heroes);
        model.addAttribute("currentHero", currentHero);
        return "editSighting";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public ModelAndView editSighting(HttpServletRequest request, Model model) {
        int sightingId = Integer.parseInt(request.getParameter("sightingId"));
        Sighting sighting = service.getHeroLocationRelationship(sightingId);

        String sightingDateString = request.getParameter("sightingDate");
        

        String description = request.getParameter("description");
        
        LocalDateTime sightingDate = LocalDateTime.parse(sightingDateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int oldHeroId = Integer.parseInt(request.getParameter("oldHero"));
        int newHeroId = Integer.parseInt(request.getParameter("hero"));
        Hero oldHero = service.getHero(oldHeroId);
        Hero newHero = service.getHero(newHeroId);

        int oldLocationId = Integer.parseInt(request.getParameter("oldLocation"));
        int newLocationId = Integer.parseInt(request.getParameter("location"));
        Location oldLocation = service.getLocation(oldLocationId);
        Location newLocation = service.getLocation(newLocationId);

        if (oldHero != newHero) {
            sighting.setHero(newHero);
        }
        if (oldLocation != newLocation) {
            sighting.setLocation(newLocation);
        }
        sighting.setSightingDate(sightingDate);
        sighting.setDescription(description);
        service.updateSighting(sighting);
        return new ModelAndView("redirect:/retrieval/displayAllSightingsPage");
    }
    
    //==========================================================================
    //==========================================================================
    //==========================================================================
    
    @RequestMapping(value = "/displayEditLocation", method = RequestMethod.GET)
    public String displayEditLocation(HttpServletRequest request, Model model) {
        Location location = service.getLocation(Integer.parseInt(request.getParameter("locationId")));
        model.addAttribute("location", location);
        return "editLocation";
    }
    //not working properly, come back later.
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public ModelAndView editLocation(@ModelAttribute("location") Location location) {
        service.updateLocation(location);
        return new ModelAndView("redirect:/retrieval/displayAllLocationsPage");
    }
    
    @RequestMapping(value = "/displayEditOrganization", method = RequestMethod.GET)
    public String displayEditOrganization(HttpServletRequest request, Model model) {
        Organization organization = service.getOrg(Integer.parseInt(request.getParameter("orgId")));
        model.addAttribute("organization", organization);
        return "editOrganization";
    }
    //not working properly, come back later.
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public ModelAndView editOrganization(@ModelAttribute("organization") Organization organization) {
        service.updateOrg(organization);
        return new ModelAndView("redirect:/retrieval/displayAllOrganizationsPage");
    }
    
    
    @RequestMapping(value = "/displayEditPower", method = RequestMethod.GET)
    public String displayEditPower(HttpServletRequest request, Model model) {
        Power power = service.getPower(Integer.parseInt(request.getParameter("powerId")));
        model.addAttribute("power", power);
        return "editPower";
    }
    
    @RequestMapping(value = "/editPower", method = RequestMethod.POST)
    public ModelAndView editPower(@ModelAttribute("power") Power power) {
        service.updatePower(power);
        return new ModelAndView("redirect:/retrieval/displayAllPowersPage");
    }
}
