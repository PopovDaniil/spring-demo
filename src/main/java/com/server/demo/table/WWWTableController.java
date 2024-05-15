package com.server.demo.table;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class WWWTableController {
    private final TableRepository tableRepository;

    public WWWTableController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @GetMapping("/tables")
    public String getTablesList(Model model) {
        Iterable<Table> tables = this.tableRepository.findAll();
        model.addAttribute("tables", tables);
        return "tablesList";
    }
}
