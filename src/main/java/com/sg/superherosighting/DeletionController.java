/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting;

import com.sg.superherosighting.model.Sighting;
import com.sg.superherosighting.service.ServiceLayer;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author feng
 */
@Controller
@RequestMapping({"/delete"})
public class DeletionController {
        ServiceLayer service;

    public DeletionController(ServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/hero", method=RequestMethod.GET)
    public ModelAndView deleteHero(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("heroId"));

        service.deleteHero(id);
        return new ModelAndView("redirect:/retrieval/displayAllHeroesPage");
    }
    
    @RequestMapping(value="/power", method=RequestMethod.GET)
    public ModelAndView deletePower(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("powerId"));
        
        service.delPower(id);
        return new ModelAndView("redirect:/retrieval/displayAllPowersPage");
    }
    
    @RequestMapping(value="/organization", method=RequestMethod.GET)
    public ModelAndView deleteOrganization(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("orgId"));
        
        service.delOrg(id);
        return new ModelAndView("redirect:/retrieval/displayAllOrganizationsPage");
    }
    
    @RequestMapping(value="/sighting", method=RequestMethod.GET)
    public ModelAndView deleteSighting(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("sightingId"));
        Sighting sighting = service.getHeroLocationRelationship(id);
        service.delSighting(sighting);
        return new ModelAndView("redirect:/retrieval/displayAllSightingsPage");
    }
    
    @RequestMapping(value="/location", method=RequestMethod.GET)
    public ModelAndView deleteLocation(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("locationId"));
        service.deleteLocation(id);
        return new ModelAndView("redirect:/retrieval/displayAllLocationsPage");
    }
}
