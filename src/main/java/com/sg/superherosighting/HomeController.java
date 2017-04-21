/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting;

import com.sg.superherosighting.model.Sighting;
import com.sg.superherosighting.service.ServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author feng
 */
@Controller
public class HomeController {
    ServiceLayer service;
    
    @Inject
    public HomeController(ServiceLayer service){
        this.service = service;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayFeeds(Model model) {
        List<Sighting> sightingList = service.getLastTenSightings();
        model.addAttribute("sightings", sightingList);
        return "index";
    }
    

    
    
}
