package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BidListController {

    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model) {

        model.addAttribute("bidLists", bidListService.findAll());

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList) {

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList,
                           BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            return "bidList/add";
        }

        bidListService.save(bidList);

        model.addAttribute("bidLists", bidListService.findAll());

        return "bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,
                                 Model model) {

        model.addAttribute("bidList",
                bidListService.findById(id));

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid BidList bidList,
                            BindingResult result,
                            Model model) {

        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidListService.update(id, bidList);

        model.addAttribute("bidLists",
                bidListService.findAll());

        return "bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id,
                            Model model) {

        bidListService.delete(id);

        model.addAttribute("bidLists",
                bidListService.findAll());

        return "bidList/list";
    }
}