package com.server.demo.table;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.demo.common.errors.UniqueConstraintError;
import com.server.demo.common.results.AddResult;
import com.server.demo.data.DataRepository;
import com.server.demo.data.common.Command;
import com.server.demo.data.errors.UnknownCommandError;
import com.server.demo.field.FieldRepository;

@RestController
@RequestMapping("/table")
public class TableController {
    private final TableRepository tableRepository;
    private final FieldRepository fieldRepository;
    private final DataRepository dataRepository;

    public TableController(TableRepository tableRepository, FieldRepository fieldRepository,
            DataRepository dataRepository) {
        this.tableRepository = tableRepository;
        this.fieldRepository = fieldRepository;
        this.dataRepository = dataRepository;
    }

    @GetMapping("/")
    public Iterable<Table> getList() {
        return tableRepository.findAll();
    }

    @GetMapping("/{tableName}")
    public ResponseEntity<Table> getByName(@PathVariable(name = "tableName") String tableName) {
        var result = tableRepository.findByName(tableName);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{tableName}/apply")
    public ResponseEntity<String> apply(@PathVariable(name = "tableName") String tableName) {
        Table table = tableRepository.findByName(tableName);
        if (table == null)
            return ResponseEntity.notFound().build();
        this.dataRepository.createTable(table);
        return ResponseEntity.ok().body("Applied");
    }

    @PostMapping("/{tableName}/revert")
    public ResponseEntity<String> revert(@PathVariable(name = "tableName") String tableName) {
        Table table = tableRepository.findByName(tableName);
        if (table == null)
            return ResponseEntity.notFound().build();
        this.dataRepository.dropTable(table);
        return ResponseEntity.ok().body("Reverted");
    }

    @PostMapping("/{tableName}/call")
    public ResponseEntity<Object> call(@PathVariable(name = "tableName") String tableName,
            @RequestBody Command command) {
        Table table = tableRepository.findByName(tableName);
        if (table == null)
            return ResponseEntity.notFound().build();
        try {
            var result = dataRepository.executeCommand(table, command);

            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.ok(new UnknownCommandError(command.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/")
    public @ResponseBody AddResult<Table> add(@RequestBody Table tableEntity) {
        try {
            tableEntity.fields.forEach((field) -> field.setTable(tableEntity));
            tableRepository.save(tableEntity);
            tableEntity.fields.forEach((field) -> fieldRepository.save(field));
            return new AddResult<Table>(tableEntity);
        } catch (DataIntegrityViolationException e) {
            return new AddResult<Table>(new UniqueConstraintError("name", tableEntity.getFullName()));
        }
    }
}
