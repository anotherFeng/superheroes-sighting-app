/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting;

import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Location;
import com.sg.superherosighting.model.Sighting;
import com.sg.superherosighting.service.ServiceLayer;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author feng
 */
@Controller
@RequestMapping({"/sighting"})
public class SightingController {
    
    ServiceLayer service;
    
    public SightingController(ServiceLayer service){
        this.service = service;
    }
    
    @RequestMapping(value = "/displayReportSightingPage", method= RequestMethod.GET)
    public String displayReportingPage(Model model){
        List<Hero> heroes = service.getAllHeroes();
        List<Location> locations = service.getAllLocations();
        Sighting sighting = new Sighting();
        model.addAttribute("sighting", sighting);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "addSighting";
    }
    
    @RequestMapping(value = "/createSighting", method=RequestMethod.POST)
    public String createSighting(@Valid HttpServletRequest rq ){
        Sighting sighting = new Sighting();
        int heroId = Integer.parseInt(rq.getParameter("hero"));
        int locationId = Integer.parseInt(rq.getParameter("location"));
        Hero hero = service.getHero(heroId);
        Location location = service.getLocation(locationId);

        String sightingDateString = rq.getParameter("sightingDate");
        LocalDateTime sightingDateTime = LocalDateTime.parse(sightingDateString);
        
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setSightingDate(sightingDateTime);
        sighting.setDescription("description");
        service.addSighting(sighting);
        return "redirect:displayReportSightingPage";
    }
}
